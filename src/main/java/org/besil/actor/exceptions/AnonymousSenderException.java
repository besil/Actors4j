package org.besil.actor.exceptions;

public class AnonymousSenderException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AnonymousSenderException(String mex) {
		super(mex);
	}
}
