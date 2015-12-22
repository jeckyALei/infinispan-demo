package com.mwh.infinispan.common.client;

import java.util.concurrent.TimeUnit;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.Configuration;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/**
 * hotrod client 测试用例
 * @author alei
 *
 */
public class HotrodClintDemo  {
	@Before
	public void startInfinispanServer(){
		System.out.println("step1: http://downloads.jboss.org/infinispan/8.1.0.Final/infinispan-server-8.1.0.Final-bin.zip");
		System.out.println("step2: decompression infinispan-server-8.1.0.Final-bin.zip");
		System.out.println("step3: start nfinispan-server" );
		System.out.println("infinispan server was started successfully！");
	}
	@Test
	public void testClient() throws InterruptedException {
		Configuration cfb=new ConfigurationBuilder().addServers("localhost:11322").build();
		RemoteCacheManager rcm=new RemoteCacheManager(cfb);
		RemoteCache<String,String> rc= rcm.getCache();
		rc.put("key1","value1",1000,TimeUnit.MILLISECONDS);
		Assert.assertTrue("value1".equals(rc.get("key1")));
		Thread.sleep(1000);
		Assert.assertTrue(rc.get("key1") == null);
	}
}
