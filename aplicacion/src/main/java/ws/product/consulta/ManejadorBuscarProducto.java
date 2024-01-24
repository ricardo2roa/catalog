package ws.product.consulta;

import org.springframework.stereotype.Component;
import ws.product.modelo.entidad.Product;
import ws.product.servicios.ServicioBuscarProducto;

@Component
public class ManejadorBuscarProducto {
    private final ServicioBuscarProducto servicioBuscarProducto;

    public ManejadorBuscarProducto(ServicioBuscarProducto servicioBuscarProducto) {
        this.servicioBuscarProducto = servicioBuscarProducto;
    }

    public Product ejecutar(String id){
        return this.servicioBuscarProducto.buscarProduct(id);
    }
}
