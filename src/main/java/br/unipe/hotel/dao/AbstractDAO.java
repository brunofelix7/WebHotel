package br.unipe.hotel.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import br.unipe.hotel.entity.AbstractEntity;

public abstract class AbstractDAO<T extends AbstractEntity> {
    
	@PersistenceContext
    protected EntityManager entityManager;
   
    protected abstract Class<T> entityClass();
    
    public void salvar(T entity){
        entityManager.persist(entity);
    }
    
    public void atualizar(T entity){
        entityManager.merge(entity);
    }
    
    public void remover(T entity){
        entity = entityManager.find(entityClass(), entity.getId());
        entityManager.remove(entity);
    }
    
    public T buscarPorId(Long id) {
        return entityManager.find(entityClass(), id);
    }
    
    @SuppressWarnings("unchecked")
    public List<T> listar(){
        Query query = entityManager.createQuery("select a from "+entityClass().getSimpleName()+" a");
        List<T> lista = query.getResultList();
        return lista;
    }
 

}
