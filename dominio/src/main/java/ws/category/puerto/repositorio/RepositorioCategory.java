package ws.category.puerto.repositorio;

import ws.brand.modelo.entidad.DateFilter;
import ws.category.modelo.dto.CategoryWrite;
import ws.category.modelo.entidad.Category;
import ws.category.modelo.entidad.SolicitudUpdateCategory;

import java.util.List;
import java.util.Map;

public interface RepositorioCategory {

    Category obtenerByCode(int code);

    int guardar(Category category);

    Map<Integer, Category> obtenerListValrep();

    List<Category> obtenerTodasLasCategorias(int page, String searchText, List<String> nameFilters, List<String> lockedFilters,
                                                  List<String> disabledFilters, List<DateFilter> dateFilters);
    int calcularCode();

    void updateCategory(SolicitudUpdateCategory category, String id);
}
