package ws.brand.servicios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import ws.brand.modelo.entidad.Brand;
import ws.brand.puerto.repositorio.RepositorioBrand;

public class ServicioConsultarBrands {
    public static final int PAGE_SIZE = 10;
    private final RepositorioBrand repositorioBrand;

    public ServicioConsultarBrands(RepositorioBrand repositorioBrand) {
        this.repositorioBrand = repositorioBrand;
    }
    public Page<Brand> ejecutar(int page, Boolean disabled, Boolean locked){
        return new PageImpl<>(
                this.repositorioBrand.obtenerTodasLasMarcas(disabled, locked),
                PageRequest.of(page, PAGE_SIZE),
                PAGE_SIZE
        );
    }
}
