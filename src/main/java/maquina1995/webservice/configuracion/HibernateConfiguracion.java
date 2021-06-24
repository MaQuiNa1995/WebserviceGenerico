package maquina1995.webservice.configuracion;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import com.zaxxer.hikari.HikariDataSource;

@SpringBootConfiguration
public class HibernateConfiguracion {

	@Value(value = "${database.user}")
	private String user;
	@Value(value = "${database.password}")
	private String pass;

	@Bean
	public DataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource();

		dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
		dataSource.setJdbcUrl("jdbc:hsqldb:mem:maquina1995");
		dataSource.setUsername(user);
		dataSource.setPassword(pass);

		dataSource.setMaximumPoolSize(5);
		dataSource.setPoolName("MaQuiNa1995-HikariCP");

		dataSource.addDataSourceProperty("dataSource.cachePrepStmts", "true");
		dataSource.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
		dataSource.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
		dataSource.addDataSourceProperty("dataSource.useServerPrepStmts", "true");

		return dataSource;
	}

}
