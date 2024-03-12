package ws.tag.comando.manejador;

import org.springframework.stereotype.Component;
import ws.brand.comando.ComandoUpdateBrand;
import ws.tag.comando.ComandoUpdateTag;
import ws.tag.comando.fabrica.FabricaSolicitudUpdateTag;
import ws.tag.servicios.ServicioUpdateTag;

@Component
public class ManejadorUpdateTag {
    private final ServicioUpdateTag servicioUpdateTag;
    private final FabricaSolicitudUpdateTag fabricaSolicitudUpdateTag;

    public ManejadorUpdateTag(ServicioUpdateTag servicioUpdateTag, FabricaSolicitudUpdateTag fabricaSolicitudUpdateTag) {
        this.servicioUpdateTag = servicioUpdateTag;
        this.fabricaSolicitudUpdateTag = fabricaSolicitudUpdateTag;
    }

    public void ejecutar(ComandoUpdateTag comandoUpdateTag, String id){
        this.servicioUpdateTag.ejecutar(this.fabricaSolicitudUpdateTag.crear(comandoUpdateTag),id);
    }
}
