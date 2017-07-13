package br.com.projeto.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.projeto.domain.Colaborador;
import br.com.projeto.util.HibernateUtil;

public class ColaboradorDAO extends GenericDAO<Colaborador> {

	
	@SuppressWarnings({ "unchecked", "deprecation", "unused" })
	public List<Colaborador> listarColaboradores(Long departamento) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Colaborador.class);

			Criteria consultaDepartamento = consulta.createCriteria("departamento", "departamento", Criteria.INNER_JOIN,
					Restrictions.like("departamento.codigo", departamento));

			List<Colaborador> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
}
