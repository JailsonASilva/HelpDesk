package br.com.projeto.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.projeto.domain.Artigo;
import br.com.projeto.util.HibernateUtil;

public class ArtigoDAO extends GenericDAO<Artigo> {

	@SuppressWarnings("unchecked")
	public List<Artigo> pesquisarPalavraChave(String palavraChave) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Artigo.class);
			consulta.add(Restrictions.like("palavraChave", "%" + palavraChave + "%"));
			consulta.addOrder(Order.asc("titulo"));

			List<Artigo> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
