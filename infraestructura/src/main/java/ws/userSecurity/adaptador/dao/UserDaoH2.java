package ws.userSecurity.adaptador.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ws.userSecurity.modelo.dto.UserDTO;
import ws.userSecurity.modelo.dto.UserRoleDTO;
import ws.userSecurity.puerto.dao.UserDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserDaoH2 implements UserDAO{
    private static final String FIND_BY_NAME = "select users.username, users.password, users.email," +
            " users.locked, users.disabled, GROUP_CONCAT(role.role) AS roles from users inner join user_role as role on users.username = role.username"+
            " where users.username=:username GROUP BY users.username";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final MapeoUser mapeoUser;

    public UserDaoH2(NamedParameterJdbcTemplate namedParameterJdbcTemplate,MapeoUser mapeoUser){
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.mapeoUser = mapeoUser;
    }

    @Override
    public UserDTO findByName(String name){
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("username", name);

        List<UserDTO> userDTOList = this.namedParameterJdbcTemplate.query(FIND_BY_NAME, paramSource, mapeoUser);
        return (userDTOList.isEmpty() ? new UserDTO(): userDTOList.get(0));
    }
}
