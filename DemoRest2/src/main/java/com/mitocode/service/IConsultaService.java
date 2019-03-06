package com.mitocode.service;

import com.mitocode.model.Consulta;
import java.util.Map;


public interface IConsultaService {
	
	public Map<String, String> crear(Consulta cons);
//	public Consulta modificar(Consulta cons);
	public String listar();
//	public Consulta listarPorId(Integer id);
//	public void eliminar(Integer id);
}
