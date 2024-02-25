package ws.brand.servicios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import ws.brand.modelo.entidad.Brand;
import ws.brand.puerto.repositorio.RepositorioBrand;
import ws.sort.modelo.dto.SortFieldDTO;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ServicioConsultarBrands {
    private final int PAGE_SIZE = 10;
    private final RepositorioBrand repositorioBrand;

    public ServicioConsultarBrands(RepositorioBrand repositorioBrand) {
        this.repositorioBrand = repositorioBrand;
    }
    public Page<Brand> ejecutar(int numberPage, Boolean disabled, Boolean locked, List<Integer> codes, String searchText, SortFieldDTO sort){
        List<Brand> allBrands = this.repositorioBrand.obtenerTodasLasMarcas(numberPage, disabled, locked, codes, searchText);

        Comparator<Brand> comparator = null;
        if(sort.getField().equals("name")){
            comparator = Comparator.comparing(Brand::getName);
        }else if(sort.getField().equals("dateCreated")){
            comparator = Comparator.comparing(Brand::getDateCreated);
        } else if (sort.getField().equals("locked")) {
            comparator = Comparator.comparing(Brand::getLocked);
        } else if (sort.getField().equals("disabled")) {
            comparator = Comparator.comparing(Brand::getDisabled);
        }

        if (sort.getOrder() < 0 && comparator != null) {
            comparator = comparator.reversed();
        }
        
        allBrands.sort(comparator);

        var size = this.repositorioBrand.calcularCode() - 1;
        return new PageImpl<>(
                allBrands,
                PageRequest.of(numberPage,PAGE_SIZE),
                size
        );
    }
}
