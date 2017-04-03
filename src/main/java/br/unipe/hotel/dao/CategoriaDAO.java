package br.unipe.hotel.dao;

import org.springframework.stereotype.Repository;
import br.unipe.hotel.entity.Categoria;

@Repository
public class CategoriaDAO extends AbstractDAO<Categoria>{
	
	@Override
    protected Class<Categoria> entityClass(){
        return Categoria.class;
    }
    
}
