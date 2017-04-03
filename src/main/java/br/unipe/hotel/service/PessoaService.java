package br.unipe.hotel.service;

import br.unipe.hotel.entity.Pessoa;
import br.unipe.hotel.dao.PessoaDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PessoaService extends AbstractService<Pessoa, PessoaDAO> {

	@Autowired
	private PessoaDAO pessoaDAO;
	
    @Override
    protected PessoaDAO getDAO() {
        return pessoaDAO;
    }
    
}
