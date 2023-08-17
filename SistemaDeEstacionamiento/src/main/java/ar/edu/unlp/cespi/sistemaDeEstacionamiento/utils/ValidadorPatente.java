package ar.edu.unlp.cespi.sistemaDeEstacionamiento.utils;

import java.util.regex.Pattern;

public class ValidadorPatente {
    
    private static final String PATENTE_REGEX = "^(?:[A-Za-z]{3}\\d{3}|[A-Za-z]{2}\\d{3}[A-Za-z]{2})$";
    private static final Pattern PATENTE_PATTERN = Pattern.compile(PATENTE_REGEX);
    
    public static boolean validarPatente(String patente) {
        return patente != null && PATENTE_PATTERN.matcher(patente).matches();
    }
}
