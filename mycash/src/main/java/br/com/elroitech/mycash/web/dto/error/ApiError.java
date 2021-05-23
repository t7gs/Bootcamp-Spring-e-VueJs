package br.com.elroitech.mycash.web.dto.error;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

public class ApiError {

	private HttpStatus status;
	private LocalDateTime dataHora;
	private String mensagem;
	private List<String> subErros = new ArrayList<>();


	public ApiError(HttpStatus status) {
		this.status = status;
		this.dataHora = LocalDateTime.now();
	}

	public List<String> getSubErros() {
		return subErros;
	}

	public void setSubErros(List<String> subErros) {
		this.subErros = subErros;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public void addValidationErrors(List<FieldError> fieldErrors) {
		this.subErros =  fieldErrors
			.stream()
			.map((fe) -> fe.getDefaultMessage())
			.collect(Collectors.toList());
		
	}

}
