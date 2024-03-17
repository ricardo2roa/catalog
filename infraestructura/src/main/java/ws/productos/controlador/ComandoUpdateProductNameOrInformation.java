package ws.productos.controlador;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/producto")
public class ComandoUpdateProductNameOrInformation {
    @PutMapping("/{id}")
    public ResponseEntity<String
}
