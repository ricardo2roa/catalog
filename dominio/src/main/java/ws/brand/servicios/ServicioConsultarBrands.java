package ws.brand.servicios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import ws.brand.modelo.entidad.Brand;
import ws.brand.puerto.repositorio.RepositorioBrand;

public class ServicioConsultarBrands {
    private final RepositorioBrand repositorioBrand;

    public ServicioConsultarBrands(RepositorioBrand repositorioBrand) {
        this.repositorioBrand = repositorioBrand;
    }
    public Page<Brand> ejecutar(int numberPage, Boolean disabled, Boolean locked){
        var allBrands = this.repositorioBrand.obtenerTodasLasMarcas(numberPage, disabled, locked);
        return new PageImpl<>(
                allBrands,
                PageRequest.ofSize(allBrands.size()),
                allBrands.size()
        );
    }
}
