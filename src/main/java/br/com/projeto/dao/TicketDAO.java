package br.com.projeto.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.omnifaces.util.Faces;

import br.com.projeto.bean.AutenticacaoBean;
import br.com.projeto.domain.Ticket;
import br.com.projeto.domain.Usuario;
import br.com.projeto.util.HibernateUtil;

public class TicketDAO extends GenericDAO<Ticket> {

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Ticket> pesquisarDepartamento(String departamento, String status) {
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Ticket.class);

			@SuppressWarnings("unused")
			Criteria consultaDepartamento = consulta.createCriteria("departamento", "departamento", Criteria.INNER_JOIN,
					Restrictions.like("departamento.nome", "%" + departamento + "%"));

			consulta.add(Restrictions.eq("status", status));

			// consulta.addOrder(Order.asc("prioridade"));
			consulta.addOrder(Order.desc("dataAbertura"));
			consulta.addOrder(Order.desc("codigo"));

			List<Ticket> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Ticket> pesquisarDepartamento(String departamento) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Ticket.class);

			@SuppressWarnings("unused")
			Criteria consultaDepartamento = consulta.createCriteria("departamento", "departamento", Criteria.INNER_JOIN,
					Restrictions.like("departamento.nome", "%" + departamento + "%"));

			consulta.add(Restrictions.in("status", "Pendente", "Em Atendimento"));

			consulta.addOrder(Order.desc("dataAbertura"));
			consulta.addOrder(Order.desc("codigo"));

			List<Ticket> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Ticket> pesquisarAdministrador(String status) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Ticket.class);
			consulta.add(Restrictions.eq("status", status));

			consulta.addOrder(Order.desc("dataAbertura"));
			consulta.addOrder(Order.desc("codigo"));

			List<Ticket> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Ticket> pesquisarAdministrador() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Ticket.class);

			consulta.add(Restrictions.in("status", "Pendente", "Em Atendimento"));

			consulta.addOrder(Order.desc("dataAbertura"));
			consulta.addOrder(Order.desc("codigo"));

			List<Ticket> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings({ "deprecation", "unchecked", "unused" })
	public List<Ticket> pesquisaAvancado(String departamento, Long usuarioAbertura, String status, String prioridade,
			String assunto, String solicitacao, Long atendenteAbertura, Long ticket) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Ticket.class);

			Criteria consultaDepartamento = consulta.createCriteria("departamento", "departamento", Criteria.INNER_JOIN,
					Restrictions.like("departamento.nome", "%" + departamento + "%"));

			if (ticket > 0L) {
				consulta.add(Restrictions.eq("codigo", ticket));
			}

			if (usuarioAbertura > 0L) {
				Criteria consultaUsuario = consulta.createCriteria("usuario", "usuario", Criteria.INNER_JOIN,
						Restrictions.eq("usuario.codigo", usuarioAbertura));
			}

			if (atendenteAbertura > 0L) {
				Criteria consultaUsuario = consulta.createCriteria("usuarioAtendimento", "usuarioAtendimento",
						Criteria.INNER_JOIN, Restrictions.eq("usuarioAtendimento.codigo", atendenteAbertura));
			}

			if (status.equals("Aberto")) {
				consulta.add(Restrictions.in("status", "Pendente", "Em Atendimento"));
			} else {
				consulta.add(Restrictions.like("status", "%" + status + "%"));
			}

			consulta.add(Restrictions.like("prioridade", "%" + prioridade + "%"));
			consulta.add(Restrictions.like("assunto", "%" + assunto + "%"));
			consulta.add(Restrictions.like("solicitacao", "%" + solicitacao + "%"));

			consulta.addOrder(Order.desc("dataAbertura"));
			consulta.addOrder(Order.desc("codigo"));

			List<Ticket> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings({ "deprecation", "unchecked", "unused" })
	public List<Ticket> pesquisaAvancadoAdministrador(Long departamento, Long usuarioAbertura, String status,
			String prioridade, String assunto, String solicitacao, Long atendenteAbertura, Long ticket) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Ticket.class);

			if (ticket > 0L) {
				consulta.add(Restrictions.eq("codigo", ticket));
			}

			if (departamento > 0L) {
				Criteria consultaDepartamento = consulta.createCriteria("departamento", "departamento",
						Criteria.INNER_JOIN, Restrictions.eq("departamento.codigo", departamento));
			}

			if (usuarioAbertura > 0L) {
				Criteria consultaUsuario = consulta.createCriteria("usuario", "usuario", Criteria.INNER_JOIN,
						Restrictions.eq("usuario.codigo", usuarioAbertura));
			}

			if (atendenteAbertura > 0L) {
				Criteria consultaUsuario = consulta.createCriteria("usuarioAtendimento", "usuarioAtendimento",
						Criteria.INNER_JOIN, Restrictions.eq("usuarioAtendimento.codigo", atendenteAbertura));
			}

			if (status.equals("Aberto")) {
				consulta.add(Restrictions.in("status", "Pendente", "Em Atendimento"));
			} else {
				consulta.add(Restrictions.like("status", "%" + status + "%"));
			}

			consulta.add(Restrictions.like("prioridade", "%" + prioridade + "%"));
			consulta.add(Restrictions.like("assunto", "%" + assunto + "%"));
			consulta.add(Restrictions.like("solicitacao", "%" + solicitacao + "%"));

			consulta.addOrder(Order.desc("dataAbertura"));
			consulta.addOrder(Order.desc("codigo"));

			List<Ticket> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Ticket> pesquisarUsuario(String usuario, String status) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Ticket.class);

			@SuppressWarnings("unused")
			Criteria consultaUsuario = consulta.createCriteria("usuarioAtendimento", "usuarioAtendimento",
					Criteria.INNER_JOIN, Restrictions.like("usuarioAtendimento.nome", "%" + usuario + "%"));

			consulta.add(Restrictions.eq("status", status));

			// consulta.addOrder(Order.asc("prioridade"));
			consulta.addOrder(Order.desc("dataAbertura"));
			consulta.addOrder(Order.desc("codigo"));

			List<Ticket> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Ticket> pesquisarUsuario(String usuario) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Ticket.class);

			@SuppressWarnings("unused")
			Criteria consultaUsuario = consulta.createCriteria("usuarioAtendimento", "usuarioAtendimento",
					Criteria.INNER_JOIN, Restrictions.like("usuarioAtendimento.nome", "%" + usuario + "%"));

			consulta.add(Restrictions.in("status", "Pendente", "Em Atendimento"));

			// consulta.addOrder(Order.asc("prioridade"));
			consulta.addOrder(Order.desc("dataAbertura"));
			consulta.addOrder(Order.desc("codigo"));

			List<Ticket> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Ticket> pesquisarUsuarioAbertura(String usuario, String status) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Ticket.class);

			@SuppressWarnings("unused")
			Criteria consultaUsuario = consulta.createCriteria("usuario", "usuario", Criteria.INNER_JOIN,
					Restrictions.like("usuario.nome", "%" + usuario + "%"));

			consulta.add(Restrictions.eq("status", status));

			// consulta.addOrder(Order.asc("prioridade"));
			consulta.addOrder(Order.desc("dataAbertura"));
			consulta.addOrder(Order.desc("codigo"));

			List<Ticket> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Ticket> meusTickets(String usuario) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Ticket.class);

			@SuppressWarnings("unused")
			Criteria consultaDepartamento = consulta.createCriteria("usuario", "usuario", Criteria.INNER_JOIN,
					Restrictions.like("usuario.nome", "%" + usuario + "%"));

			consulta.add(Restrictions.in("status", "Pendente", "Em Atendimento"));

			consulta.addOrder(Order.desc("dataAbertura"));
			consulta.addOrder(Order.desc("codigo"));

			List<Ticket> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Ticket> ticketPendentes() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
			Usuario usuario = autenticacaoBean.getUsuarioLogado();

			Calendar data48 = Calendar.getInstance();
			data48.add(Calendar.DAY_OF_MONTH, -2);

			Calendar dataInicial = Calendar.getInstance();
			dataInicial.add(Calendar.DAY_OF_MONTH, -99999);

			Criteria consulta = sessao.createCriteria(Ticket.class);

			@SuppressWarnings("unused")
			Criteria consultaUsuario = consulta.createCriteria("usuarioAtendimento", "usuarioAtendimento",
					Criteria.INNER_JOIN, Restrictions.like("usuarioAtendimento.nome", "%" + usuario.getNome() + "%"));

			consulta.add(Restrictions.in("status", "Pendente", "Em Atendimento"));
			consulta.add(Restrictions.between("ultimaInteracao", dataInicial.getTime(), data48.getTime()));
			consulta.addOrder(Order.asc("codigo"));

			List<Ticket> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings({ "unchecked", "unused", "deprecation" })
	public List<Ticket> ticketSemAtendente() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
			Usuario usuario = autenticacaoBean.getUsuarioLogado();

			Criteria consulta = sessao.createCriteria(Ticket.class);

			Criteria consultaDepartamento = consulta.createCriteria("departamento", "departamento", Criteria.INNER_JOIN,
					Restrictions.like("departamento.nome", "%" + usuario.getDepartamento().getNome() + "%"));

			consulta.add(Restrictions.isNull("usuarioAtendimento"));
			consulta.addOrder(Order.desc("codigo"));

			List<Ticket> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("rawtypes")
	public List ticketPendentesDepartamento() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
			Usuario usuario = autenticacaoBean.getUsuarioLogado();

			Calendar data48 = Calendar.getInstance();
			data48.add(Calendar.DAY_OF_MONTH, -2);

			String dataFormatada = new SimpleDateFormat("yyyy/MM/dd").format(data48.getTime());

			dataFormatada = "'" + dataFormatada + "'";

			List tickets = sessao
					.createSQLQuery("SELECT * FROM vs_ticket_pendentes WHERE departamento_codigo = "
							+ usuario.getDepartamento().getCodigo() + " AND ultimaInteracao <= " + dataFormatada)
					.list();

			return tickets;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("rawtypes")
	public List resumoDepartamento() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
			Usuario usuario = autenticacaoBean.getUsuarioLogado();

			List tickets = sessao.createSQLQuery("SELECT * FROM vs_resumo_equipe WHERE Departamento_Codigo = "
					+ usuario.getDepartamento().getCodigo()).list();

			return tickets;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("rawtypes")
	public List resumoGeral() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {

			List tickets = sessao.createSQLQuery("SELECT * FROM vs_resumo_equipe").list();

			return tickets;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("rawtypes")
	public List aberturaDepartamento(Date dataInicial, Date dataFinal) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		String dataInicialFormatada = new SimpleDateFormat("yyyy/MM/dd").format(dataInicial);
		String dataFinalFormatada = new SimpleDateFormat("yyyy/MM/dd").format(dataFinal);

		try {
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
			Usuario usuario = autenticacaoBean.getUsuarioLogado();

			List tickets = sessao
					.createSQLQuery("CALL sp_abertura_departamento(" + usuario.getDepartamento().getCodigo() + ",'"
							+ dataInicialFormatada + "','" + dataFinalFormatada + "')")
					.list();

			return tickets;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("rawtypes")
	public List aberturaUsuario(Date dataInicial, Date dataFinal) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		String dataInicialFormatada = new SimpleDateFormat("yyyy/MM/dd").format(dataInicial);
		String dataFinalFormatada = new SimpleDateFormat("yyyy/MM/dd").format(dataFinal);

		try {
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
			Usuario usuario = autenticacaoBean.getUsuarioLogado();

			List tickets = sessao.createSQLQuery("CALL sp_abertura_usuario(" + usuario.getDepartamento().getCodigo()
					+ ",'" + dataInicialFormatada + "','" + dataFinalFormatada + "')").list();

			return tickets;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public void realizarEvento(Long ticket) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			sessao.getTransaction().begin();
			
			sessao.createSQLQuery("UPDATE evento SET realizado = true WHERE ticket_codigo = " + ticket.toString())
					.executeUpdate();

			sessao.getTransaction().commit();

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
