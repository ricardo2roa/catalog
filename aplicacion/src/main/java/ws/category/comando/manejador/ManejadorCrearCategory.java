package ws.category.comando.manejador;

import org.springframework.stereotype.Component;
import ws.brand.servicios.ServicioCrearBrand;
import ws.category.comando.ComandoSolicitudCrearCategory;
import ws.category.comando.fabrica.FabricaSolicitudCrearCategory;
import ws.category.servicios.ServicioCrearCategory;

@Component
public class ManejadorCrearCategory {
    private final FabricaSolicitudCrearCategory fabricaSolicitudCrearCategory;
    private final ServicioCrearCategory servicioCrearCategory;

    public ManejadorCrearCategory(FabricaSolicitudCrearCategory fabricaSolicitudCrearCategory, ServicioCrearCategory servicioCrearCategory) {
        this.fabricaSolicitudCrearCategory = fabricaSolicitudCrearCategory;
        this.servicioCrearCategory = servicioCrearCategory;
    }

    public int ejecutar(ComandoSolicitudCrearCategory comandoSolicitudCrearCategory){
        return this.servicioCrearCategory.ejecutar(fabricaSolicitudCrearCategory.crear(comandoSolicitudCrearCategory));
    }
}
