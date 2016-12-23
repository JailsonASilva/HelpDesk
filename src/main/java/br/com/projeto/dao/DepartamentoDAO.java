package br.com.projeto.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.projeto.domain.Departamento;
import br.com.projeto.util.HibernateUtil;

public class DepartamentoDAO extends GenericDAO<Departamento> {
	
	@SuppressWarnings("unchecked")
	public List<Departamento> listarAtendimento() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Departamento.class);
			consulta.add(Restrictions.eq("atendimento", true));
			consulta.addOrder(Order.asc("nome"));
			
			List<Departamento> resultado = consulta.list();			
			return resultado;
			
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}	
	
}
