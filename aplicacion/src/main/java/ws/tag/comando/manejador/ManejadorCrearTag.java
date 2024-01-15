package ws.tag.comando.manejador;

import org.springframework.stereotype.Component;
import ws.tag.comando.ComandoSolicitudCrearTag;
import ws.tag.comando.fabrica.FabricaSolicitudCrearTag;
import ws.tag.servicios.ServicioCrearTag;

@Component
public class ManejadorCrearTag {
    private final FabricaSolicitudCrearTag fabricaSolicitudCrearTag;
    private final ServicioCrearTag servicioCrearTag;

    public ManejadorCrearTag(FabricaSolicitudCrearTag fabricaSolicitudCrearTag, ServicioCrearTag servicioCrearTag) {
        this.fabricaSolicitudCrearTag = fabricaSolicitudCrearTag;
        this.servicioCrearTag = servicioCrearTag;
    }

    public int ejecutar(ComandoSolicitudCrearTag comandoSolicitudCrearTag){
        return this.servicioCrearTag.ejecutar(fabricaSolicitudCrearTag.crear(comandoSolicitudCrearTag));
    }
}
