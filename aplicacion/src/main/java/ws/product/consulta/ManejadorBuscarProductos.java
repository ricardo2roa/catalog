package ws.product.consulta;

import org.springframework.stereotype.Component;
import ws.product.modelo.entidad.Product;
import ws.product.servicios.ServicioBuscarProducto;

import java.util.List;

@Component
public class ManejadorBuscarProductos {
    private final ServicioBuscarProducto servicioBuscarProducto;

    public ManejadorBuscarProductos(ServicioBuscarProducto servicioBuscarProducto) {
        this.servicioBuscarProducto = servicioBuscarProducto;
    }

    /*public List<Product> ejecutar(){
        return this.servicioBuscarProducto.buscarProductos();
    }*/


    public List<Product> ejecutar(String id){
        return this.servicioBuscarProducto.buscarProductos(id);
    }
}
