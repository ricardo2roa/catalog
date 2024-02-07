package ws.brand.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.brand.consulta.ManejadorGetValrepBrand;
import ws.brand.modelo.entidad.Brand;

import java.util.Map;

@RestController
@RequestMapping("/v1/brand/valrep")
public class ComandoGetValrepBrand {
    private final ManejadorGetValrepBrand manejadorGetValrep;

    public ComandoGetValrepBrand(ManejadorGetValrepBrand manejadorGetValrep) {
        this.manejadorGetValrep = manejadorGetValrep;
    }

    @GetMapping
    public ResponseEntity<Map<Integer, Brand>> getValrep(){
        return ResponseEntity.ok(this.manejadorGetValrep.ejecutar());
    }
}
