package ws.category.comando.manejador;

import org.springframework.stereotype.Component;
import ws.brand.comando.ComandoUpdateBrand;
import ws.category.comando.ComandoSolicitudUpdateCategory;
import ws.category.comando.fabrica.FabricaUpdateCategory;
import ws.category.servicios.ServicioUpdateCategory;

@Component
public class ManejadorUpdateCategory {
    private final ServicioUpdateCategory servicioUpdateCategory;
    private final FabricaUpdateCategory fabricaUpdateCategory;
    public ManejadorUpdateCategory(ServicioUpdateCategory servicioUpdateCategory, FabricaUpdateCategory fabricaUpdateCategory) {
        this.servicioUpdateCategory = servicioUpdateCategory;
        this.fabricaUpdateCategory = fabricaUpdateCategory;
    }

    public void ejecutar(ComandoSolicitudUpdateCategory comandoCategory, String id){
        this.servicioUpdateCategory.ejecutar(this.fabricaUpdateCategory.crear(comandoCategory),id);
    }
}
