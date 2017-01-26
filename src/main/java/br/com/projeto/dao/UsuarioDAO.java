package br.com.projeto.dao;

import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.projeto.domain.Usuario;
import br.com.projeto.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario> {

	public Usuario autenticar(String email, String senha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);

			consulta.add(Restrictions.eq("email", email));

			SimpleHash hash = new SimpleHash("md5", senha);
			consulta.add(Restrictions.eq("senha", hash.toHex()));

			Usuario resultado = (Usuario) consulta.uniqueResult();

			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings({ "unchecked", "deprecation", "unused" })
	public List<Usuario> listarUsuarios(Long departamento) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);

			Criteria consultaDepartamento = consulta.createCriteria("departamento", "departamento", Criteria.INNER_JOIN,
					Restrictions.like("departamento.codigo", departamento));

			List<Usuario> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings({ "unchecked", "deprecation", "unused" })
	public List<Usuario> pesquisarUsuarioDepartamento(Long departamento, String usuario) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.add(Restrictions.like("nome", "%" + usuario + "%"));

			Criteria consultaDepartamento = consulta.createCriteria("departamento", "departamento", Criteria.INNER_JOIN,
					Restrictions.like("departamento.codigo", departamento));

			List<Usuario> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings({ "unchecked", "deprecation", "unused" })
	public List<Usuario> pesquisarUsuarioDepartamento(Long departamento) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);			

			Criteria consultaDepartamento = consulta.createCriteria("departamento", "departamento", Criteria.INNER_JOIN,
					Restrictions.like("departamento.codigo", departamento));
			
			consulta.addOrder(Order.asc("nome"));

			List<Usuario> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}	
}
