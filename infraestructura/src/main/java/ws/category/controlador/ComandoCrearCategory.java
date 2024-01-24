package ws.category.controlador;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.category.comando.ComandoSolicitudCrearCategory;
import ws.category.comando.manejador.ManejadorCrearCategory;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/category")
public class ComandoCrearCategory {
    private final ManejadorCrearCategory manejadorCrearCategory;

    public ComandoCrearCategory(ManejadorCrearCategory manejadorCrearCategory) {
        this.manejadorCrearCategory = manejadorCrearCategory;
    }

    @PostMapping("/crear")
    public Map<String,Integer> crear(@RequestBody ComandoSolicitudCrearCategory comandoSolicitudCrearCategory){
        Map<String,Integer> response = new HashMap<>();
        int code = this.manejadorCrearCategory.ejecutar(comandoSolicitudCrearCategory);
        response.put("code",code);
        return response;
    }
}
