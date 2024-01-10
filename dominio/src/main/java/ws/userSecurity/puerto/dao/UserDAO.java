package ws.userSecurity.puerto.dao;

import org.springframework.data.repository.CrudRepository;
import ws.userSecurity.modelo.dto.UserDTO;

import java.util.Optional;

public interface UserDAO{
    UserDTO findByName(String name);
}
