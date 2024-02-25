package ws.brand.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.brand.comando.ComandoUpdateBrand;
import ws.brand.comando.manejador.ManejadorUpdateBrand;

import java.util.List;

@RestController
@RequestMapping("/v1/brand")
public class ComandoControladorUpdateBrand {
    private final ManejadorUpdateBrand manejadorUpdateBrand;

    public ComandoControladorUpdateBrand(ManejadorUpdateBrand manejadorUpdateBrand) {
        this.manejadorUpdateBrand = manejadorUpdateBrand;
    }
    @PostMapping("/update")
    public ResponseEntity<Void> updateBrand(List<ComandoUpdateBrand> brands){
        this.manejadorUpdateBrand.ejecutar(brands);
        return ResponseEntity.ok().build();
    }
}
