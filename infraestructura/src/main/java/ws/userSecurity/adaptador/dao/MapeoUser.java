package ws.userSecurity.adaptador.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ws.userSecurity.modelo.dto.UserDTO;
import ws.userSecurity.modelo.dto.UserRoleDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Component
public class MapeoUser implements RowMapper<UserDTO>{
    @Override
    public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        String username = rs.getString("username");
        String password = rs.getString("password");
        String email = rs.getString("email");
        String[] roles = Arrays.asList(rs.getString("roles").split(",")).toArray(String[]::new);
        Boolean locked = rs.getBoolean("locked");
        Boolean disabled = rs.getBoolean("disabled");
        return new UserDTO(username, password, email, roles, locked, disabled);
    }
}
