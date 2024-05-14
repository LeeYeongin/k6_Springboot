package edu.pnu.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.pnu.dao.LogDao;
import edu.pnu.dao.MemberDao;

@Configuration
public class ResourceConfig {

	private final DataSource dataSource;

    public ResourceConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberDao memberDao() {
        return new MemberDao(dataSource);
    }
    
    @Bean
    public LogDao logDao() {
        return new LogDao(dataSource);
    }
}
