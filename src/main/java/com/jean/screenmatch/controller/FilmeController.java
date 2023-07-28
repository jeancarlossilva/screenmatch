package com.jean.screenmatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jean.screenmatch.domain.filme.DadosAlteracaoFilme;
import com.jean.screenmatch.domain.filme.DadosCadastroFilme;
import com.jean.screenmatch.domain.filme.Filme;
import com.jean.screenmatch.domain.filme.FilmeRepository;

import jakarta.transaction.Transactional;
	
@Controller
@RequestMapping("/filmes")
public class FilmeController {
	
	@Autowired	
	private FilmeRepository reposiroty;

	@GetMapping("/formulario")
	public String carregaPaginaFormulario(Long id, Model model) {
		
		if(id != null) {
			Filme filme = reposiroty.getReferenceById(id);
			model.addAttribute("filme", filme);
		}
		
		return "filmes/formulario";	
	}
	
	@GetMapping
	public String carregaPaginaListagem(Model model) {
		model.addAttribute("lista", reposiroty.findAll());
		return "filmes/listagem";	
	}
	
	@PostMapping
	@Transactional
	public String cadastraFilme(DadosCadastroFilme dados) {
		var filme = new Filme(dados);	
		reposiroty.save(filme);
			
		return "redirect:/filmes";	
	}
	
	@PutMapping
	@Transactional
	public String atualizarFilme(DadosAlteracaoFilme dados) {
		var filme = reposiroty.getReferenceById(dados.id());
		filme.atualizaDados(dados);
					
		return "redirect:/filmes";	
	}
	
	@DeleteMapping
	@Transactional
	public String removeFilme(Long id) {
		
		reposiroty.deleteById(id);
		
		return "redirect:/filmes";	
	}
	
}
