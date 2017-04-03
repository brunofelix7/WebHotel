package br.unipe.hotel.service;

import br.unipe.hotel.entity.Hospede;
import br.unipe.hotel.dao.HospedeDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class HospedeService extends AbstractService<Hospede, HospedeDAO>{
	
	@Autowired
	private HospedeDAO hospedeDAO;
	
	@Override
    protected HospedeDAO getDAO() {
        return hospedeDAO;
    }
    
}
