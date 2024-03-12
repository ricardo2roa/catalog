package ws.tag.consulta;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import ws.brand.modelo.entidad.DateFilter;
import ws.sort.modelo.dto.SortFieldDTO;
import ws.tag.modelo.entidad.Tag;
import ws.tag.servicios.ServicioConsultarTag;

import java.util.List;

@Component
public class ManejadorConsultarTags {
    private final ServicioConsultarTag servicioConsultarTag;

    public ManejadorConsultarTags(ServicioConsultarTag servicioConsultarTag) {
        this.servicioConsultarTag = servicioConsultarTag;
    }

   public Page<Tag> ejecutar(int page, String searchText, SortFieldDTO sort,
                             List<String> nameFilters, List<String> lockedFilters, List<String> disabledFilters,
                             List<DateFilter> dateFilters){
        return this.servicioConsultarTag.ejecutar(page,searchText, sort, nameFilters, lockedFilters, disabledFilters, dateFilters);
   }
}
