package ws.userSecurity.servicios;

import lombok.extern.java.Log;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ws.userSecurity.modelo.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

@Service
@Log
public class UserSecurityService implements UserDetailsService {
    private final ServicioBuscarUserById servicioBuscarUserById;

    public UserSecurityService(ServicioBuscarUserById servicioBuscarUserById){
        this.servicioBuscarUserById = servicioBuscarUserById;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = this.servicioBuscarUserById.ejecutar(username);
        if(userDTO.getUsername().isEmpty()) throw new UsernameNotFoundException("User"+username+"not found");

        log.info(userDTO.getRole().toString());
        return User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                //.roles(userDTO.getRole())
                .authorities(grantedAuthority(userDTO.getRole()))
                .accountLocked(userDTO.getLocked())
                .disabled(userDTO.getDisabled())
                .build();
    }

    private String[] getAuthorities(String role){
        if("ADMIN".equals(role) || "CUSTOMER".equals(role)){
            return new String[]{"ramdom"};
        }
        return new String[]{};
    }

    private List<GrantedAuthority> grantedAuthority(String[] roles){
        List<GrantedAuthority> authorities = new ArrayList<>(roles.length);
        for(String role : roles){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role));
            for(String authority: getAuthorities(role)){
                authorities.add(new SimpleGrantedAuthority(authority));
            }
        }
        return authorities;
    }
}
