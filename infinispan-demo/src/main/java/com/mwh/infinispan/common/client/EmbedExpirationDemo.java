package com.mwh.infinispan.common.client;

import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.infinispan.Cache;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.junit.Test;
/**
 * Embed测试用例
 * @author alei
 *
 */
public class EmbedExpirationDemo {

	@Test
	public void expirationLife() throws InterruptedException {
		Configuration configuration = new ConfigurationBuilder().expiration()
				.lifespan(2000, TimeUnit.MILLISECONDS).build();
		DefaultCacheManager defaultCacheManager = new DefaultCacheManager(
				configuration);
		defaultCacheManager.start();
		Cache<String, String> cache = defaultCacheManager.getCache();
		cache.put("key", "value");
		Assert.assertTrue("value".equals(cache.get("key")));
		Thread.sleep(2000);
		Assert.assertTrue(cache.get("key") == null);
		defaultCacheManager.stop();
	}

	@Test
	public void expirationIdel() throws InterruptedException {
		Configuration configuration = new ConfigurationBuilder().expiration()
				.maxIdle(1000, TimeUnit.MILLISECONDS).build();
		DefaultCacheManager defaultCacheManager = new DefaultCacheManager(
				configuration);
		defaultCacheManager.start();
		Cache<String, String> cache = defaultCacheManager.getCache();
		cache.put("key", "value");
		Assert.assertTrue("value".equals(cache.get("key")));
		Thread.sleep(1000);
		Assert.assertTrue(cache.get("key") == null);
		defaultCacheManager.stop();
	}

	@Test
	public void expirationlifeAndIdle() throws InterruptedException {
		Configuration configuration = new ConfigurationBuilder().expiration()
				.lifespan(3000, TimeUnit.MILLISECONDS)
				.maxIdle(1000, TimeUnit.MILLISECONDS)
				.wakeUpInterval(5000, TimeUnit.MILLISECONDS).build();
		DefaultCacheManager defaultCacheManager = new DefaultCacheManager(
				configuration);
		defaultCacheManager.start();
		Cache<String, String> cache = defaultCacheManager.getCache();
		cache.put("key", "value");
		Assert.assertTrue("value".equals(cache.get("key")));
		Thread.sleep(1000);
		Assert.assertTrue(cache.get("key") == null);
		defaultCacheManager.stop();
	}

	@Test
	public void expirationInterval() throws InterruptedException {
		Configuration configuration = new ConfigurationBuilder().expiration()
				.lifespan(3000, TimeUnit.MILLISECONDS)
				.maxIdle(1000, TimeUnit.MILLISECONDS)
				.wakeUpInterval(4000, TimeUnit.MILLISECONDS).build();
		DefaultCacheManager defaultCacheManager = new DefaultCacheManager(
				configuration);
		defaultCacheManager.start();
		Cache<String, String> cache = defaultCacheManager.getCache();
		cache.put("key", "value");
		Assert.assertTrue(cache.getAdvancedCache().getDataContainer()
				.peek("key").getValue().equals("value"));
		Thread.sleep(5000);
		Assert.assertTrue(cache.getAdvancedCache().getDataContainer()
				.peek("key") == null);
		defaultCacheManager.stop();
	}
 
	
}
