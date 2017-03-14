package br.com.projeto.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.projeto.domain.AberturaEmail;
import br.com.projeto.util.HibernateUtil;

public class AberturaEmailDAO extends GenericDAO<AberturaEmail> {
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<AberturaEmail> pesquisarDepartamento(String departamento) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(AberturaEmail.class);

			@SuppressWarnings("unused")
			Criteria consultaDepartamento = consulta.createCriteria("departamento", "departamento", Criteria.INNER_JOIN,
					Restrictions.like("departamento.nome", "%" + departamento + "%"));

			List<AberturaEmail> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
