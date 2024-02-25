package ws.brand.controlador;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ws.brand.consulta.ManejadorConsultarBrand;
import ws.brand.modelo.entidad.Brand;

import java.util.List;


@RestController
@RequestMapping("v1/brands")
public class ComandoConsultarAllBrands {
    private final ManejadorConsultarBrand manejadorConsultarBrand;

    public ComandoConsultarAllBrands(ManejadorConsultarBrand manejadorConsultarBrand) {
        this.manejadorConsultarBrand = manejadorConsultarBrand;
    }

    @GetMapping
    public ResponseEntity<Page<Brand>> consultarTodasLasMarcas(@RequestParam(required = false, defaultValue = "0") int page,
                                                               @RequestParam(required = false, defaultValue = "false") Boolean disabled,
                                                               @RequestParam(required = false, defaultValue = "false") Boolean locked,
                                                               @RequestParam(required = false, defaultValue = "") List<Integer> codes){
        return ResponseEntity.ok(this.manejadorConsultarBrand.ejecutar(page,disabled,locked,codes));
    }
}
