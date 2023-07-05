package cespi.unlp.edu.ar.SistemaDeEstacionamiento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cespi.unlp.edu.ar.SistemaDeEstacionamiento.models.Patente;
import cespi.unlp.edu.ar.SistemaDeEstacionamiento.service.SistemaDeEstacionamientoService;

@RestController
public class PatenteController {

    @Autowired
    private SistemaDeEstacionamientoService service;

    @GetMapping("/patentes/{telefono}")
    public List<Patente> getPatentesPorTelefono(@PathVariable String telefono) {
        return service.verPatentesDelAutomovilista(telefono);
    }

    @PostMapping("/patentes/agregar")
    public ResponseEntity<?> agregarPatenteAutomovilista(@RequestBody Map<String, String> request) {
        String telefono = request.get("telefono");
        String patente = request.get("patente");

        service.agregarPatenteSegunTelefonoDelAutomovilista(telefono, patente);

        return ResponseEntity.ok().build();
    }
}