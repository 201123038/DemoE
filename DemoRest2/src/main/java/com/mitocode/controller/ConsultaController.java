package com.mitocode.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.model.Consulta;
import com.mitocode.service.IConsultaService;
@RestController
@RequestMapping("/consultar")

public class ConsultaController {

	@Autowired	
	private IConsultaService service;

	@GetMapping
	public String listar(){
		return service.listar();
	}
	
	@PostMapping
	public Map<String, String> registrar(@RequestBody Consulta per) {
		return service.crear(per);
	}

}
