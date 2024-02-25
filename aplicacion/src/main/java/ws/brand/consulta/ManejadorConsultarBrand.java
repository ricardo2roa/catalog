package ws.brand.consulta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import ws.brand.modelo.entidad.Brand;
import ws.brand.puerto.repositorio.RepositorioBrand;
import ws.brand.servicios.ServicioConsultarBrands;

import java.util.List;

@Component
public class ManejadorConsultarBrand {
    private final ServicioConsultarBrands servicioConsultarBrands;

    public ManejadorConsultarBrand(ServicioConsultarBrands servicioConsultarBrands) {
        this.servicioConsultarBrands = servicioConsultarBrands;
    }

    public Page<Brand> ejecutar(int page, Boolean disabled, Boolean locked, List<Integer> codes){
        return this.servicioConsultarBrands.ejecutar(page,disabled,locked,codes);
    }
}
