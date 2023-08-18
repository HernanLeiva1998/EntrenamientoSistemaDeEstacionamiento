package ar.edu.unlp.cespi.sistemaDeEstacionamiento.utils;

import java.util.regex.Pattern;

public class ValidadorPatente {
    
    private static String PATENTE_REGEX = "^(?:[A-Za-z]{3}\\d{3}|[A-Za-z]{2}\\d{3}[A-Za-z]{2})$";
    private static Pattern PATENTE_PATTERN = Pattern.compile(PATENTE_REGEX);
    
    public ValidadorPatente() {
    }
    
    
    
    public static boolean validarPatente(String patente) {
        return patente != null && PATENTE_PATTERN.matcher(patente).matches();
    }
    
    public static boolean validarPatente(String patente, String formatosValidos) {
        return patente != null && Pattern.compile(formatosValidos).matcher(patente).matches();
    }
}
