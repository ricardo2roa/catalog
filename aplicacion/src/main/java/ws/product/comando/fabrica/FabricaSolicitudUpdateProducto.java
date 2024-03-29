package ws.product.comando.fabrica;

import org.springframework.stereotype.Component;
import ws.product.comando.ComandoUpdateProduct;
import ws.product.modelo.entidad.SolicitudUpdateProduct;

@Component
public class FabricaSolicitudUpdateProducto {
    public SolicitudUpdateProduct crear(ComandoUpdateProduct comandoUpdateProduct){
        return new SolicitudUpdateProduct(comandoUpdateProduct.getTag(),comandoUpdateProduct.getCategory(),
                comandoUpdateProduct.getBrand(), comandoUpdateProduct.getName(), comandoUpdateProduct.getInformation());
    }
}
