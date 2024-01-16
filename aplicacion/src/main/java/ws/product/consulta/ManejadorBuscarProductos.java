package ws.product.consulta;

import org.springframework.stereotype.Component;
import ws.product.modelo.dto.ProductDTO;
import ws.product.servicios.ServicioBuscarProducto;
import ws.product.servicios.ServicioCrearProducto;

import java.util.List;

@Component
public class ManejadorBuscarProductos {
    private final ServicioBuscarProducto servicioBuscarProducto;

    public ManejadorBuscarProductos(ServicioBuscarProducto servicioBuscarProducto) {
        this.servicioBuscarProducto = servicioBuscarProducto;
    }

    public List<ProductDTO> ejecutar(){
        return this.servicioBuscarProducto.buscarProductos();
    }

    public List<ProductDTO> ejecutar(String id){
        return this.servicioBuscarProducto.buscarProductos(id);
    }
}
