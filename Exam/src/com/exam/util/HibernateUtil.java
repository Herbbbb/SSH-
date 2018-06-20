package com.exam.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/*
 * Hibernate工厂类
 */
public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
//	获取静态工厂类
	private static SessionFactory buildSessionFactory(){
		//读取配置信息
		Configuration configuration = new Configuration().configure();
		//服务注册类
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}
	
//	获取session工厂    提供对外的借口，以便访问。
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	//测试类
	public static void main(String[] args){
		HibernateUtil.getSessionFactory();
	}
}
