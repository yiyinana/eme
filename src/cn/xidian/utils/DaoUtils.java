package cn.xidian.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class DaoUtils {

	private static SessionFactory sessionFactory;
	private static Session session;
	private static Transaction transaction;

	public static Session getSession() {
		// 1.创建SessionFactory对象
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		// 2.创建Session对象
		//session = sessionFactory.openSession();
		session = sessionFactory.getCurrentSession();
		transaction = session.beginTransaction();
		return session;
	}

	public static void close() {
		// 提交事务
		transaction.commit();

	}
//	public static String md5(String message) throws NoSuchAlgorithmException{
//		MessageDigest md = MessageDigest.getInstance("md5");
//		byte[] md5 = md.digest(message.getBytes());
//		BASE64Encoder encoder = new BASE64Encoder();
//		return encoder.encode(md5);
//	}
}
