package ws.productos.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.product.consulta.ManejadorBuscarProducto;
import ws.product.modelo.entidad.Product;

@RestController
@RequestMapping("/v1/producto")
public class ComandoBuscarProductById {
    private final ManejadorBuscarProducto manejadorBuscarProducto;

    public ComandoBuscarProductById(ManejadorBuscarProducto manejadorBuscarProducto) {
        this.manejadorBuscarProducto = manejadorBuscarProducto;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductbyId(@PathVariable String id){
        return ResponseEntity.ok(this.manejadorBuscarProducto.ejecutar(id));
    }
}
