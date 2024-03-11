package ws.category.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ws.brand.comando.ComandoUpdateBrand;
import ws.brand.comando.manejador.ManejadorUpdateBrand;
import ws.category.comando.ComandoSolicitudUpdateCategory;
import ws.category.comando.manejador.ManejadorUpdateCategory;

@RestController
@RequestMapping("v1/category")
public class ComandoControladorUpdateCategory {
    private final ManejadorUpdateCategory manejadorUpdateCategory;

    public ComandoControladorUpdateCategory(ManejadorUpdateCategory manejadorUpdateCategory) {
        this.manejadorUpdateCategory = manejadorUpdateCategory;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateBrand(@PathVariable String id, @RequestBody ComandoSolicitudUpdateCategory category){
        this.manejadorUpdateCategory.ejecutar(category, id);
        return ResponseEntity.ok().build();
    }
}
