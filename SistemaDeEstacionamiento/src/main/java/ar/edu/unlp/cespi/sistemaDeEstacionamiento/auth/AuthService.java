package ar.edu.unlp.cespi.sistemaDeEstacionamiento.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ar.edu.unlp.cespi.sistemaDeEstacionamiento.config.jwt.JwtService;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.exceptions.ParkingSystemException;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Driver;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Wallet;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.models.Role;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.repositories.DriverRepository;
import ar.edu.unlp.cespi.sistemaDeEstacionamiento.service.interfaces.DriverService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final DriverRepository userRepository;
    @Autowired private DriverService driverService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=userRepository.findByPhone(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();

    }

    public AuthResponse register(RegisterRequest request) throws ParkingSystemException  {
       /* Automovilista user = Automovilista.builder()
            .telefono(request.getUsername())
            .contrasena(passwordEncoder.encode( request.getPassword()))
            .role(Role.USER)
            .cuentaCorriente(new CuentaCorriente(1000d))
            .build();
        */ 
    	Driver user= driverService.createDriver(
        		request.getUsername(), 
        		passwordEncoder.encode( request.getPassword()),
        		request.getEmail(),
        		new Wallet(1000d),
        		Role.USER);
        //userRepository.save(user);

        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();
        
    }

}