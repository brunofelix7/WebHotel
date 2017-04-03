package br.unipe.hotel.dao;

import org.springframework.stereotype.Repository;
import br.unipe.hotel.entity.Hospede;

@Repository
public class HospedeDAO extends AbstractDAO<Hospede>{
	
    @Override
    protected Class<Hospede> entityClass() {
        return Hospede.class;
    }

}
