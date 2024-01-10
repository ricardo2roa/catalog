package ws.userSecurity.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO{
    private String username;
    private String password;
    private String email;
    private String[] role;
    private Boolean locked;
    private Boolean disabled;
}

