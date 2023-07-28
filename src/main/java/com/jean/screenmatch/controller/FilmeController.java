package com.jean.screenmatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jean.screenmatch.domain.filme.DadosCadastroFilme;
import com.jean.screenmatch.domain.filme.Filme;
import com.jean.screenmatch.domain.filme.FilmeRepository;
	
@Controller
@RequestMapping("/filmes")
public class FilmeController {
	
	@Autowired	
	private FilmeRepository reposiroty;

	@GetMapping("/formulario")
	public String carregaPaginaFormulario() {
		return "filmes/formulario";	
	}
	
	@GetMapping
	public String carregaPaginaListagem(Model model) {
		model.addAttribute("lista", reposiroty.findAll());
		return "filmes/listagem";	
	}
	
	@PostMapping
	public String cadastraFilme(DadosCadastroFilme dados) {
		var filme = new Filme(dados);	
		reposiroty.save(filme);
			
		return "redirect:/filmes";	
	}
	
	@DeleteMapping
	public String removeFilme(Long id) {
		
		reposiroty.deleteById(id);
		
		return "redirect:/filmes";	
	}
	
}
