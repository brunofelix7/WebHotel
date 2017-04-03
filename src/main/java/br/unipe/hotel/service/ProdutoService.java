package br.unipe.hotel.service;

import br.unipe.hotel.entity.Produto;
import br.unipe.hotel.dao.ProdutoDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ProdutoService extends AbstractService<Produto, ProdutoDAO>{
    
	@Autowired
	private ProdutoDAO produtoDAO;
	
    @Override
    protected ProdutoDAO getDAO() {
        return produtoDAO;
    }
    
}
