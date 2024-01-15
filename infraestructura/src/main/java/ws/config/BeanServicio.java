package ws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ws.brand.puerto.repositorio.RepositorioBrand;
import ws.brand.servicios.ServicioCrearBrand;
import ws.product.puerto.repositorio.RepositorioProduct;
import ws.product.servicios.ServicioBuscarProducto;
import ws.product.servicios.ServicioCrearProducto;
import ws.tag.puerto.repositorio.RepositorioTag;
import ws.tag.servicios.ServicioCrearTag;
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

    @Bean
    public ServicioCrearBrand servicioCrearBrand(RepositorioBrand repositorioBrand){
        return new ServicioCrearBrand(repositorioBrand);
    }

    @Bean
    public ServicioBuscarProducto servicioBuscarProducto(RepositorioProduct repositorioProduct){
        return new ServicioBuscarProducto(repositorioProduct);
    }

    @Bean
    public ServicioCrearTag servicioCrearTag(RepositorioTag repositoryTag){
        return new ServicioCrearTag(repositoryTag);
    }
}
