package com.mitocode.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.dao.IConsultaDAO;
import com.mitocode.model.Consulta;
import com.mitocode.model.ConsultaImp;

@Service
public class ConsultaServiceImpl implements IConsultaService {

	
	@Autowired
	private IConsultaDAO dao;
			
	@Override
	public Map<String, String> crear(Consulta cons) {
		ConsultaImp imp = new ConsultaImp();
    	dao.save(cons);
    	return imp.consultar(cons.isLatino(), cons.getText(), cons.getSortby(), cons.isNews(), cons.isBlogs(), cons.isDiscussions(), cons.isReviews(), cons.getLanguage(), cons.getCountry(), cons.getLocation(), cons.getCategory(), cons.getDate());
	}

	@Override
	public String listar() {
		return "listo";
	}

}
