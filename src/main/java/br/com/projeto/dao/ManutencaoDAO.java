package br.com.projeto.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.projeto.domain.Manutencao;
import br.com.projeto.util.HibernateUtil;

public class ManutencaoDAO extends GenericDAO<Manutencao> {
	@SuppressWarnings({ "unchecked", "unused", "deprecation" })
	public List<Manutencao> pesquisarEquipamento(Date DataInicio, Date DataFinal, String tipo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Manutencao.class);
			consulta.add(Restrictions.between("dataManutencao", DataInicio, DataFinal));

			Criteria consultaEquipamento = consulta.createCriteria("equipamento", "equipamento", Criteria.INNER_JOIN);

			Criteria consultaTipo = consultaEquipamento.createCriteria("tipoEquipamento", "tipoEquipamento",
					Criteria.INNER_JOIN, Restrictions.eq("tipoEquipamento.nome", tipo));

			consulta.addOrder(Order.asc("tipoEquipamento.nome"));

			List<Manutencao> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
