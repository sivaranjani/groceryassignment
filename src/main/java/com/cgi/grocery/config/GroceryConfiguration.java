/**
 * 
 */
package com.cgi.grocery.config;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author vranj
 *
 */
@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.cgi.grocery.repository" })
@ComponentScan("com.cgi.grocery")
@PropertySource("classpath:application.properties")
public class GroceryConfiguration {
	
	private static final String[] ENTITYMANAGER_PACKAGES_TO_SCAN = { "com.cgi.grocery.entity" };
	
	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {

		String username = env.getProperty("spring.datasource.username");
		String password = env.getProperty("spring.datasource.password");
		String driverClass = env.getProperty("spring.datasource.driver-class-name");
		String url = env.getProperty("spring.datasource.url");

		return DataSourceBuilder.create().username(username).password(password).url(url).driverClassName(driverClass)
				.build();
	}

	@Bean(name="transactionManager")
	public JpaTransactionManager jpaTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);

		return entityManagerFactoryBean;
	}
}
