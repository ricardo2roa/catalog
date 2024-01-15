package ws.brand.controlador;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.brand.comando.ComandoSolicitudCrearBrand;
import ws.brand.comando.manejador.ManejadorCrearBrand;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("v1/brand")
public class ComandoCrearBrand {
    private final ManejadorCrearBrand manejadorCrearBrand;

    public ComandoCrearBrand(ManejadorCrearBrand manejadorCrearBrand) {
        this.manejadorCrearBrand = manejadorCrearBrand;
    }

    @PostMapping("/crear")
    public Map<String,Integer> crear(@RequestBody ComandoSolicitudCrearBrand comandoSolicitudCrearBrand){
        Map<String,Integer> response = new HashMap<>();
        int code = this.manejadorCrearBrand.ejecutar(comandoSolicitudCrearBrand);
        response.put("code",code);
        return response;
    }
}
