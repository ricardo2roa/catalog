package ws.brand.comando.manejador;

import org.springframework.stereotype.Component;
import ws.brand.comando.ComandoSolicitudCrearBrand;
import ws.brand.comando.fabrica.FabricaSolicitudCrearBrand;
import ws.brand.puerto.repositorio.RepositorioBrand;
import ws.brand.servicios.ServicioCrearBrand;

@Component
public class ManejadorCrearBrand {
    private final FabricaSolicitudCrearBrand fabricaSolicitudCrearBrand;
    private final ServicioCrearBrand servicioCrearBrand;

    public ManejadorCrearBrand(FabricaSolicitudCrearBrand fabricaSolicitudCrearBrand, ServicioCrearBrand servicioCrearBrand) {
        this.fabricaSolicitudCrearBrand = fabricaSolicitudCrearBrand;
        this.servicioCrearBrand = servicioCrearBrand;
    }

    public int ejecutar(ComandoSolicitudCrearBrand comandoSolicitudCrearBrand){
        return this.servicioCrearBrand.ejecutar(fabricaSolicitudCrearBrand.crear(comandoSolicitudCrearBrand));
    }
}
