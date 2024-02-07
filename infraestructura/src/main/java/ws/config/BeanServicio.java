package ws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ws.UploadFiles.config.PropiedadesAlmacenamiento;
import ws.UploadFiles.servicios.ImageSystemStorageService;
import ws.brand.puerto.repositorio.RepositorioBrand;
import ws.brand.servicios.ServicioBuscarBrand;
import ws.brand.servicios.ServicioCrearBrand;
import ws.category.puerto.repositorio.RepositorioCategory;
import ws.category.servicios.ServicioConsultarCategory;
import ws.category.servicios.ServicioCrearCategory;
import ws.product.puerto.repositorio.RepositorioProduct;
import ws.product.servicios.ServicioBuscarProducto;
import ws.product.servicios.ServicioCrearProducto;
import ws.reference.puerto.repositorio.RepositorioImageReference;
import ws.reference.puerto.repositorio.RepositorioReference;
import ws.reference.servicios.ServicioObtenerReferencia;
import ws.tag.puerto.repositorio.RepositorioTag;
import ws.tag.servicios.ServicioConsultarTag;
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
    public ServicioCrearProducto servicioCrearProducto(RepositorioProduct repositorioProduct,
              RepositorioBrand repositorioBrand, RepositorioReference repositorioReference,
              ImageSystemStorageService serviceImageStorage, RepositorioImageReference repositorioImageReference){
        return new ServicioCrearProducto(repositorioProduct, repositorioBrand, repositorioReference, repositorioImageReference, serviceImageStorage);
    }

    @Bean
    public ServicioCrearBrand servicioCrearBrand(RepositorioBrand repositorioBrand){
        return new ServicioCrearBrand(repositorioBrand);
    }

    @Bean
    public ServicioBuscarProducto servicioBuscarProducto(RepositorioProduct repositorioProduct, RepositorioCategory repositorioCategory,
                                                         RepositorioBrand repositorioBrand, RepositorioTag repositorioTag, RepositorioReference repositorioReference){
        return new ServicioBuscarProducto(repositorioProduct, repositorioCategory, repositorioBrand, repositorioTag, repositorioReference);
    }

    @Bean
    public ServicioCrearTag servicioCrearTag(RepositorioTag repositoryTag){
        return new ServicioCrearTag(repositoryTag);
    }

    @Bean
    public ServicioCrearCategory servicioCrearCategory(RepositorioCategory repositorioCategory){
        return new ServicioCrearCategory(repositorioCategory);
    }

    @Bean
    public ServicioBuscarBrand servicioBuscarBrand(RepositorioBrand repositorioBrand){
        return new ServicioBuscarBrand(repositorioBrand);
    }

    @Bean
    public ServicioConsultarCategory servicioConsultarCategory(RepositorioCategory repositorioCategory){
        return new ServicioConsultarCategory(repositorioCategory);
    }

    @Bean
    public ServicioConsultarTag servicioConsultarTag(RepositorioTag repositorioTag){
        return new ServicioConsultarTag(repositorioTag);
    }

    @Bean
    public ServicioObtenerReferencia servicioCrearReferencia(RepositorioReference repositorioReference){
        return new ServicioObtenerReferencia(repositorioReference);
    }

    @Bean
    public ImageSystemStorageService imageSystemStorageService(PropiedadesAlmacenamiento propiedadesAlmacenamiento){
        return new ImageSystemStorageService(propiedadesAlmacenamiento);
    }
}
