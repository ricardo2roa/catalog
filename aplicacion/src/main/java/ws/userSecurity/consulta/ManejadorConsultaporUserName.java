package ws.userSecurity.consulta;

import org.springframework.stereotype.Component;
import ws.userSecurity.modelo.dto.UserDTO;
import ws.userSecurity.servicios.ServicioBuscarUserById;

@Component
public class ManejadorConsultaporUserName {
    private final ServicioBuscarUserById servicioBuscarUserById;

    public ManejadorConsultaporUserName(ServicioBuscarUserById servicioBuscarUserById){
        this.servicioBuscarUserById = servicioBuscarUserById;
    }

    public UserDTO ejecutar(String name){
        return servicioBuscarUserById.ejecutar(name);
    }
}
