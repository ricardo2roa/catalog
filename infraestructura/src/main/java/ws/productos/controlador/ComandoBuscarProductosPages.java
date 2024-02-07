package ws.productos.controlador;

import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.product.consulta.ManejadorBuscarProductosPages;
import ws.product.modelo.entidad.Product;

@RestController
@RequestMapping("/v1/productos")
public class ComandoBuscarProductosPages {
    private final ManejadorBuscarProductosPages manejadorBuscarProductosPages;

    public ComandoBuscarProductosPages(ManejadorBuscarProductosPages manejadorBuscarProductosPages) {
        this.manejadorBuscarProductosPages = manejadorBuscarProductosPages;
    }
    
    @GetMapping
    public ResponseEntity<PageImpl<Product>> buscarProductos(){
        return ResponseEntity.ok(this.manejadorBuscarProductosPages.ejecutar());
    }
}
