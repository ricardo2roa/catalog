package ws.category.consulta;

import org.springframework.stereotype.Component;
import ws.category.modelo.entidad.Category;
import ws.category.puerto.repositorio.RepositorioCategory;

import java.util.Map;

@Component
public class ManejadorGetValrepCategory {
    private final RepositorioCategory repositorioCategory;

    public ManejadorGetValrepCategory(RepositorioCategory repositorioCategory) {
        this.repositorioCategory = repositorioCategory;
    }

    public Map<Integer, Category> ejecutar(){
        return this.repositorioCategory.obtenerListValrep();
    }
}
