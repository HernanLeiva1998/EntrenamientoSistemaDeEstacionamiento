package cespi.unlp.edu.ar.SistemaDeEstacionamiento.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cespi.unlp.edu.ar.SistemaDeEstacionamiento.controllers.dtos.ErrorDTO;




@ControllerAdvice
public class ApiExceptionHandler {

    
    @ExceptionHandler(value = {SistemaDeEstacionamientoException.class})
    public ResponseEntity<Object> handleApiRequestException(SistemaDeEstacionamientoException e){
        //crear payload

        //return response entity
        if (e.getHttpStatus() != null) return ResponseEntity.status(e.getHttpStatus()).body(new ErrorDTO(e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
    }
}

