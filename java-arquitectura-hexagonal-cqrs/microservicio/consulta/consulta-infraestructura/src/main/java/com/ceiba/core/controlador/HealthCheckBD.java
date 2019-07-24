package com.ceiba.core.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/healthCheckBD") 
@Api(tags={"Controlador consulta de la base de datos"}) 
public class HealthCheckBD {
	
	
	private final ConsultarEstadoBD consultarEstadoBD;
	
	public HealthCheckBD(ConsultarEstadoBD consultar) {
		this.consultarEstadoBD=consultar;
	}
	
	@GetMapping
	@ApiOperation("estado de la base de datos")
	public  ResponseEntity<String> healthCheckBD() {
				
		String[] message= this.consultarEstadoBD.messageHelthCheckDB();
	
	return new ResponseEntity<String>(message[0], message[1].equals("200") ? HttpStatus.OK :  HttpStatus.SERVICE_UNAVAILABLE);
	
	}


}
