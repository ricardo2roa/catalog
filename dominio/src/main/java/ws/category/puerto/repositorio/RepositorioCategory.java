package ws.category.puerto.repositorio;

import ws.brand.modelo.entidad.Brand;
import ws.brand.modelo.entidad.SolicitudUpdateBrand;
import ws.category.modelo.dto.CategoryDTO;
import ws.category.modelo.entidad.Category;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RepositorioCategory {

    Category obtenerByCode(int code);

    int guardar(Category category);

    Map<Integer, CategoryDTO> obtenerListByCodes(Set<Integer> codes);
    Map<Integer, Category> obtenerListValrep();
    List<Brand> obtenerTodasLasCategorias(int page, Boolean disabled, Boolean locked, List<Integer> codes);
    int calcularCode();
    void updateCategories(List<SolicitudUpdateBrand> brands);
}
