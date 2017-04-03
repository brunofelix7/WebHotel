package br.unipe.hotel.data;

import br.unipe.hotel.entity.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaData extends JpaRepository<Categoria, Long>{

	//	Spring Data JPA
	
	public List<Categoria> findByNomeContaining(String nome);
	
}
