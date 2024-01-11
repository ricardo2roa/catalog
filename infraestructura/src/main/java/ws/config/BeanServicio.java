package ws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ws.product.puerto.repositorio.RepositorioProduct;
import ws.product.servicios.ServicioCrearProducto;
import ws.userSecurity.puerto.dao.UserDAO;
import ws.userSecurity.servicios.ServicioBuscarUserById;

@Configuration
public class BeanServicio {
    @Bean
    public ServicioBuscarUserById servicioBuscarUserById(UserDAO userDao){
        return new ServicioBuscarUserById(userDao);
    }

    @Bean
    public ServicioCrearProducto servicioCrearProducto(RepositorioProduct repositorioProduct){
        return new ServicioCrearProducto(repositorioProduct);
    }

}
