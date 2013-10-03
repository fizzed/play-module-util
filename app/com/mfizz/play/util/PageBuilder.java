package com.mfizz.play.util;

import static com.mfizz.play.util.Page.*;

import java.util.HashMap;
import java.util.Map;

public class PageBuilder extends BasePage {
	
	/**
	 * Creates a new page builder with a parent with no initial values.
	 * @param parent Parent page of this page
	 */
	public PageBuilder(Page parent) {
		super(parent, new HashMap<String,String>(), true);
	}
	
	/**
	 * Creates a new page builder with a parent and a copy of the initial
	 * values. 
	 * @param parent Parent page of this page
	 * @param values Values that will be copied
	 */
	protected PageBuilder(Page parent, Map<String,String> values) {
		super(parent, new HashMap<String,String>(values), true);
	}
	
	/**
	 * Builds an immutable view of the values.
	 */
	public Page build() {
		return new Page(this.parent, this.values);
	}
	
	public PageBuilder set(String key, String value) {
		this.values.put(key, value);
		return this;
	}
	
	/** standard properties **/
	
	public PageBuilder id(String id) {
		this.set(ID, id);
		return this;
	}
	
	public PageBuilder name(String name) {
		this.set(NAME, name);
		return this;
	}
	
	public PageBuilder url(String url) {
		this.set(URL, url);
		return this;
	}
	
	public PageBuilder brand(String brand) {
		this.set(BRAND, brand);
		return this;
	}
	
	public PageBuilder title(String title) {
		this.set(TITLE, title);
		return this;
	}
	
	public PageBuilder description(String description) {
		this.set(DESCRIPTION, description);
		return this;
	}
	
	public PageBuilder keywords(String keywords) {
		this.set(KEYWORDS, keywords);
		return this;
	}
	
	public PageBuilder author(String author) {
		this.set(AUTHOR, author);
		return this;
	}
	
	public PageBuilder sectionTitle(String title) {
		this.set(SECTION_TITLE, title);
		return this;
	}
	
	public PageBuilder breadcrumbTitle(String title) {
		this.set(BREADCRUMB_TITLE, title);
		return this;
	}
	
	public PageBuilder twitterCard(String card) {
		this.set(TWITTER_CARD, card);
		return this;
	}
	
	public PageBuilder twitterSite(String site) {
		this.set(TWITTER_SITE, site);
		return this;
	}
	
	public PageBuilder twitterCreator(String creator) {
		this.set(TWITTER_CREATOR, creator);
		return this;
	}
	
	public PageBuilder twitterTitle(String title) {
		this.set(TWITTER_TITLE, title);
		return this;
	}
	
	public PageBuilder twitterDescription(String description) {
		this.set(TWITTER_DESCRIPTION, description);
		return this;
	}
	
	public PageBuilder openGraphTitle(String title) {
		this.set(OG_TITLE, title);
		return this;
	}
	
	public PageBuilder openGraphDescription(String description) {
		this.set(OG_DESCRIPTION, description);
		return this;
	}
	
	public PageBuilder openGraphSiteName(String siteName) {
		this.set(OG_SITE_NAME, siteName);
		return this;
	}
	
	public PageBuilder openGraphImage(String image) {
		this.set(OG_IMAGE, image);
		return this;
	}
	
	public PageBuilder openGraphType(String type) {
		this.set(OG_TYPE, type);
		return this;
	}
}
