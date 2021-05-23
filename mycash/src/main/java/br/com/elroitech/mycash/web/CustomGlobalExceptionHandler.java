package br.com.elroitech.mycash.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.elroitech.mycash.web.dto.error.ApiError;

@RestControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request) {
		
		ApiError apiError = new  ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMensagem("Erro de Validação");
		apiError.addValidationErrors(ex.getFieldErrors());
		
		
		return ResponseEntity.status(apiError.getStatus()).body(apiError);
	}
}
