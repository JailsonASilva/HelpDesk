package br.com.projeto.util;

import org.hibernate.Session;

public class teste {

	public static void main(String args[]) throws Exception {
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {

			System.out.println((Long) sessao
					.createSQLQuery("SELECT LAST_INSERT_ID(codigo) FROM pessoa ORDER BY codigo DESC LIMIT 1")
					.uniqueResult());

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}

	}
}