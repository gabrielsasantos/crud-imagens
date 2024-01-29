package br.com.capflix.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.capflix.model.Filme;
import br.com.capflix.model.dto.FilmeEntradaDto;
import br.com.capflix.model.dto.FilmeSaidaDto;
import br.com.capflix.repository.FilmeRepository;

@Service
public class FilmeService {

	@Autowired
	private FilmeRepository repository;
	
	@Autowired
	private ModelMapper mapper;

	public FilmeSaidaDto salvar(FilmeEntradaDto entradaDto) {

		Filme filme = mapper.map(entradaDto, Filme.class);

		Filme filmeBanco = repository.save(filme);

		FilmeSaidaDto saidaDto = mapper.map(filmeBanco, FilmeSaidaDto.class);

		return saidaDto;
	}



	public FilmeSaidaDto pegarUm(Integer id) {
		Optional<Filme> optional = repository.findById(id);

		if (optional.isPresent()) {
			Filme filmeBanco = optional.get();
			
			FilmeSaidaDto saidaDto = mapper.map(filmeBanco, FilmeSaidaDto.class);

			return saidaDto;

		}
		return null;
	}

	public boolean excluir(Integer id) {

		if (!repository.existsById(id)) {
			return false;
		}

		repository.deleteById(id);

		return true;
	}
	
	public List<FilmeSaidaDto> listar() {
		List<Filme> filmes = repository.findAll();
		
		List <FilmeSaidaDto> saidaDto = mapper.map(filmes, new TypeToken<List<FilmeSaidaDto>>() {}.getType());
		return saidaDto;
	}



}
