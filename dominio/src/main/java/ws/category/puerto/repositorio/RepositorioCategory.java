package ws.category.puerto.repositorio;

import ws.category.modelo.dto.CategoryDTO;
import ws.category.modelo.entidad.Category;

import java.util.Map;
import java.util.Set;

public interface RepositorioCategory {

    Category obtenerByCode(int code);

    int guardar(Category category);

    Map<Integer, CategoryDTO> obtenerListByCodes(Set<Integer> codes);
    Map<Integer, Category> obtenerListValrep();
    int calcularCode();
}
