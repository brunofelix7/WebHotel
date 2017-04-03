package br.unipe.hotel.dao;

import org.springframework.stereotype.Repository;
import br.unipe.hotel.entity.Reserva;

@Repository
public class ReservaDAO extends AbstractDAO<Reserva>{
    
    @Override
    protected Class<Reserva> entityClass() {
        return Reserva.class;
    }
    
}
