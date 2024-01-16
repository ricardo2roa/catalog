package ws.product.consulta;

import org.springframework.stereotype.Component;
import ws.product.servicios.ServicioBuscarProducto;

@Component
public class ManejadorCountProduct {
    private final ServicioBuscarProducto servicioBuscarProducto;

    public ManejadorCountProduct(ServicioBuscarProducto servicioBuscarProducto) {
        this.servicioBuscarProducto = servicioBuscarProducto;
    }

    public int ejecutar(){
        return this.servicioBuscarProducto.count();
    }
}
