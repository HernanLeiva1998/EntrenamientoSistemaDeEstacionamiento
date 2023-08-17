package ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions;

import org.springframework.http.HttpStatus;

public class SistemaDeEstacionamientoException extends Exception{
	
	private String message;
	private HttpStatus httpStatus;

    public SistemaDeEstacionamientoException(String message) {
        this.message = message;
    }
    public SistemaDeEstacionamientoException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus=httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
