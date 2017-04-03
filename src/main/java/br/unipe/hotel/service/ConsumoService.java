package br.unipe.hotel.service;

import br.unipe.hotel.entity.Consumo;
import br.unipe.hotel.dao.ConsumoDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ConsumoService extends AbstractService<Consumo, ConsumoDAO>{
    
	@Autowired
	private ConsumoDAO consumoDAO;
	
    @Override
    protected ConsumoDAO getDAO() {
        return consumoDAO;
    }
    
}
