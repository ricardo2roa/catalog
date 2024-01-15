package ws.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.userdetails.User;
import ws.userSecurity.servicios.UserSecurityService;

import java.io.IOException;

@Component
@Log
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtils jwtUtils;

    private final UserSecurityService userSecurityService;

    public JWTFilter(JWTUtils jwtUtils, UserSecurityService userSecurityService) {
        this.jwtUtils = jwtUtils;
        this.userSecurityService = userSecurityService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authHeader == null || authHeader.isEmpty() || !authHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }

        String jwt = authHeader.split(" ")[1].trim();

        if(!this.jwtUtils.isValid(jwt)){
            filterChain.doFilter(request,response);
            return;
        }

        String username = this.jwtUtils.getUserName(jwt);
        User user = (User) this.userSecurityService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
             user.getUsername(),
             user.getPassword(),
             user.getAuthorities()
        );

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        log.info("Hola token: "+authenticationToken);
        filterChain.doFilter(request,response);
    }
}
