package com.mwh.infinispan.common.client;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Before;
import org.junit.Test;
/**
 * WebSocket client 测试用例
 * @author alei
 *
 */
public class WebSocketDemo {
	@Before
	public void startInfinispanServer(){
		System.out.println("step1: http://downloads.jboss.org/infinispan/8.1.0.Final/infinispan-server-8.1.0.Final-bin.zip");
		System.out.println("step2: decompression infinispan-server-8.1.0.Final-bin.zip");
		System.out.println("step3: start nfinispan-server" );
		System.out.println("infinispan server was started successfully！");
	}
	@Test
	public void testClient() throws Exception {
		// var cache = new Cache("omCache", "ws://ws.acmews.com:8181");
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine se = sem.getEngineByName("ecmascript");
		se.eval("alert('----------')");
		FileReader reader = new FileReader("D:\\eclipse-workplace\\Test\\src\\main\\java\\infinispan-ws.js");
		se.eval(reader);
		se.eval("var cache = new Cache('default', 'ws://localhost:8281');");
		se.eval("cache.put('key','value');");
		se.eval("print(cache.get('key'));");		
	}
	
}
