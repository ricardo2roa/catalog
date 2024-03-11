package ws.brand.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ws.brand.comando.ComandoUpdateBrand;
import ws.brand.comando.manejador.ManejadorUpdateBrand;

import java.util.List;

@RestController
@RequestMapping("v1/brand")
public class ComandoControladorUpdateBrand {
    private final ManejadorUpdateBrand manejadorUpdateBrand;

    public ComandoControladorUpdateBrand(ManejadorUpdateBrand manejadorUpdateBrand) {
        this.manejadorUpdateBrand = manejadorUpdateBrand;
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateBrand(@PathVariable String id, @RequestBody ComandoUpdateBrand brand){
        this.manejadorUpdateBrand.ejecutar(brand, id);
        return ResponseEntity.ok().build();
    }
}
