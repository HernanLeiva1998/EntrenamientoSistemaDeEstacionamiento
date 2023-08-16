package ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.controllers.dtos.ErrorDTO;




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

