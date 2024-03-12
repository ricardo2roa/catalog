package ws.tag.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ws.category.comando.ComandoSolicitudUpdateCategory;
import ws.tag.comando.ComandoUpdateTag;
import ws.tag.comando.manejador.ManejadorUpdateTag;

@RestController
@RequestMapping("v1/tag")
public class ComandoControladorUpdateTag {
    private final ManejadorUpdateTag manejadorUpdateTag;
    public ComandoControladorUpdateTag(ManejadorUpdateTag manejadorUpdateTag) {
        this.manejadorUpdateTag = manejadorUpdateTag;
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateBrand(@PathVariable String id, @RequestBody ComandoUpdateTag tag){
        this.manejadorUpdateTag.ejecutar(tag, id);
        return ResponseEntity.ok().build();
    }
}
