package br.unipe.hotel.dao;

import org.springframework.stereotype.Repository;
import br.unipe.hotel.entity.Consumo;

@Repository
public class ConsumoDAO extends AbstractDAO<Consumo>{
    
    @Override
    protected Class<Consumo> entityClass() {
        return Consumo.class;
    }
    
}
