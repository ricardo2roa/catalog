package ws.tag.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.brand.consulta.ManejadorGetValrepBrand;
import ws.brand.modelo.entidad.Brand;
import ws.tag.consulta.ManejadorGetValrepTag;
import ws.tag.modelo.entidad.Tag;

import java.util.Map;

@RestController
@RequestMapping("/v1/tag/valrep")
public class ComandoGetValrepTag {
    private final ManejadorGetValrepTag manejadorGetValrepTag;

    public ComandoGetValrepTag(ManejadorGetValrepTag manejadorGetValrepTag) {
        this.manejadorGetValrepTag = manejadorGetValrepTag;
    }

    @GetMapping
    public ResponseEntity<Map<Integer, Tag>> getValrep(){
        return ResponseEntity.ok(this.manejadorGetValrepTag.ejecutar());
    }
}
