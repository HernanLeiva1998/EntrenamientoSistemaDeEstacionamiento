package ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions;

import org.springframework.http.HttpStatus;

public class ParkingSystemException extends Exception{
	
	private String message;
	private HttpStatus httpStatus;

    public ParkingSystemException(String message) {
        this.message = message;
    }
    public ParkingSystemException(String message, HttpStatus httpStatus) {
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
