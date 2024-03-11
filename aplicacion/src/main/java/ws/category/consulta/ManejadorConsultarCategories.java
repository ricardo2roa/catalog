package ws.category.consulta;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import ws.brand.modelo.entidad.Brand;
import ws.brand.modelo.entidad.DateFilter;
import ws.category.modelo.entidad.Category;
import ws.category.servicios.ServicioConsultarCategory;
import ws.sort.modelo.dto.SortFieldDTO;

import java.util.List;

@Component
public class ManejadorConsultarCategories {
    private final ServicioConsultarCategory servicioConsultarCategory;

    public ManejadorConsultarCategories(ServicioConsultarCategory servicioConsultarCategory) {
        this.servicioConsultarCategory = servicioConsultarCategory;
    }

    public Page<Category> ejecutar(int page, String searchText, SortFieldDTO sort,
                                   List<String> nameFilters, List<String> lockedFilters, List<String> disabledFilters,
                                   List<DateFilter> dateFilters){
        return this.servicioConsultarCategory.ejecutar(page,searchText, sort, nameFilters, lockedFilters, disabledFilters, dateFilters);
    }
}
