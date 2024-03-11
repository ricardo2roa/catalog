package ws.category.servicios;

import ws.brand.modelo.entidad.SolicitudUpdateBrand;
import ws.brand.puerto.repositorio.RepositorioBrand;
import ws.category.modelo.entidad.SolicitudUpdateCategory;
import ws.category.puerto.repositorio.RepositorioCategory;

public class ServicioUpdateCategory {
    private final RepositorioCategory repositorioCategory;
    public ServicioUpdateCategory(RepositorioCategory repositorioCategory) {
        this.repositorioCategory = repositorioCategory;
    }
    public void ejecutar(SolicitudUpdateCategory category, String id){
        this.repositorioCategory.updateCategory(category, id);
    }
}
