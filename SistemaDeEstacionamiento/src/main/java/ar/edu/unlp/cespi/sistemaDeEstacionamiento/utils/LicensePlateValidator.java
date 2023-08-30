package ar.edu.unlp.cespi.sistemaDeEstacionamiento.utils;

import java.util.regex.Pattern;

public class LicensePlateValidator {
    
    private static String LICENSE_PLATE_REGEX = "^(?:[A-Za-z]{3}\\d{3}|[A-Za-z]{2}\\d{3}[A-Za-z]{2})$";
    private static Pattern LICENSE_PLATE_PATTERN = Pattern.compile(LICENSE_PLATE_REGEX);
    
    public LicensePlateValidator() {
    }
    
    
    
    public static boolean validateLicensePlate(String licensePlate) {
        return licensePlate != null && LICENSE_PLATE_PATTERN.matcher(licensePlate).matches();
    }
    
    public static boolean validateLicensePlate(String licensePlate, String validFormats) {
        return licensePlate != null && Pattern.compile(validFormats).matcher(licensePlate).matches();
    }
}
