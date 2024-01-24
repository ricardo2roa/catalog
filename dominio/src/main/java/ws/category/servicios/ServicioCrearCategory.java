package ws.category.servicios;

import ws.category.modelo.entidad.Category;
import ws.category.modelo.entidad.SolicitudCrearCategory;
import ws.category.puerto.repositorio.RepositorioCategory;

public class ServicioCrearCategory {
    private final RepositorioCategory repositorioCategory;

    public ServicioCrearCategory(RepositorioCategory repositorioCategory) {
        this.repositorioCategory = repositorioCategory;
    }

    public int ejecutar(SolicitudCrearCategory solicitudCrearCategory){
        int code = this.repositorioCategory.calcularCode();
        Category category = Category.crear(solicitudCrearCategory, code);
        return this.repositorioCategory.guardar(category);
    }
}
