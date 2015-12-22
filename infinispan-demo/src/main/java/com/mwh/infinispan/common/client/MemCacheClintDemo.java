package com.mwh.infinispan.common.client;

import java.io.IOException;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/**
 * memcache 测试用例
 * @author alei
 *
 */
public class MemCacheClintDemo {
	@Before
	public void startInfinispanServer(){
		System.out.println("step1: http://downloads.jboss.org/infinispan/8.1.0.Final/infinispan-server-8.1.0.Final-bin.zip");
		System.out.println("step2: decompression infinispan-server-8.1.0.Final-bin.zip");
		System.out.println("step3: start nfinispan-server" );
		System.out.println("infinispan server was started successfully！");
	}
	@Test
	public void testClient() throws InterruptedException, IOException {
		MemcachedClient mc = new MemcachedClient(
				AddrUtil.getAddresses("127.0.0.1:11311"));
		mc.set("key1", 1, "value1");
		Assert.assertTrue("value1".equals(mc.get("key1").toString()));
		Thread.sleep(1000);
		Assert.assertTrue(mc.get("key1") == null);
	}
}
