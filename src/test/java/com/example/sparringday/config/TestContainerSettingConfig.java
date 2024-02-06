package com.example.sparringday.config;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

import com.example.sparringday.SparringDayApplication;

@TestConfiguration(proxyBeanMethods = false)
public class TestContainerSettingConfig {
	@Bean
	@ServiceConnection
	MySQLContainer<?> mysqlContainer() {
		MySQLContainer<?> container = new MySQLContainer<>(DockerImageName.parse("mysql:latest"));
		return container;
	}

	@Bean
	public DataSourceInitializer initializer(DataSource dataSource) {
		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(dataSource);
		dataSourceInitializer.setDatabasePopulator(
			new ResourceDatabasePopulator(new ClassPathResource("./Total DB Schema.sql")));

		return dataSourceInitializer;
	}

	public static void main(String[] args) {
		SpringApplication.from(SparringDayApplication::main).with(TestContainerSettingConfig.class).run(args);
	}

}