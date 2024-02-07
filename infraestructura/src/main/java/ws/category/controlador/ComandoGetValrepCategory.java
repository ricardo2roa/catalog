package ws.category.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.category.consulta.ManejadorGetValrepCategory;
import ws.category.modelo.entidad.Category;

import java.util.Map;

@RestController
@RequestMapping("/v1/category/valrep")
public class ComandoGetValrepCategory {
    private final ManejadorGetValrepCategory manejadorGetValrepCategory;

    public ComandoGetValrepCategory(ManejadorGetValrepCategory manejadorGetValrepCategory) {
        this.manejadorGetValrepCategory = manejadorGetValrepCategory;
    }

    @GetMapping
    public ResponseEntity<Map<Integer, Category>> getValrep(){
        return ResponseEntity.ok(this.manejadorGetValrepCategory.ejecutar());
    }
}
