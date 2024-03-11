package ws.brand.consulta;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import ws.brand.modelo.entidad.Brand;
import ws.brand.modelo.entidad.DateFilter;
import ws.brand.servicios.ServicioConsultarBrands;
import ws.sort.modelo.dto.SortFieldDTO;

import java.util.Date;
import java.util.List;

@Component
public class ManejadorConsultarBrand {
    private final ServicioConsultarBrands servicioConsultarBrands;

    public ManejadorConsultarBrand(ServicioConsultarBrands servicioConsultarBrands) {
        this.servicioConsultarBrands = servicioConsultarBrands;
    }

    public Page<Brand> ejecutar(int page, String searchText, SortFieldDTO sort,
            List<String> nameFilters, List<String> lockedFilters, List<String> disabledFilters,
            List<DateFilter> dateFilters){
        return this.servicioConsultarBrands.ejecutar(page,searchText, sort, nameFilters, lockedFilters, disabledFilters, dateFilters);
    }
}
