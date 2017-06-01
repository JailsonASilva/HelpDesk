package br.com.projeto.util;

import java.net.InetAddress;

public class teste {

	public static void main(String args[]) throws Exception {
		
		

		try {

			System.out.println(InetAddress.getLocalHost().getHostAddress());

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			
		}

	}
}