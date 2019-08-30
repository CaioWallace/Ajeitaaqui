package org.edvaldo.excecoes.business.model.execoes;

public class UsuarioInexistenteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3649629373228752165L;

	public UsuarioInexistenteException(String message) {
		super(message);
	}
}
