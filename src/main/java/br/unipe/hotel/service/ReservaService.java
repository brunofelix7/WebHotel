package br.unipe.hotel.service;

import br.unipe.hotel.entity.Reserva;
import br.unipe.hotel.dao.ReservaDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ReservaService extends AbstractService<Reserva, ReservaDAO> {
    
	@Autowired
	private ReservaDAO reservaDAO;
	
    @Override
    protected ReservaDAO getDAO() {
        return reservaDAO;
    }
    
}
