package db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class MyHibernateSessionFactory {

	private static SessionFactory sessionFactory;

	// 构造方法私有化，保证单例模式
	private MyHibernateSessionFactory() {

	}

	// 公有的静态方法，获取会话工厂对象
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			Configuration config = new Configuration().configure();
			ServiceRegistry serviceRigistry = new ServiceRegistryBuilder()
					.applySettings(config.getProperties())
					.buildServiceRegistry();
			sessionFactory = config.buildSessionFactory(serviceRigistry);
			return sessionFactory;
		} else {
			return sessionFactory;
		}
	}
}
