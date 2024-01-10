package ws.productos.controlador;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/productos")
public class ComandoConsultarProducto {
    @GetMapping
    public Map<String,String> product() {
        Map<String,String> response = new HashMap<>();
        response.put("prueba","Ricardo");
        return response;
    }

    @PostMapping
    public Map<String,String> product(@RequestBody Map<String,String> request){
        return request;
    }

    @GetMapping("/{id}")
    public Map<String,String> productconId(@PathVariable String id) {
        Map<String,String> response = new HashMap<>();
        response.put("prueba","Ricardo");
        response.put("id",id);
        return response;
    }

    @GetMapping("/chef")
    public Map<String,String> chef() {
        Map<String,String> response = new HashMap<>();
        response.put("Hola","soy chef");
        return response;
    }

    @GetMapping("/ramdom")
    public Map<String,String> ramdom() {
        Map<String,String> response = new HashMap<>();
        response.put("Muchas veces","soy ramdom");
        return response;
    }
}
