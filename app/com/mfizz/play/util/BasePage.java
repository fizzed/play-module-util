package com.mfizz.play.util;

import static com.mfizz.play.util.Page.*;

import java.util.Collections;
import java.util.Map;

public abstract class BasePage {
	
	protected final Page parent;
	protected final Map<String,String> values;
	
	// protected access to modifiable version of the map
	protected BasePage(Page parent, Map<String,String> values, boolean modifiable) {
		this.parent = parent;
		this.values = (modifiable ? values : Collections.unmodifiableMap(values));
	}
	
	public boolean hasParent() {
		return this.parent != null;
	}
	
	public Page parent() {
		return this.parent;
	}
	
	public boolean has(String key) {
		return this.values.containsKey(key.toLowerCase());
	}
	
	public boolean hasAny(String key) {
		// walk up chain until we get a non-null
		for (BasePage p = this; p != null; ) {
			if (p.has(key)) {
				return true;
			}
			p = p.parent();
		}
		return false;
	}
	
	public boolean hasNonEmpty(String key) {
		return (getNonEmpty(key) != null);
	}
	
	public boolean hasAnyNonEmpty(String key) {
		// walk up chain until we get a non-null
		for (BasePage p = this; p != null; ) {
			if (p.hasNonEmpty(key)) {
				return true;
			}
			p = p.parent();
		}
		return false;
	}
	
	public String get(String key) {
		return this.values.get(key.toLowerCase());
	}
	
	public String get(String ... keys) {
		for (String key : keys) {
			String v = get(key);
			if (v != null) { return v; }
		}
		return null;
	}
	
	public String getNonEmpty(String key) {
		return nonEmpty(this.values.get(key.toLowerCase()));
	}
	
	public String getOrElse(String key, String defaultValue) {
		String v = this.values.get(key);
		return (v == null ? defaultValue : v);
	}
	
	public String getAny(String key) {
		// walk up chain until we get a non-null
		for (BasePage p = this; p != null; ) {
			String v = p.get(key);
			if (v != null) {
				return v;
			}
			p = p.parent();
		}
		return null;
	}
	
	public String getAny(String ... keys) {
		// walk up chain until we get a non-null
		for (BasePage p = this; p != null; ) {
			String v = p.get(keys);
			if (v != null) {
				return v;
			}
			p = p.parent();
		}
		return null;
	}
	
	public String getAnyOrElse(String key, String defaultValue) {
		String v = getAny(key);
		return (v == null ? defaultValue : v);
	}
	
	static public boolean isEmpty(String v) {
		if (v == null || v.equals("")) {
			return true;
		} else {
			return false;
		}
	}
	
	static public String nonEmpty(String v) {
		if (v == null || v.equals("")) {
			return null;
		} else {
			return v;
		}
	}
	
	/** convenience getters for common properties **/
	
	public String id() {
		return getNonEmpty(ID);
	}
	
	public String name() {
		return getNonEmpty(NAME);
	}
	
	public String url() {
		return getNonEmpty(URL);
	}
	
	public String brand() {
		return get(BRAND);
	}
	
	public String anyBrand() {
		return getAny(BRAND);
	}
	
	public String title() {
		return get(TITLE);
	}
	
	public String anyTitle() {
		return getAny(TITLE);
	}
	
	public String description() {
		return get(DESCRIPTION);
	}
	
	public String anyDescription() {
		return getAny(DESCRIPTION);
	}
	
	public String keywords() {
		return get(KEYWORDS);
	}
	
	public String anyKeywords() {
		return getAny(KEYWORDS);
	}
	
	public String author() {
		return get(AUTHOR);
	}
	
	public String anyAuthor() {
		return getAny(AUTHOR);
	}
	
	public String sectionTitle() {
		return get(SECTION_TITLE);
	}
	
	public String anySectionTitle() {
		return getAny(SECTION_TITLE);
	}
	
	public String breadcrumbTitle() {
		return get(BREADCRUMB_TITLE);
	}
	
	
	public String twitterCard() {
		return get(TWITTER_CARD);
	}
	
	public String anyTwitterCard() {
		return getAny(TWITTER_CARD);
	}
	
	public String twitterSite() {
		return get(TWITTER_SITE);
	}
	
	public String anyTwitterSite() {
		return getAny(TWITTER_SITE);
	}
	
	public String twitterCreator() {
		return get(TWITTER_CREATOR);
	}
	
	public String anyTwitterCreator() {
		return getAny(TWITTER_CREATOR);
	}
	
	public String twitterTitle() {
		return get(TWITTER_TITLE);
	}
	
	/**
	 * Gets best title to use for twitter:title by first
	 * getting TWITTER_TITLE and falling back to TITLE.
	 */
	public String twitterTitles() {
		return get(TWITTER_TITLE, TITLE);
	}
	
	public String anyTwitterTitle() {
		return getAny(TWITTER_TITLE);
	}
	
	/**
	 * Gets best title to use for twitter:title by walking
	 * path until either a TWITTER_TITLE or TITLE is non-null.
	 */
	public String anyTwitterTitles() {
		return getAny(TWITTER_TITLE, TITLE);
	}
	
	public String twitterDescription() {
		return get(TWITTER_DESCRIPTION);
	}
	
	public String twitterDescriptions() {
		return get(TWITTER_DESCRIPTION, DESCRIPTION);
	}
	
	public String anyTwitterDescription() {
		return getAny(TWITTER_DESCRIPTION);
	}
	
	public String anyTwitterDescriptions() {
		return getAny(TWITTER_DESCRIPTION, DESCRIPTION);
	}
	
	public String openGraphTitle() {
		return get(OG_TITLE);
	}
	
	public String anyOpenGraphTitle() {
		return getAny(OG_TITLE);
	}
	
	public String openGraphDescription() {
		return get(OG_DESCRIPTION);
	}
	
	public String anyOpenGraphDescription() {
		return getAny(OG_DESCRIPTION);
	}
	
	public String openGraphImage() {
		return get(OG_IMAGE);
	}
	
	public String anyOpenGraphImage() {
		return getAny(OG_IMAGE);
	}
	
	public String openGraphSiteName() {
		return get(OG_SITE_NAME);
	}
	
	public String anyOpenGraphSiteName() {
		return getAny(OG_SITE_NAME);
	}
	
	public String openGraphType() {
		return get(OG_TYPE);
	}
	
	public String anyOpenGraphType() {
		return getAny(OG_TYPE);
	}
	
	/**
	 * Gets best title to use for og:title by walking
	 * path until either a TWITTER_TITLE or TITLE is non-null.
	 */
	public String openGraphTitles() {
		return get(OG_TITLE, TITLE);
	}
	
	public String anyOpenGraphTitles() {
		return getAny(OG_TITLE, TITLE);
	}
	
	/**
	 * Gets best title to use for og:title by walking
	 * path until either a TWITTER_TITLE or TITLE is non-null.
	 */
	public String openGraphDescriptions() {
		return get(OG_DESCRIPTION, DESCRIPTION);
	}
	
	public String anyOpenGraphDescriptions() {
		return getAny(OG_DESCRIPTION, DESCRIPTION);
	}
}
