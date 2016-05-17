package br.clinica.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.clinica.domain.PessoaFisica;
import br.clinica.util.HibernateUtil;

public class PessoaFisicaDAO extends GenericDAO<PessoaFisica> {

	public PessoaFisica dadosPessoaFisica(Long codigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(PessoaFisica.class);
			consulta.add(Restrictions.eq("pessoa.codigo", codigo));

			PessoaFisica resultado = (PessoaFisica) consulta.uniqueResult();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
