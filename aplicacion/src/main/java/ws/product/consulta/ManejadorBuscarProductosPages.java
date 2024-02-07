package ws.product.consulta;

import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import ws.product.modelo.entidad.Product;
import ws.product.servicios.ServicioBuscarProducto;

@Component
public class ManejadorBuscarProductosPages {
    private final ServicioBuscarProducto servicioBuscarProducto;

    public ManejadorBuscarProductosPages(ServicioBuscarProducto servicioBuscarProducto) {
        this.servicioBuscarProducto = servicioBuscarProducto;
    }

    public PageImpl<Product> ejecutar(){return this.servicioBuscarProducto.buscarProductosPages();}
}
