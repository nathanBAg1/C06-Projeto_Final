package br.faculdade.exceptions;

public class FalhaNoEmailException extends RuntimeException {
    public FalhaNoEmailException(String message) {
        super(message);
    }
}
