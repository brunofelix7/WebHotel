package br.unipe.hotel.dao;

import org.springframework.stereotype.Repository;
import br.unipe.hotel.entity.Produto;

@Repository
public class ProdutoDAO extends AbstractDAO<Produto>{

    @Override
    protected Class<Produto> entityClass(){
        return Produto.class;
    }
    
}
