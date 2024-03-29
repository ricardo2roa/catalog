package ws.product.comando.manejador;

import org.springframework.stereotype.Component;
import ws.product.comando.ComandoUpdateProduct;
import ws.product.comando.fabrica.FabricaSolicitudUpdateProducto;
import ws.product.puerto.repositorio.RepositorioProduct;
import ws.product.servicios.ServiciosUpdateProduct;

@Component
public class ManejadorUpdateProduct {
    private final ServiciosUpdateProduct serviciosUpdateProduct;
    private final FabricaSolicitudUpdateProducto fabricaSolicitudUpdateProducto;
    public ManejadorUpdateProduct(ServiciosUpdateProduct serviciosUpdateProduct, FabricaSolicitudUpdateProducto fabricaSolicitudUpdateProducto) {
        this.serviciosUpdateProduct = serviciosUpdateProduct;
        this.fabricaSolicitudUpdateProducto = fabricaSolicitudUpdateProducto;
    }

    public String ejecutar(ComandoUpdateProduct comandoUpdateProduct, String id){
        return this.serviciosUpdateProduct.ejecutar(this.fabricaSolicitudUpdateProducto.crear(comandoUpdateProduct),id);
    }
}
