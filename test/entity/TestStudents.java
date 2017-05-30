package entity;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

public class TestStudents {

	@Test
	public void testSchemaExport() {
		// 创建配置对象
		Configuration config = new Configuration().configure();
		// 创建SchemaExport对象
		SchemaExport export = new SchemaExport(config);
		export.create(true, true);// 第一个参数表示生成表结构，第二个参数表示输出sql语句
	}

	// 添加测试数据
	@Test
	public void testSaveStudents() {
		// 创建配置对象
		Configuration config = new Configuration().configure();
		// 创建注册服务对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(config.getProperties()).buildServiceRegistry();
		// 创建SessionFactory
		SessionFactory sessionFactory = config
				.buildSessionFactory(serviceRegistry);
		// 创建session对象
		Session session = sessionFactory.getCurrentSession();
		// 创建事务对象
		Transaction ts = session.beginTransaction();
		Students s1 = new Students("S0000001", "张三丰", "男", new Date(), "武当山");
		Students s2 = new Students("S0000002", "郭靖", "男", new Date(), "桃花岛");
		Students s3 = new Students("S0000003", "黄蓉", "女", new Date(), "桃花岛");
		session.save(s1);
		session.save(s2);
		session.save(s3);
		ts.commit();
		sessionFactory.close();
	}

}
