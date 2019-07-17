package com.nielsen.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class HibernateConfig {
	
		@Autowired
	    Environment env;
	
		@Bean
		public ComboPooledDataSource comboPooledDataSource() throws PropertyVetoException {
			ComboPooledDataSource cpds = new ComboPooledDataSource();
			cpds.setDriverClass(env.getProperty("db.driver"));
			cpds.setJdbcUrl(env.getProperty("db.url") + TimeZone.getDefault().getID());
			cpds.setUser(env.getProperty("db.user"));
			cpds.setPassword(env.getProperty("db.password"));
			return cpds;	
		}
		
		@Bean
		public LocalSessionFactoryBean sessionFactory() throws PropertyVetoException {
			LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
			sessionFactory.setDataSource(comboPooledDataSource());
			sessionFactory.setPackagesToScan("com.nielsen");
			sessionFactory.setHibernateProperties(hibernateProperties());
			
			return sessionFactory;
		}
		
		private final Properties hibernateProperties() {
	        Properties hibernateProperties = new Properties();
	        hibernateProperties.setProperty("hibernate.show_sql", "true");
	        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
	 
	        return hibernateProperties;
	    }
		
		@Bean
	    public PlatformTransactionManager hibernateTransactionManager() throws PropertyVetoException {
	        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	        transactionManager.setSessionFactory(sessionFactory().getObject());
	        return transactionManager;
	    }
}
