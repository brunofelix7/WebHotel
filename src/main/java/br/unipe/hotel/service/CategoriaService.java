package br.unipe.hotel.service;

import br.unipe.hotel.entity.Categoria;
import br.unipe.hotel.dao.CategoriaDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CategoriaService extends AbstractService<Categoria, CategoriaDAO> {
    
	@Autowired
	private CategoriaDAO categoriaDAO;
	
    @Override
    protected CategoriaDAO getDAO() {
        return categoriaDAO;
    }
    
}
