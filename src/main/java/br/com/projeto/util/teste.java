package br.com.projeto.util;

import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.Gson;

public class teste {

	public static void main(String args[]) throws Exception {

		URL url = new URL("https://cdrapi:cdrapiFACEMA@192.168.0.100:8443/cdrapi");
		InputStreamReader reader = new InputStreamReader(url.openStream());
		
		//MyDto dto = new Gson().fromJson(reader, MyDto.class);

		// using the deserialized object
		//System.out.println(dto.src);
		//System.out.println(dto.dst);

	}

	private class MyDto {
		String src;
		String dst;
	}
}