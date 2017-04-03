package br.unipe.hotel.data;

import br.unipe.hotel.entity.Reserva;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ReservaData extends JpaRepository<Reserva, Long> {
	
	//	Spring Data JPA

	public List<Reserva> findByDataInicialAndDataFinalAndHospedeNomeAndQuartoNumero(Date dataInicial, Date dataFinal, String hospede, String quarto);
	
}
