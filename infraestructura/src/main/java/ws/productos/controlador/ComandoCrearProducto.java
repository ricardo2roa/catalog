package ws.productos.controlador;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ws.product.comando.ComandoProducto;
import ws.product.comando.ComandoSolicitudCrearEnvio;
import ws.product.comando.manejador.ManejadorCrearProductos;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/producto")
public class ComandoCrearProducto {
    private final ManejadorCrearProductos manejadorCrearProductos;

    public ComandoCrearProducto(ManejadorCrearProductos manejadorCrearProductos) {
        this.manejadorCrearProductos = manejadorCrearProductos;
    }

    @PostMapping("/crear")
    public Map<String,String> crear(@RequestParam("files") List<MultipartFile> files,
                                    @RequestParam("product")String json){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            var producto = objectMapper.readValue(json,ComandoProducto.class);
            String id = this.manejadorCrearProductos.ejecutar(new ComandoSolicitudCrearEnvio(producto,files));
            Map<String,String> response = new HashMap<>();
            response.put("id",id);
            return response;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
