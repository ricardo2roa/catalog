package ws.userSecurity.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import ws.userSecurity.modelo.dto.UserDTO;
import ws.userSecurity.puerto.dao.UserDAO;

import java.util.Optional;

public class ServicioBuscarUserById {
    private final UserDAO userDAO;

    public ServicioBuscarUserById(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public UserDTO ejecutar(String name){
        return this.userDAO.findByName(name);
    }
}
