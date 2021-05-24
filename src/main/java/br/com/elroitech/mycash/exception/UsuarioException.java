package br.com.elroitech.mycash.exception;

public class UsuarioException extends RuntimeException {

	private static final long serialVersionUID = 6666203136453340420L;

	public UsuarioException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsuarioException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public UsuarioException(String message, Throwable cause) {
		super(message, cause);

	}

	public UsuarioException(String message) {
		super(message);

	}

	public UsuarioException(Throwable cause) {
		super(cause);

	}

}
