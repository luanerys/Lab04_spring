package br.com.testeComSpring.service.exception;

public class FreteException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public FreteException(String msg) throws FreteException {
		super(msg);
	}

}
