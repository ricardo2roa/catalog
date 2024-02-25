package ws.brand.servicios;

import ws.brand.modelo.entidad.Brand;
import ws.brand.modelo.entidad.SolicitudUpdateBrand;
import ws.brand.puerto.repositorio.RepositorioBrand;

import java.util.List;

public class ServicioUpdateBrand {
    private final RepositorioBrand repositorioBrand;
    public ServicioUpdateBrand(RepositorioBrand repositorioBrand) {
        this.repositorioBrand = repositorioBrand;
    }
    public void ejecutar(List<SolicitudUpdateBrand> brands){
        this.repositorioBrand.updateBrands(brands);
    }
}
