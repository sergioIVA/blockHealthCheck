package com.ceiba.core.repositorio;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.ceiba.core.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.core.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.core.modelo.usuario.Usuario;

@Repository
public class RepositorioUsuarioMysql implements RepositorioUsuario {

	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
	
	@SqlStatement(namespace="usuario", value="crear")
	private String sqlCrear;
	
	@SqlStatement(namespace="usuario", value="actualizar")
	private String sqlActualizar;
	
	@SqlStatement(namespace="usuario", value="eliminar")
	private String sqlEliminar;
	
	@SqlStatement(namespace="usuario", value="existe")
	private String sqlExiste;
	
	@SqlStatement(namespace="usuario", value="existeExcluyendoId") private String sqlExisteExcluyendoId;
	
	public RepositorioUsuarioMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
		this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
	}

	@Override
	public Long crear(Usuario usuario) {
		return this.customNamedParameterJdbcTemplate.crear(usuario, this.sqlCrear);
	}

    @Override
    public void eliminar(Long id) {
    	MapSqlParameterSource paramSource = new MapSqlParameterSource();
	    paramSource.addValue("id", id);
	    
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
    }

	@Override
	public boolean existe(String nombre) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
	    paramSource.addValue("nombre", nombre);
	    
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste,paramSource, Boolean.class);
	}

	@Override
	public void actualizar(Usuario usuario) {
		this.customNamedParameterJdbcTemplate.actualizar(usuario, this.sqlActualizar);
	}

	@Override
	public boolean existeExcluyendoId(Long id, String nombre) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", id);
	    paramSource.addValue("nombre", nombre);
	    
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExisteExcluyendoId,paramSource, Boolean.class);
	}
}
