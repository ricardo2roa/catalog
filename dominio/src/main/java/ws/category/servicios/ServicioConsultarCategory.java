package ws.category.servicios;

import ws.category.modelo.dto.CategoryDTO;
import ws.category.puerto.repositorio.RepositorioCategory;

import java.util.Map;
import java.util.Set;

public class ServicioConsultarCategory {
    private final RepositorioCategory repositorioCategory;

    public ServicioConsultarCategory(RepositorioCategory repositorioCategory) {
        this.repositorioCategory = repositorioCategory;
    }

    public Map<Integer, CategoryDTO> getCategorysFor(Set<Integer> codes){
        return this.repositorioCategory.obtenerListByCodes(codes);
    }
}
