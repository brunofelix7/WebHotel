package br.unipe.hotel.dao;

import org.springframework.stereotype.Repository;
import br.unipe.hotel.entity.Pessoa;

@Repository
public class PessoaDAO extends AbstractDAO<Pessoa>{
    
    @Override
    protected Class<Pessoa> entityClass() {
        return Pessoa.class;
    }
 
}
