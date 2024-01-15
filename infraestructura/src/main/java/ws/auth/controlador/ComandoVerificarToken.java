package ws.auth.controlador;

import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.config.JWTUtils;
import ws.userSecurity.comando.ComandoCheckAuth;

@Log
@RestController
@RequestMapping("v1/api/auth")
public class ComandoVerificarToken {
    private final JWTUtils jwtUtils;

    public ComandoVerificarToken(JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/check")
    public boolean check(@RequestBody ComandoCheckAuth comandoCheckAuth) {
        log.info("Prueba de token"+comandoCheckAuth.getToken());
        return this.jwtUtils.isValid(comandoCheckAuth.getToken());
    }
}
