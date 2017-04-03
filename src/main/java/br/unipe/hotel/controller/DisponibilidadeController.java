package br.unipe.hotel.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import br.unipe.hotel.data.QuartoData;
import br.unipe.hotel.entity.Quarto;
import br.unipe.hotel.service.QuartoService;

@Controller
@RequestMapping(value = "/disponibilidade")
public class DisponibilidadeController {

	private static final String LISTAR_VIEW = "/disponibilidade/ListarDisponibilidades";
	private static final String RESERVA_VIEW = "/reserva/ListarReservas";
	
	@Autowired
	private QuartoService quartoService;
	
	@Autowired
	private QuartoData quartoData;
	
	
	//	1. LISTAR
	@RequestMapping(method = RequestMethod.GET)
	public String listar(ModelMap modelMap) {
		List<Quarto> quartos = quartoService.listar();
		modelMap.addAttribute("quartos", quartos);
		return LISTAR_VIEW;
	}
	
	//	2. RESERVAR
	@RequestMapping(value = "/reservar", method=RequestMethod.GET)
	public ModelAndView reservar(){
		ModelAndView mv = new ModelAndView(RESERVA_VIEW);
		return mv;
	}
	
}
