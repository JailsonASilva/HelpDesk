package br.com.projeto.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.omnifaces.util.Faces;

import br.com.projeto.bean.AutenticacaoBean;
import br.com.projeto.domain.Fornecedor;
import br.com.projeto.domain.Rpa;
import br.com.projeto.domain.Usuario;
import br.com.projeto.util.HibernateUtil;

public class RpaDAO extends GenericDAO<Rpa> {

	@SuppressWarnings({ "deprecation", "unchecked", "unused" })
	public List<Rpa> pesquisarRpa(String nome) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Rpa.class);

			Criteria consultaColaborador = consulta.createCriteria("fornecedor", "fornecedor", Criteria.INNER_JOIN,
					Restrictions.like("fornecedor.nome", "%" + nome + "%"));

			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
			Usuario usuario = autenticacaoBean.getUsuarioLogado();

			Criteria consultaUsuario = consulta.createCriteria("usuario", "usuario", Criteria.INNER_JOIN,
					Restrictions.eq("usuario.codigo", usuario.getCodigo()));

			consulta.addOrder(Order.desc("codigo"));

			List<Rpa> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings({ "deprecation", "unchecked", "unused" })
	public List<Rpa> buscaAvancada(Date dataInicial, Date dataFinal, Fornecedor fornecedor, String referente) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Rpa.class);

			if (fornecedor != null) {
				Criteria consultaColaborador = consulta.createCriteria("fornecedor", "fornecedor", Criteria.INNER_JOIN,
						Restrictions.like("fornecedor.nome", "%" + fornecedor.getNome() + "%"));
			}

			consulta.add(Restrictions.like("referente", "%" + referente + "%"));
			consulta.add(Restrictions.between("dataInicio", dataInicial, dataFinal));

			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
			Usuario usuario = autenticacaoBean.getUsuarioLogado();

			Criteria consultaUsuario = consulta.createCriteria("usuario", "usuario", Criteria.INNER_JOIN,
					Restrictions.eq("usuario.codigo", usuario.getCodigo()));

			consulta.addOrder(Order.desc("codigo"));

			List<Rpa> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings({ "unchecked" })
	public List<Rpa> ListarPendentes(Boolean pendente) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Rpa.class);

			if (pendente == true) {
				consulta.add(Restrictions.eq("pendente", pendente));
			}

			consulta.addOrder(Order.desc("codigo"));

			List<Rpa> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
