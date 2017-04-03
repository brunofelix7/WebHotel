package br.unipe.hotel.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import br.unipe.hotel.data.ReservaData;
import br.unipe.hotel.entity.Hospede;
import br.unipe.hotel.entity.Quarto;
import br.unipe.hotel.entity.Reserva;
import br.unipe.hotel.repository.EnumPagamento;
import br.unipe.hotel.repository.EnumStatus;
import br.unipe.hotel.service.HospedeService;
import br.unipe.hotel.service.QuartoService;
import br.unipe.hotel.service.ReservaService;

@Controller
@RequestMapping(value = "/reservas")
public class ReservaController {

	private static final String CADASTRAR_VIEW = "/reserva/CadastrarReserva";
	private static final String ATUALIZAR_VIEW = "/reserva/AtualizarReserva";
	private static final String VISUALIZAR_VIEW = "/reserva/VisualizarReserva";
	private static final String PAGAMENTO_VIEW = "/reserva/EfetuarPagamentoReserva";
	private static final String LISTAR_VIEW = "/reserva/ListarReservas";
	
	@Autowired
	private ReservaService reservaService;
	
	@Autowired
	private HospedeService hospedeService;
	
	@Autowired
	private QuartoService quartoService;
	
	@Autowired
	private ReservaData reservaData;
	
	
	//	PERMITE A ENTRADA DE DATAS NOS INPUTS E FORMATA ANTES DE ENVIAR PARA O BANCO
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	//	1. REDIRECIONA PARA PÁGINA DE CADASTRO
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo(ModelMap modelMap) {
		ModelAndView mv = new ModelAndView(CADASTRAR_VIEW);
		Reserva reserva = new Reserva();
		List<Quarto> quartos = quartoService.listar();
		List<Hospede> hospedes = hospedeService.listar();
		mv.addObject("reserva", reserva);
		mv.addObject("quartos", quartos);
		mv.addObject("hospedes", hospedes);
		return mv;
	}
	
