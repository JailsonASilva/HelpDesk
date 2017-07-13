package br.com.projeto.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.omnifaces.util.Faces;

import br.com.projeto.bean.AutenticacaoBean;
import br.com.projeto.domain.ProgramacaoFerias;
import br.com.projeto.domain.Usuario;
import br.com.projeto.util.HibernateUtil;

public class ProgramacaoFeriasDAO extends GenericDAO<ProgramacaoFerias> {

	@SuppressWarnings({ "deprecation", "unchecked", "unused" })
	public List<ProgramacaoFerias> pesquisarColaborador(String colaborador) {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(ProgramacaoFerias.class);

			Criteria consultaColaborador = consulta.createCriteria("colaborador", "colaborador", Criteria.INNER_JOIN,
					Restrictions.like("colaborador.nome", "%" + colaborador + "%"));

			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
			Usuario usuario = autenticacaoBean.getUsuarioLogado();

			Criteria consultaUsuario = consulta.createCriteria("usuario", "usuario", Criteria.INNER_JOIN);

			Criteria consultaDepartamento = consultaUsuario.createCriteria("departamento", "departamento", Criteria.INNER_JOIN,
					Restrictions.eq("departamento.codigo", usuario.getDepartamento().getCodigo()));

			consulta.addOrder(Order.desc("codigo"));

			List<ProgramacaoFerias> resultado = consulta.list();

			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings({ "deprecation", "unchecked", "unused" })
	public Boolean duplicidade(Long colaborador) {
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(ProgramacaoFerias.class);

			Criteria consultaColaborador = consulta.createCriteria("colaborador", "colaborador", Criteria.INNER_JOIN,
					Restrictions.eq("colaborador.codigo", colaborador));		

			List<ProgramacaoFerias> resultado = consulta.list();
			
		return resultado.isEmpty();	

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
