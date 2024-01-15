package ws.tag.controlador;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.tag.comando.ComandoSolicitudCrearTag;
import ws.tag.comando.manejador.ManejadorCrearTag;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("v1/tag")
public class ComandoCrearTag {
    private final ManejadorCrearTag manejadorCrearTag;

    public ComandoCrearTag(ManejadorCrearTag manejadorCrearTag) {
        this.manejadorCrearTag = manejadorCrearTag;
    }

    @PostMapping("/crear")
    public Map<String,Integer> crear(@RequestBody ComandoSolicitudCrearTag comandoSolicitudCrearTag){
        Map<String,Integer> response = new HashMap<>();
        int code = this.manejadorCrearTag.ejecutar(comandoSolicitudCrearTag);
        response.put("code",code);
        return response;
    }
}