	//	2. SALVAR
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute(value = "reserva") @Validated Reserva reserva, Errors errors){
		ModelAndView mv = new ModelAndView(CADASTRAR_VIEW);
		List<Quarto> quartos = quartoService.listar();
		List<Hospede> hospedes = hospedeService.listar();
		mv.addObject("reserva", reserva);
		mv.addObject("quartos", quartos);
		mv.addObject("hospedes", hospedes);
		try{
			if(errors.hasErrors()){
				return mv;
			}else{
				reserva.setStatus(EnumStatus.PENDENTE);
				reserva.setPago("NÃO");
				reservaService.salvar(reserva);
				mv.addObject("mensagem", "Salvo com sucesso!");
			}
		}catch(SystemException e){
			e.printStackTrace();
		}
		return mv;
	}
	
	//	3. ATUALIZAR
	@RequestMapping(value = "/alterar", method = RequestMethod.POST)
	public ModelAndView salvarAlteracoes(@ModelAttribute(value = "reserva") @Validated Reserva reserva, Errors errors){
		ModelAndView mv = new ModelAndView(ATUALIZAR_VIEW);
		List<Quarto> quartos = quartoService.listar();
		List<Hospede> hospedes = hospedeService.listar();
		mv.addObject("reserva", reserva);
		mv.addObject("quartos", quartos);
		mv.addObject("hospedes", hospedes);
		try{
			if(errors.hasErrors()){
				return mv;
			}else{
				reservaService.atualizar(reserva);
				mv.addObject("mensagem", "Dados atualizados com sucesso!");
			}
		}catch(SystemException e){
			e.printStackTrace();
		}
		return mv;
	}
	
	//	4. REDIRECIONA PARA PÁGINA DE ATUALIZAÇÃO
	@RequestMapping(value = "/atualizar/{id}", method=RequestMethod.GET)
	public ModelAndView atualizar(@PathVariable(value="id") Long id){
		ModelAndView mv = new ModelAndView(ATUALIZAR_VIEW);
		List<Quarto> quartos = quartoService.listar();
		List<Hospede> hospedes = hospedeService.listar();
		Reserva reserva = reservaService.buscarPorId(id);
		mv.addObject("reserva", reserva);
		mv.addObject("quartos", quartos);
		mv.addObject("hospedes", hospedes);
		return mv;
	}
	
	//	5. EXCLUIR
	@RequestMapping(value = "/excluir/{id}", method=RequestMethod.GET)
	public String excluir(@PathVariable(value="id") Long id, ModelMap modelMap){
		try{
			Reserva reserva = reservaService.buscarPorId(id);
			reservaService.remover(reserva);
		}catch (SystemException e) {
			e.printStackTrace();
		}
		return "redirect:/reservas";
	}
	
	//	6. LISTAR
	@RequestMapping(method = RequestMethod.GET)
	public String listar(ModelMap modelMap) {
		List<Reserva> reservas = reservaService.listar();
		List<Hospede> hospedes = hospedeService.listar();
		List<Quarto> quartos = quartoService.listar();
		modelMap.addAttribute("reservas", reservas);
		modelMap.addAttribute("quartos", quartos);
		modelMap.addAttribute("hospedes", hospedes);
		return LISTAR_VIEW;
	}
	
	//	7. VISUALIZAR
	@RequestMapping(value = "/visualizar/{id}", method=RequestMethod.GET)
	public String visualizar(@PathVariable(value="id") Long id, ModelMap modelMap){
		Reserva reserva = reservaService.buscarPorId(id);
		modelMap.addAttribute("reserva", reserva);
		return VISUALIZAR_VIEW;
	}	
	
	//	8. CHECKIN
	@RequestMapping(value = "/checkin/{id}", method=RequestMethod.GET)
	public String checkin(@PathVariable(value="id") Long id, ModelMap modelMap) throws SystemException{
		Reserva reserva = reservaService.buscarPorId(id);
		reserva.setDataCheckIn(new Date());
		reservaService.atualizar(reserva);
		modelMap.addAttribute("reserva", reserva);
		return "redirect:/reservas?checkin";
	}	
	
	//	9. CHECKOUT
	@RequestMapping(value = "/checkout/{id}", method=RequestMethod.GET)
	public String checkout(@PathVariable(value="id") Long id, ModelMap modelMap) throws SystemException{
		Reserva reserva = reservaService.buscarPorId(id);
		reserva.setDataCheckOut(new Date());
		reservaService.atualizar(reserva);
		modelMap.addAttribute("reserva", reserva);
		return "redirect:/reservas?checkout";
	}	
	
	//	10. EFETUAR PAGAMENTO
	@RequestMapping(value = "/pagamento/{id}", method=RequestMethod.GET)
	public ModelAndView teste(@PathVariable(value="id") Long id){
		ModelAndView mv = new ModelAndView(PAGAMENTO_VIEW);
		Reserva reserva = reservaService.buscarPorId(id);
		mv.addObject("reserva", reserva);
		return mv;
	}
	
	//	11. PAGAR (NÃO FUNCIONA)
	@RequestMapping(value = "/pagar", method = RequestMethod.POST)
	public ModelAndView pagar(@ModelAttribute(value = "reserva") Reserva reserva) throws SystemException{
		ModelAndView mv = new ModelAndView(PAGAMENTO_VIEW);
		reserva.setPago("SIM");
		reserva.setStatus(EnumStatus.RESERVADO);
		reservaService.atualizar(reserva);
		mv.addObject("mensagem", "Pagamento realizado com sucesso!");
		return mv;
	}
	
	//	12. PESQUISA
	@RequestMapping(value = "/filtrar", method = RequestMethod.GET)
	public String pesquisar(@ModelAttribute(value="dataInicial") Date dataInicial, @ModelAttribute(value="dataFinal") Date dataFinal, @ModelAttribute(value="hospede") String hospede, @ModelAttribute(value="quarto") String quarto, ModelMap modelMap){
		List<Hospede> hospedes = hospedeService.listar();
		List<Quarto> quartos = quartoService.listar();
		List<Reserva> reservas = reservaData.findByDataInicialAndDataFinalAndHospedeNomeAndQuartoNumero(dataInicial, dataFinal, hospede, quarto);
		modelMap.addAttribute("quartos", quartos);
		modelMap.addAttribute("hospedes", hospedes);
		modelMap.addAttribute("reservas", reservas);
		return LISTAR_VIEW;
	}
	
	//	RETORNA TODOS OS ENUNS EM UM ARRAY DINAMICAMENTE 
	@ModelAttribute("enumPagamento")		// Define o nome que eu quero chamar lá na View com o thymeleaf
	public List<EnumPagamento> enumPagamento(){
		return Arrays.asList(EnumPagamento.values());
	}
	
	//	RETORNA TODOS OS ENUNS EM UM ARRAY DINAMICAMENTE 
	@ModelAttribute("enumStatus")		// Define o nome que eu quero chamar lá na View com o thymeleaf
	public List<EnumStatus> todosStatusTitulo(){
		return Arrays.asList(EnumStatus.values());
	}
	
}
