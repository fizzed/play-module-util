package com.mfizz.play.util;

import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.util.concurrent.ConcurrentHashMap;

import play.Logger;
import play.Play;
import play.api.mvc.Action;
import play.api.mvc.ActionBuilder;
import play.api.mvc.AnyContent;
import play.mvc.Controller;

public class CacheableAssets extends Controller {
	
	private static final ConcurrentHashMap<String,String> fingerprints = new ConcurrentHashMap<String,String>();
	
	public static Action<AnyContent> at(String path, String file) throws Exception {
		// if the file ends with a fingerprint -- strip it out
		String no_fp_file = stripFingerprint(file);
		
		// delegate to normal handler
		Action<AnyContent> action = controllers.Assets.at(path, no_fp_file);
		
		//Logger.info("action class: " + action.getClass());
		
		return action;
    }
	
	public static String at(String file) throws Exception {
		URL url = play.Play.application().resource("/public/" + file);
		//Logger.info("file: " + file + " -> url: " + url);
		
		if (url == null) {
			// file doesn't exist -- might be nice to warn the user
			// fallback to normal asset handler
			return play.Play.application().configuration().getString("application.context", "") + "/assets/" + file;
		} else {
			// strip the file extension
			String fileNoExt = file;
			String ext = "";
			int extPos = file.lastIndexOf('.');
			if (extPos > 0) {
				fileNoExt = file.substring(0, extPos);
				ext = file.substring(extPos);
			}
			
			// in production, we permanently cache the hash of a file
			String fp = fingerprints.get(file);
			
			// if not in prod *always* generate a fresh fingerprint
			if (fp == null || !Play.isProd()) {
				// generate new fingerprint
				fp = fingerprint(url);
				fingerprints.putIfAbsent(file, fp);
				if (Play.isProd()) {
					Logger.debug("generated fingerprint " + fp + " for file " + file);
				}
			}
			
			return play.Play.application().configuration().getString("application.context", "") + "/cacheable/" + fileNoExt + "." + fp + ext;
		}
    }
	
	public static final String stripFingerprint(String file) throws Exception {
		int extPos = file.lastIndexOf('.');
		if (extPos > 0) {
			int fpPos = file.lastIndexOf('.', extPos-1);
			if (fpPos > 0) {
				String possible_fp = file.substring(fpPos+1, extPos);
				//Logger.info("possible fingerprint: " + possible_fp);
				// should look like "{128-bit hash}" such as "e03f23b3da70fc0d7ae1942f97396c78"
				if (possible_fp != null && possible_fp.length() == 32) {
					String file_before_fp = file.substring(0, fpPos);
					String file_after_fp = file.substring(extPos);
					return file_before_fp + file_after_fp;
				}
			}
		}
		
		// assume no fingerprint
		return file;
	}
	
	public static final String fingerprint(URL url) throws Exception {
		MessageDigest md = MessageDigest.getInstance("md5");
	    InputStream fis = url.openStream();
	    byte[] dataBytes = new byte[1024];
	 
	    int nread = 0; 
	 
	    while ((nread = fis.read(dataBytes)) != -1) {
	    	md.update(dataBytes, 0, nread);
	    };
	 
	    byte[] mdbytes = md.digest();
	 
	    // convert the byte to hex format
	    StringBuffer sb = new StringBuffer("");
	    for (int i = 0; i < mdbytes.length; i++) {
	    	sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
	    }
	    
	    return sb.toString();
	}
	
}
