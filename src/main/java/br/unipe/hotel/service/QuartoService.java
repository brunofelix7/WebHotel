package br.unipe.hotel.service;

import br.unipe.hotel.entity.Quarto;
import br.unipe.hotel.dao.QuartoDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class QuartoService extends AbstractService<Quarto, QuartoDAO>{
    
	@Autowired
	private QuartoDAO quartoDAO;
	
    @Override
    protected QuartoDAO getDAO() {
        return quartoDAO;
    }
  
}
