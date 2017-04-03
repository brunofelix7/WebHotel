package br.unipe.hotel.service;

import java.util.List;
import br.unipe.hotel.dao.AbstractDAO;
import br.unipe.hotel.entity.AbstractEntity;
import javax.transaction.SystemException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class AbstractService<T extends AbstractEntity, D extends AbstractDAO<T>> {

    protected abstract D getDAO();

    
    //	1. SALVAR
    public void salvar(T entity) throws SystemException{
		try{
			D dao = getDAO();
			dao.salvar(entity);
		}
		catch(Exception e){
			throw new SystemException("Erro ao salvar");
		}
	}

    //	2. REMOVER
    public void remover(T entity) throws SystemException{
		try{
			if(entity.getId() == null || entity.getId() == 0){
				throw new SystemException("Id é obrigatório");
			}
			D dao = getDAO();
			entity = dao.buscarPorId(entity.getId());
			dao.remover(entity);
		}
		catch(Exception e){
			throw new SystemException("Erro ao salvar");
		}
	}
    
    //	3. ATUALIZAR
    public void atualizar(T entity) throws SystemException{
		try{
			D dao = getDAO();
			dao.atualizar(entity);
		}
		catch(Exception e){
			throw new SystemException("Erro ao atualizar");
		}
	}

    //	4. LISTAR
    public List<T> listar(){
		return getDAO().listar();
	}
    
    //	5. BUSCAR POR ID
    public T buscarPorId(Long id){
		return getDAO().buscarPorId(id);
	}
    
}
