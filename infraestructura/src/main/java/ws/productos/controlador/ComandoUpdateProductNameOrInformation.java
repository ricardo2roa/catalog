package ws.productos.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.product.comando.ComandoUpdateProduct;
import ws.product.comando.manejador.ManejadorUpdateProduct;

@RestController
@RequestMapping("/v1/producto")
public class ComandoUpdateProductNameOrInformation {
    private final ManejadorUpdateProduct manejadorUpdateProduct;

    public ComandoUpdateProductNameOrInformation(ManejadorUpdateProduct manejadorUpdateProduct) {
        this.manejadorUpdateProduct = manejadorUpdateProduct;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(ComandoUpdateProduct comando, @PathVariable String id){
        return ResponseEntity.ok(this.manejadorUpdateProduct.ejecutar(comando,id));
    }
}
