package ws.category.servicios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import ws.brand.modelo.entidad.Brand;
import ws.brand.modelo.entidad.DateFilter;
import ws.category.modelo.dto.CategoryWrite;
import ws.category.modelo.entidad.Category;
import ws.category.puerto.repositorio.RepositorioCategory;
import ws.sort.modelo.dto.SortFieldDTO;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ServicioConsultarCategory {
    private static final int PAGE_SIZE = 10;
    private final RepositorioCategory repositorioCategory;

    public ServicioConsultarCategory(RepositorioCategory repositorioCategory) {
        this.repositorioCategory = repositorioCategory;
    }

    public Page<Category> ejecutar(int numberPage, String searchText, SortFieldDTO sort,
                                List<String> nameFilters, List<String> lockedFilters, List<String> disabledFilters,
                                List<DateFilter> dateFilters){
        List<Category> allCategories = this.repositorioCategory.obtenerTodasLasCategorias(numberPage, searchText, nameFilters,
                lockedFilters, disabledFilters, dateFilters);

        Comparator<Category> comparator = null;
        if(sort.getField().equals("name")){
            comparator = Comparator.comparing(category -> category.getName().toLowerCase());
        }else if(sort.getField().equals("dateCreated")){
            comparator = Comparator.comparing(Category::getDateCreated);
        } else if (sort.getField().equals("locked")) {
            comparator = Comparator.comparing(Category::getLocked);
        } else if (sort.getField().equals("disabled")) {
            comparator = Comparator.comparing(Category::getDisabled);
        }

        if (sort.getOrder() < 0 && comparator != null) {
            comparator = comparator.reversed();
        }

        if(comparator != null) {
            allCategories = allCategories.stream().sorted(comparator).toList();
        }

        var size = this.repositorioCategory.calcularCode() - 1;
        return new PageImpl<>(
                allCategories,
                PageRequest.of(numberPage,PAGE_SIZE),
                size
        );
    }
}
