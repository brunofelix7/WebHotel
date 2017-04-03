package br.unipe.hotel.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.unipe.hotel.entity.Quarto;

public interface QuartoData extends JpaRepository<Quarto, Long>{

	//	Spring Data JPA
	
	public List<Quarto> findByNumeroAndCategoriaNome(String numero, String categoria);
	
}
