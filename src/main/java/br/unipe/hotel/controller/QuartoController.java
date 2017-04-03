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

import br.unipe.hotel.data.QuartoData;
import br.unipe.hotel.entity.Categoria;
import br.unipe.hotel.entity.Quarto;
import br.unipe.hotel.service.CategoriaService;
import br.unipe.hotel.service.QuartoService;

@Controller
@RequestMapping(value = "/quartos")
public class QuartoController {
	
	private static final String CADASTRAR_VIEW = "/quarto/CadastrarQuarto";
	private static final String ATUALIZAR_VIEW = "/quarto/AtualizarQuarto";
	private static final String LISTAR_VIEW = "/quarto/ListarQuartos";

	@Autowired
	private QuartoService quartoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private QuartoData quartoData;

	
	//	1. REDIRECIONA PARA PÁGINA DE CADASTRO
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo(ModelMap modelMap) {
		ModelAndView mv = new ModelAndView(CADASTRAR_VIEW);
		Quarto quarto = new Quarto();
		List<Categoria> categorias = categoriaService.listar();
		mv.addObject("quarto", quarto);
		mv.addObject("categorias", categorias);
		return mv;
	}
	
	//	2. SALVAR
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute(value = "categoria") @Validated Categoria categoria, Errors errorsCategoria, @ModelAttribute(value = "quarto") @Validated Quarto quarto, Errors errors){
		ModelAndView mv = new ModelAndView(CADASTRAR_VIEW);
		List<Categoria> categorias = categoriaService.listar();
		mv.addObject("categorias", categorias);
		mv.addObject("quarto", quarto);
		try{
			if(errors.hasErrors() || errorsCategoria.hasErrors()){
				return mv;
			}else{
				quarto.setCategoria(categoria);
				quartoService.salvar(quarto);
				mv.addObject("mensagem", "Salvo com sucesso!");
			}
		}catch(SystemException e){
			e.printStackTrace();
		}
		return mv;
	}
	
	//	3. ATUALIZAR
	@RequestMapping(value = "/alterar", method = RequestMethod.POST)
	public ModelAndView salvarAlteracoes(@ModelAttribute(value = "quarto") @Validated Quarto quarto, Errors errors){
		ModelAndView mv = new ModelAndView(ATUALIZAR_VIEW);
		List<Categoria> categorias = categoriaService.listar();
		mv.addObject("categorias", categorias);
		mv.addObject("quarto", quarto);
		try{
			if(errors.hasErrors()){
				return mv;
			}else{
				quartoService.atualizar(quarto);
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
		List<Categoria> categorias = categoriaService.listar();
		Quarto quarto = quartoService.buscarPorId(id);
		mv.addObject("categorias", categorias);
		mv.addObject("quarto", quarto);
		return mv;
	}
	
	//	5. EXCLUIR
	@RequestMapping(value = "/excluir/{id}", method=RequestMethod.GET)
	public String excluir(@PathVariable(value="id") Long id, ModelMap modelMap){
		try{
			Quarto quarto = quartoService.buscarPorId(id);
			quartoService.remover(quarto);
		}catch (SystemException e) {
			e.printStackTrace();
		}
		return "redirect:/quartos";
	}
	
	//	6. LISTAR
	@RequestMapping(method = RequestMethod.GET)
	public String listar(ModelMap modelMap) {
		List<Quarto> quartos = quartoService.listar();
		List<Categoria> categorias = categoriaService.listar();
		modelMap.addAttribute("quartos", quartos);
		modelMap.addAttribute("categorias", categorias);
		return LISTAR_VIEW;
	}
	
	//	7. PESQUISA
	@RequestMapping(value = "/filtrar", method = RequestMethod.GET)
	public String pesquisar(@ModelAttribute(value="numero") String numero, @ModelAttribute(value="categoria") String categoria, ModelMap modelMap){
		List<Categoria> categorias = categoriaService.listar();
		List<Quarto> quartos = quartoData.findByNumeroAndCategoriaNome(numero, categoria);
		modelMap.addAttribute("quartos", quartos);
		modelMap.addAttribute("categorias", categorias);
		return LISTAR_VIEW;
	}
	
}
