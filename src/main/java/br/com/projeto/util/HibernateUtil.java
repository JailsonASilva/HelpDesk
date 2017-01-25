package br.com.projeto.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private static final SessionFactory fabricaDeSessoes = setUp();
	   
    private static SessionFactory setUp() {
        final StandardServiceRegistry registro = new StandardServiceRegistryBuilder().configure().build();
        try {
            return new MetadataSources(registro).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registro);
            throw new ExceptionInInitializerError("Não foi possível iniciar o hibernate");
        }
    }
 
    public static SessionFactory getFabricaDeSessoes() {
        return fabricaDeSessoes;
    }
}