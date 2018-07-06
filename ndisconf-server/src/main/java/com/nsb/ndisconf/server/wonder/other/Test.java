package com.nsb.ndisconf.server.wonder.other;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.nsb.ndisconf.server.disconf.web.service.data.bo.DataSql;

public class Test {
	CookieStore cookieStore = new BasicCookieStore();
	CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
	static String domain = "http://192.168.10.135";

	public static void main(String[] args) throws Exception {
		Test t=new Test();
		t.session();
		
		t.applist();
		
		t.login("admin", "admin");
		
		t.session();
		
		t.applist();
		
		t.signout();
		
		DisconfRemoteBizAppApi api=new DisconfRemoteBizAppApi(domain);
		
		api.session();
		
		api.login("admin", "admin");
		
		api.session();
		
		HttpGet get = new HttpGet(domain + "/api/app/list");
		
		CloseableHttpResponse response = api.httpClient.execute(get);

		HttpEntity responseEntity = response.getEntity();

		String res = EntityUtils.toString(responseEntity, "UTF-8");

		System.out.println("\nlist:\n\t" + res);

		EntityUtils.consume(responseEntity);

		response.close();
		
		api.signout();
		
		
	}

	public boolean login(String name, String passwd) {
		try {

			HttpPost httpPost = new HttpPost(domain + "/api/account/signin");

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("name", name));
			nvps.add(new BasicNameValuePair("password", passwd));
			nvps.add(new BasicNameValuePair("remember", "0"));

			httpPost.setEntity(new UrlEncodedFormEntity(nvps));

			CloseableHttpResponse response = httpClient.execute(httpPost);

			HttpEntity responseEntity = response.getEntity();

			String res = EntityUtils.toString(responseEntity, "UTF-8");

			System.out.println("\nlogin:\n\t" + res);

			EntityUtils.consume(responseEntity);

			response.close();

			if (res.contains("true")) {
				return true;
			}

		} catch (Exception e) {
			return false;
		}
		return false;
		//
	}

	public boolean session() {
		try {
			HttpGet httpGet = new HttpGet(domain + "/api/account/session");

			CloseableHttpResponse response = httpClient.execute(httpGet);

			HttpEntity responseEntity = response.getEntity();

			String res = EntityUtils.toString(responseEntity, "UTF-8");

			System.out.println("\nsession:\n\t" + res);

			EntityUtils.consume(responseEntity);

			response.close();

			if (res.contains("true")) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public boolean applist() {
		try {
			HttpGet httpGet = new HttpGet(domain + "/api/app/list");

			CloseableHttpResponse response = httpClient.execute(httpGet);

			HttpEntity responseEntity = response.getEntity();

			String res = EntityUtils.toString(responseEntity, "UTF-8");

			System.out.println("\nlist:\n\t" + res);

			EntityUtils.consume(responseEntity);

			response.close();

			if (res.contains("true")) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
	public void signout() {
		if (session()) {
			try {
				HttpGet httpGet = new HttpGet(domain + "/api/account/signout");

				CloseableHttpResponse response = httpClient.execute(httpGet);

				HttpEntity responseEntity = response.getEntity();

				String res = EntityUtils.toString(responseEntity, "UTF-8");

				System.out.println("\nsignout:\n\t" + res);

				EntityUtils.consume(responseEntity);

				response.close();

			} catch (Exception e) {

			}
		}

	}

	public void close() {
		try {
			signout();

			httpClient.close();
		} catch (Exception e) {

		}
	}

	public static void write() throws Exception {
		List<DataSql> list = new ArrayList<>();
		DataSql e = new DataSql();
		e.setInsertSql("insert into tab(id,name) values(1,'app')");
		e.setUpdateSql("update tab set name='haha' where id=1");
		list.add(e);
		//
		FileOutputStream out = new FileOutputStream("test");
		ObjectOutputStream objout = new ObjectOutputStream(out);
		objout.writeObject(list);
		objout.flush();
		objout.close();
		out.close();
		//
	}

	public static void read() throws Exception {
		FileInputStream ins = new FileInputStream("test");
		ObjectInputStream objins = new ObjectInputStream(ins);
		Object obj = objins.readObject();
		System.out.println("--->obj:\n\t" + obj);
		objins.close();
		ins.close();
		//
	}

}
