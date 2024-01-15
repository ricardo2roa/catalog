package ws.brand.servicios;

import ws.brand.modelo.entidad.Brand;
import ws.brand.modelo.entidad.SolicitudCrearBrand;
import ws.brand.puerto.repositorio.RepositorioBrand;

public class ServicioCrearBrand {
    private final RepositorioBrand repositorioBrand;

    public ServicioCrearBrand(RepositorioBrand repositorioBrand) {
        this.repositorioBrand = repositorioBrand;
    }

    public int ejecutar(SolicitudCrearBrand solicitudCrearBrand){
        int code = this.repositorioBrand.calcularCode();
        Brand brand = Brand.crear(code,solicitudCrearBrand.getName(),solicitudCrearBrand.getLocked(),
        solicitudCrearBrand.getDisabled());
        return this.repositorioBrand.guardar(brand);
    }
}
