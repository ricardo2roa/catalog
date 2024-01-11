package ws.product.comando.fabrica;

import org.springframework.stereotype.Component;
import ws.product.comando.ComandoSolicitudCrearEnvio;
import ws.product.modelo.entidad.SolicitudCrearProducto;

@Component
public class FabricaSolicitudCrearProducto {
    public SolicitudCrearProducto crear(ComandoSolicitudCrearEnvio comandoSolicitudCrearEnvio){
        return new SolicitudCrearProducto(
                comandoSolicitudCrearEnvio.getTag(), comandoSolicitudCrearEnvio.getCategory(),
                comandoSolicitudCrearEnvio.getBrand(), comandoSolicitudCrearEnvio.getName(),
                comandoSolicitudCrearEnvio.getInformation(), comandoSolicitudCrearEnvio.getReferences()
        );
    }
}
