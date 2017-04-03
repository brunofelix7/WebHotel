package br.unipe.hotel.service;

import br.unipe.hotel.entity.Usuario;
import br.unipe.hotel.dao.UsuarioDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UsuarioService extends AbstractService<Usuario, UsuarioDAO> {
    
	@Autowired
	private UsuarioDAO usuarioDAO;
	
    @Override
    protected UsuarioDAO getDAO() {
        return usuarioDAO;
    }
    
}
