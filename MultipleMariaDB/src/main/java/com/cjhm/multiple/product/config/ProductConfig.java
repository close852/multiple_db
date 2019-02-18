package com.cjhm.multiple.product.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
@Configuration
@EnableJpaRepositories(
	basePackages ="com.cjhm.multiple.product.dao"
	,entityManagerFactoryRef="productEntityManager"
	,transactionManagerRef ="productTransactionManager"
)
public class ProductConfig {
	
	@Autowired
	private Environment env;
	private static final String NAME="product";
	private static final String PREFIX="db.second.";
	private static final String packageScan="com.cjhm.multiple.product.model";

	@Bean(name=NAME+"EntityManager")
	public LocalContainerEntityManagerFactoryBean entityManager(){
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] {packageScan});
		
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty(PREFIX+"hbm2ddl.auto"));
		properties.put("hibernate.dialect", env.getProperty(PREFIX+"hibernate.dialect"));
		em.setJpaPropertyMap(properties);
		
		return em;
	}

	@Bean(name=NAME+"DataSource")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty(PREFIX+"driverClassName"));
		dataSource.setUrl(env.getProperty(PREFIX+"url"));
		dataSource.setUsername(env.getProperty(PREFIX+"username"));
		dataSource.setPassword(env.getProperty(PREFIX+"password"));
		return dataSource;
	}
	@Bean(name=NAME+"TransactionManager")
	public PlatformTransactionManager transactionManager(){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManager().getObject());
		
		return transactionManager;
	}


}
