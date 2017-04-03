package br.unipe.hotel.controller;

import java.util.List;
import javax.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import br.unipe.hotel.data.HospedeData;
import br.unipe.hotel.entity.Endereco;
import br.unipe.hotel.entity.Hospede;
import br.unipe.hotel.service.HospedeService;

@Controller
@RequestMapping(value = "/hospedes")
public class HospedeController {
	
	private static final String CADASTRAR_VIEW = "/hospede/CadastrarHospede";
	private static final String ATUALIZAR_VIEW = "/hospede/AtualizarHospede";
	private static final String VISUALIZAR_VIEW = "/hospede/VisualizarHospede";
	private static final String LISTAR_VIEW = "/hospede/ListarHospedes";
	
	@Autowired
	private HospedeService hospedeService;
	
	@Autowired
	private HospedeData hospedeData;
	

	//	1. REDIRECIONA PARA PÁGINA DE CADASTRO
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo(ModelMap modelMap) {
		ModelAndView mv = new ModelAndView(CADASTRAR_VIEW);
		Hospede hospede = new Hospede();
		Endereco endereco = new Endereco();
		mv.addObject("hospede", hospede);
		mv.addObject("endereco", endereco);
		return mv;
	}
	
	//	2. SALVAR
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute(value = "endereco") @Validated Endereco endereco, Errors errorsEndereco, @ModelAttribute(value = "hospede") @Validated Hospede hospede, Errors errors, ModelMap modelMap){
		ModelAndView mv = new ModelAndView(CADASTRAR_VIEW);
		modelMap.addAttribute("hospede", hospede);
		try{
			if(errors.hasErrors() || errorsEndereco.hasErrors()){
				return mv;
			}else{
				hospede.setEndereco(endereco);
				hospedeService.salvar(hospede);
				mv.addObject("mensagem", "Salvo com sucesso!");
			}
		}catch(SystemException e){
			e.printStackTrace();
		}
		return mv;
	}
	
	//	3. ATUALIZAR
	@RequestMapping(value = "/alterar", method = RequestMethod.POST)
	public ModelAndView salvarAlteracoes(@ModelAttribute(value = "endereco") @Validated Endereco endereco, Errors errorsEndereco, @ModelAttribute(value = "hospede") @Validated Hospede hospede, Errors errors, ModelMap modelMap){
		ModelAndView mv = new ModelAndView(ATUALIZAR_VIEW);
		modelMap.addAttribute("hospede", hospede);
		try{
			if(errors.hasErrors() || errorsEndereco.hasErrors()){
				return mv;
			}else{
				hospede.setEndereco(endereco);
				hospedeService.atualizar(hospede);
				mv.addObject("mensagem", "Dados atualizados com sucesso!");
			}
		}catch(SystemException e){
			e.printStackTrace();
		}
		return mv;
	}
	
	//	4. REDIRECIONA PARA PÁGINA DE ATUALIZAÇÃO
	@RequestMapping(value = "/atualizar/{id}", method=RequestMethod.GET)
	public String atualizar(@PathVariable(value="id") Long id, ModelMap modelMap){
		Hospede hospede = hospedeService.buscarPorId(id);
		modelMap.addAttribute("hospede", hospede);
		return ATUALIZAR_VIEW;
	}
	
	//	5. EXCLUIR
	@RequestMapping(value = "/excluir/{id}", method=RequestMethod.GET)
	public String excluir(@PathVariable(value="id") Long id, ModelMap modelMap){
		try{
			Hospede hospede = hospedeService.buscarPorId(id);
			hospedeService.remover(hospede);
		}catch (SystemException e) {
			e.printStackTrace();
		}
		return "redirect:/hospedes";
	}
	
	//	6. LISTAR
	@RequestMapping(method = RequestMethod.GET)
	public String listar(ModelMap modelMap) {
		List<Hospede> hospedes = hospedeService.listar();
		modelMap.addAttribute("hospedes", hospedes);
		return LISTAR_VIEW;
	}
	
	//	7. VISUALIZAR
	@RequestMapping(value = "/visualizar/{id}", method=RequestMethod.GET)
	public String visualizar(@PathVariable(value="id") Long id, ModelMap modelMap){
		Hospede hospede = hospedeService.buscarPorId(id);
		modelMap.addAttribute("hospede", hospede);
		return VISUALIZAR_VIEW;
	}	
	
	//	8. PESQUISA
	@RequestMapping(value = "/filtrar", method = RequestMethod.GET)
	public String pesquisar(@ModelAttribute(value="nome") String nome, @ModelAttribute(value="cpf") String cpf, ModelMap modelMap){
		List<Hospede> hospedes = hospedeData.findByNomeAndCpf(nome, cpf);
		modelMap.addAttribute("hospedes", hospedes);
		return LISTAR_VIEW;
	}
	
	
}
