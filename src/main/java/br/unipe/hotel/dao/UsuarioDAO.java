package br.unipe.hotel.dao;

import org.springframework.stereotype.Repository;
import br.unipe.hotel.entity.Usuario;

@Repository
public class UsuarioDAO extends AbstractDAO<Usuario>{
    
    @Override
    protected Class<Usuario> entityClass() {
        return Usuario.class;
    }  
    
}
