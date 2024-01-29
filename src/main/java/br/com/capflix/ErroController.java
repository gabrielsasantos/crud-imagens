package br.com.capflix;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import  org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.capflix.model.dto.ErroDto;


@RestControllerAdvice
public class ErroController {

	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	@ResponseBody
	public ErroDto handle(BindException exception) {
		List<String> validacoes = new ArrayList<>();

		for (FieldError error : exception.getBindingResult().getFieldErrors()) {
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			validacoes.add(error.getField()+": "+mensagem);
		}
		
		ErroDto erroDto = new ErroDto();
		erroDto.setErro("Erro de Validação");
		erroDto.setValidacoes(validacoes);

		return erroDto;
	}
}

