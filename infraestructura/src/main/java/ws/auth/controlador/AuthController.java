package ws.auth.controlador;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.config.JWTUtils;
import ws.userSecurity.modelo.dto.LoginDTO;

@RestController
@RequestMapping("v1/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, JWTUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword());
        try {
            Authentication authentication = this.authenticationManager.authenticate(login);
        }catch(BadCredentialsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String jwt = this.jwtUtils.create(loginDTO.getUsername());
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,jwt)
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,"Authorization").build();
    }
}
