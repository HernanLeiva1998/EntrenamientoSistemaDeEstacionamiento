package cespi.unlp.edu.ar.SistemaDeEstacionamiento.utils;

public class SistemaDeEstacionamientoException extends Exception{
	
	private String message;

    public SistemaDeEstacionamientoException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
