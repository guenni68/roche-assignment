package com.roche.product.service.jpa.conf;

import com.roche.product.service.api.ProductService;
import com.roche.product.service.jpa.ProductServiceJpa;
import com.roche.product.service.jpa.entity.ProductJpa;
import com.roche.product.service.jpa.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackageClasses = ProductRepo.class)
@EntityScan(basePackageClasses = ProductJpa.class)
@PropertySource("application.properties")
public class ProductServiceJpaConf {

    @Bean
    public ProductService productService(ProductRepo productRepo) {
        return new ProductServiceJpa(productRepo);
    }

    @Bean
    public DataSource dataSource(@Value("${spring.datasource.driverClassName}") String driverClassName,
                                 @Value("${spring.datasource.url}") String dataSourceUrl,
                                 @Value("${spring.datasource.username}") String databaseUser,
                                 @Value("${spring.datasource.password}") String databasePassword) {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(dataSourceUrl);
        dataSource.setUsername(databaseUser);
        dataSource.setPassword(databasePassword);

        return dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.roche.product.service.jpa.entity");
        factory.setDataSource(dataSource);
        factory.afterPropertiesSet();

        return factory.getObject();
    }


    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }

}
