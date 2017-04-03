package br.unipe.hotel.data;

import br.unipe.hotel.entity.Hospede;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospedeData extends JpaRepository<Hospede, Long>{
	
	//	Spring Data JPA
	
	public List<Hospede> findByNomeAndCpf(String nome, String cpf);
	
}
