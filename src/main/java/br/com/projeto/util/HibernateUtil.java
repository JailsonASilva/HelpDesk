package br.com.projeto.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.jdbc.ReturningWork;

public class HibernateUtil {
	private static SessionFactory fabricaDeSessoes;

	public static SessionFactory getFabricaDeSessoes() {
		try {
			setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fabricaDeSessoes;
	}

	public static Connection getConexao() {
		Session sessao = fabricaDeSessoes.openSession();

		Connection conexao = sessao.doReturningWork(new ReturningWork<Connection>() {
			@Override
			public Connection execute(Connection conn) throws SQLException {
				return conn;
			}
		});

		return conexao;
	}

	protected static void setUp() throws Exception {
		// Uma SessionFactory é definida uma vez por aplicação
		final StandardServiceRegistry registro = new StandardServiceRegistryBuilder().configure() // configures
																									// settings
																									// from
																									// hibernate.cfg.xml
				.build();
		try {
			fabricaDeSessoes = new MetadataSources(registro).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			// O registro poderia ser destruido pela SessionFactory, mas
			// teriamos problemas na SessionFactory
			// então destruimos manualmente
			StandardServiceRegistryBuilder.destroy(registro);
		}
	}
}