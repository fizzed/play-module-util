package com.fizzed.play.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import com.fizzed.play.util.Page;
import com.fizzed.play.util.PageBuilder;

import static com.fizzed.play.util.Page.*;

public class PageTest {

	@Test 
    public void get() {
		Map<String,String> av = new HashMap<String,String>();
		av.put(Page.NAME, "home");
		av.put(Page.TITLE, "home title");
		Page a = new Page(null, av);
		
		Map<String,String> bv = new HashMap<String,String>();
		bv.put(Page.NAME, "contact");
		bv.put(Page.SECTION_TITLE, "contact");
		bv.put(Page.URL, "");			// set to empty value
		Page b = new Page(a, bv);
		
		Page c = new PageBuilder(b)
			.set(NAME, "form")
			.build();
		
		Page d = c.child().name("c-child").title("page d").build();
		
		Assert.assertTrue(b.has(Page.NAME));
		Assert.assertFalse(b.has(Page.TITLE));
		Assert.assertTrue(b.has(Page.URL));
		Assert.assertFalse(b.hasNonEmpty(Page.URL));
		Assert.assertTrue(b.hasAny(Page.TITLE));
		Assert.assertEquals("contact", b.get(Page.NAME));
		Assert.assertEquals("contact", b.getAny(Page.NAME));
		Assert.assertNull(b.get(Page.TITLE));
		// should get parent's title
		Assert.assertEquals("home title", b.getAny(Page.TITLE));
		
		// should still parent's title
		Assert.assertEquals("home title", c.getAny(Page.TITLE));
		Assert.assertFalse(c.hasAnyNonEmpty(Page.URL));
		Assert.assertTrue(c.hasAnyNonEmpty(Page.SECTION_TITLE));
		
		Assert.assertEquals("c-child", d.name());
		Assert.assertEquals("page d", d.title());
		Assert.assertEquals("contact", d.anySectionTitle());
		
		List<Page> path = d.path();
		Assert.assertSame(a, path.get(0));
		Assert.assertSame(b, path.get(1));
		Assert.assertSame(c, path.get(2));
		Assert.assertSame(d, path.get(3));
    }
	
}
