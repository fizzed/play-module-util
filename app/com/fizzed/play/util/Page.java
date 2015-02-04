package com.fizzed.play.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Each page has the following elements:
 * 
 *  id: unique identifier (e.g. useful for including in a search indexing)
 *  url: url of the page
 *  title: head->title  (optimal length for seo ~70 chars)
 *  description: head->meta-description (optimal length for seo ~155 chars)
 *  keywords: head->meta-keywords (not important to include)
 *  
 *  sectionTitle: usually displayed as the section of the site we're in
 *  breadCrumbTitle: usually displayed as part of a bread crumb trail (optimal length 30 chars)
 *  
 *  https://dev.twitter.com/docs/cards/types/summary-card
 *  only important to display potentially to "Twitterbot" user-agent
 *  
 *  twitter:card: should be set to a value of "summary"
 *  twitter:site: e.g @nytimes
 *  twitter:creator: e.g. @SarahMaslinNir
 *  twitter:title: title should be concise and will be truncated at 70 characters
 *  twitter:description: a description that concisely summarizes the content of the page,
 *  			as appropriate for presentation within a Tweet. Do not re-use the title
 *  			text as the description, or use this field to describe the general services
 *  			provided by the website. Description text will be truncated at the word to
 *  			200 characters.
 * 
 * @author joelauer
 *
 */
public class Page extends BasePage {
	
	// helpful lengths for SEO
	public static final int SEO_TITLE_LENGTH = 70;
	public static final int SEO_DESCRIPTION_LENGTH = 160;
	public static final int SEO_TWITTER_TITLE_LENGTH = 70;
	public static final int SEO_TWITTER_DESCRIPTION_LENGTH = 200;
	
	// standard properties
	public static final String ID = "id";
	public static final String URL = "url";
	public static final String NAME = "name";
	public static final String BRAND = "brand";
	public static final String TITLE = "title";
	public static final String DESCRIPTION = "description";
	public static final String KEYWORDS = "keywords";
	public static final String AUTHOR = "author";
	public static final String SECTION_TITLE = "section-title";
	public static final String BREADCRUMB_TITLE = "breadcrumb-title";
	public static final String SECTION_URL = "section-url";
	public static final String BREADCRUMB_URL = "breadcrumb-url";
	
	// twitter card properties
	public static final String TWITTER_CARD = "twitter:card";
	public static final String TWITTER_SITE = "twitter:site";
	public static final String TWITTER_CREATOR = "twitter:creator";
	public static final String TWITTER_TITLE = "twitter:title";
	public static final String TWITTER_DESCRIPTION = "twitter:description";
	
	// open graph properties
	// by facebook https://developers.facebook.com/docs/opengraph/
	// https://developers.facebook.com/docs/opengraph/creating-object-types/
	// https://developers.facebook.com/tools/debug
	public static final String OG_TITLE = "og:title";
	public static final String OG_IMAGE = "og:image";
	public static final String OG_SITE_NAME = "og:site_name";
	public static final String OG_TYPE = "og:type";
	public static final String OG_DESCRIPTION = "og:description";
	public static final String OG_ARTICLE_TAG = "og:article:tag";
	public static final String OG_URL = "og:url";

	/**
	 * Creates a copy of the provided values and then ensures that it becomes
	 * unmodifiable. Only public way to construct a page.
	 * @param parent Parent page of this page
	 * @param values Values that will be copied into this page
	 */
	public Page(Page parent, Map<String,String> values) {
		super(parent, new HashMap<String,String>(values), false);
	}
	
	public PageBuilder modify() {
		return new PageBuilder(this.parent, this.values);
	}
	
	
	public PageBuilder child() {
		return new PageBuilder(this, this.values);
	}
	
	public List<Page> path() {
		LinkedList<Page> pages = new LinkedList<Page>();
		for (Page p = this; p != null; ) {
			pages.addFirst(p);
			p = p.parent();
		}
		return pages;
	}
	
	public List<Page> parents() {
		LinkedList<Page> pages = new LinkedList<Page>();
		for (Page p = this.parent; p != null; ) {
			pages.addFirst(p);
			p = p.parent();
		}
		return pages;
	}
	
}
