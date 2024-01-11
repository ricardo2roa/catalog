package ws.product.comando.manejador;

import org.springframework.stereotype.Component;
import ws.product.comando.ComandoSolicitudCrearEnvio;
import ws.product.comando.fabrica.FabricaSolicitudCrearProducto;
import ws.product.servicios.ServicioCrearProducto;

@Component
public class ManejadorCrearProductos {
    private final ServicioCrearProducto servicioCrearProducto;
    private final FabricaSolicitudCrearProducto fabricaSolicitudCrearProducto;

    public ManejadorCrearProductos(ServicioCrearProducto servicioCrearProducto, FabricaSolicitudCrearProducto fabricaSolicitudCrearProducto) {
        this.servicioCrearProducto = servicioCrearProducto;
        this.fabricaSolicitudCrearProducto = fabricaSolicitudCrearProducto;
    }

    public String ejecutar(ComandoSolicitudCrearEnvio comandoSolicitudCrearEnvio){
        return this.servicioCrearProducto.ejecutar(fabricaSolicitudCrearProducto.crear(comandoSolicitudCrearEnvio));
    }
}
