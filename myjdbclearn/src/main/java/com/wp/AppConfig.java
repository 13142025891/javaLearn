package com.wp;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@PropertySource("jdbc.properties")
public class AppConfig {

    @Value("${jdbc.url}")
    String url;

    @Value("${jdbc.name}")
    String name;

    @Value("${jdbc.pass}")
    String pass;


    @Bean
    JdbcTemplate createJdbcTemplate(@Autowired DataSource dataSource){
        return  new JdbcTemplate(dataSource);
    }

    @Bean
    DataSource createDataSource(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(name);
        hikariConfig.setPassword(pass);
        hikariConfig.addDataSourceProperty("autoCommit", "true");
        hikariConfig.addDataSourceProperty("connectionTimeout", "5");
        hikariConfig.addDataSourceProperty("idleTimeout", "60");
        return new HikariDataSource(hikariConfig);
    }


}
