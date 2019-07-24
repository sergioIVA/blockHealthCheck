package com.ceiba.core.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ceiba.core.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.core.repositorio.RepositorioUsuario;
import com.ceiba.core.repositorio.RepositorioUsuarioMysql;

@Configuration
public class BeanRepositorio {

	@Bean
	public RepositorioUsuario repositorioUsuario(CustomNamedParameterJdbcTemplate repositorioGenerico) {
		return new RepositorioUsuarioMysql(repositorioGenerico);
	}

}
