package br.com.capflix.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.capflix.model.Filme;
import br.com.capflix.model.dto.FilmeEntradaDto;
import br.com.capflix.model.dto.FilmeSaidaDto;
import br.com.capflix.service.FilmeService;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("filme")
@Log4j2
public class FilmeController {

	@Autowired
	private FilmeService service;


	@PostMapping
	public FilmeSaidaDto salvar(@Valid @RequestBody FilmeEntradaDto entradaDto) {
		log.info("salvar: {}" + entradaDto);
		return service.salvar(entradaDto);
	}

	
	@GetMapping("id/{id}")
	public FilmeSaidaDto pagarUm(@PathVariable("id") Integer id) {
		log.info("pegarUm: {} " + id);

		return service.pegarUm(id);
	}

	@DeleteMapping("id/{id}")
	public boolean excluir(@PathVariable("id") Integer id) {
		log.info("excluir: {} " + id);

		return service.excluir(id);
	}
	
	@GetMapping("listar")
	public List<FilmeSaidaDto>listar() {
		log.info("listar");
		return service.listar();
	}


}