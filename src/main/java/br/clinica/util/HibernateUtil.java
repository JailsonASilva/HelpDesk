package br.clinica.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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