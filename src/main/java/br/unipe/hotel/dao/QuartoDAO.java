package br.unipe.hotel.dao;

import org.springframework.stereotype.Repository;
import br.unipe.hotel.entity.Quarto;

@Repository
public class QuartoDAO extends AbstractDAO<Quarto>{
    
    @Override
    protected Class<Quarto> entityClass(){
        return Quarto.class;
    }
    
}
