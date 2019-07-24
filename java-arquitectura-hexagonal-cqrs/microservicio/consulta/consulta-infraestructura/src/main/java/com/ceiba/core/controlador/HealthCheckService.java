package com.ceiba.core.controlador;

import org.springframework.http.ResponseEntity;


public interface  HealthCheckService {
		
	 abstract public String[] healthCheckBD();
	 abstract public  String[] healthCheckRabbit();
	

}
