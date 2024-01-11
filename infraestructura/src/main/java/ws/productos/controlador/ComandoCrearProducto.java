package ws.productos.controlador;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.product.comando.ComandoSolicitudCrearEnvio;
import ws.product.comando.manejador.ManejadorCrearProductos;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/producto")
public class ComandoCrearProducto {
    private final ManejadorCrearProductos manejadorCrearProductos;

    public ComandoCrearProducto(ManejadorCrearProductos manejadorCrearProductos) {
        this.manejadorCrearProductos = manejadorCrearProductos;
    }

    @PostMapping("/crear")
    public Map<String,String> crear(@RequestBody ComandoSolicitudCrearEnvio comandoSolicitudCrearEnvio){
        String id = this.manejadorCrearProductos.ejecutar(comandoSolicitudCrearEnvio);
        Map<String,String> response = new HashMap<>();
        response.put("id",id);
        return response;
    }
}
