package com.ceiba.core.controlador;

import javax.sql.DataSource;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ceiba.core.infraestructura.actuator.DataSourceHealthIndicator;

@Configuration
public class ConsultarEstadoBD {

	
	 private final  DataSourceHealthIndicator dataSourceHealthIndicator;
	 private final DataSource dataSource;
	 private final static String CONSULTA_PRUEBA="SELECT 1";
	 
	 
	
	public ConsultarEstadoBD(DataSourceHealthIndicator dataSourceHealthIndicator,DataSource dataSource) {
		this.dataSourceHealthIndicator = dataSourceHealthIndicator;
		this.dataSource=dataSource;
	}


	public String[] messageHelthCheckDB(){
		
		String[] message=new String[2];
		
		try {
			
			new JdbcTemplate(this.dataSource).execute(CONSULTA_PRUEBA);
			dataSourceHealthIndicator.setDataSource(dataSource);
			dataSourceHealthIndicator.setQuery(CONSULTA_PRUEBA);
			Health health = dataSourceHealthIndicator.health();
			
			
			if(health.getStatus().equals(Status.UP)) {
				message[0]="La base de datos esta respondiendo  correctamente";
				message[1]="200";
			}
		
			}catch(Exception e) {
				
			  message[0]="La base de datos no esta respondiendo";
			  message[1]="503";  
			}
		
		return message;

	}
}
