package br.com.projeto.util;

import org.hibernate.Session;

public class teste {

	public static Long main(String[] args) {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Long nextVal = (Long) sessao.createQuery("SELECT next_val FROM hibernate_sequence").uniqueResult();

			System.out.println(nextVal);

			return nextVal;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}

	}

}
