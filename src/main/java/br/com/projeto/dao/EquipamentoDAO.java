package br.com.projeto.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.projeto.domain.Equipamento;
import br.com.projeto.util.HibernateUtil;

public class EquipamentoDAO extends GenericDAO<Equipamento> {

	@SuppressWarnings({ "unchecked", "unused", "deprecation" })
	public List<Equipamento> pesquisarEquipamento(String nome) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Equipamento.class);
			
			Criteria consultaTipoEquipamento = consulta.createCriteria("tipoEquipamento", "tipoEquipamento",
					Criteria.INNER_JOIN, Restrictions.like("tipoEquipamento.nome", "%" + nome + "%"));

			consulta.addOrder(Order.asc("tipoEquipamento.nome"));

			List<Equipamento> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		} 
	}
	
	@SuppressWarnings({ "unchecked"})	
	public List<Equipamento> pesquisarEquipamentoCodigo(Long codigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Equipamento.class);
			consulta.add(Restrictions.eq("codigo", codigo));

			List<Equipamento> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}	

}
