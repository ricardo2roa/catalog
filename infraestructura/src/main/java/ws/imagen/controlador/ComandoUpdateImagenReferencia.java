package ws.imagen.controlador;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ws.imagen.comando.ComandoSolicitudUpdateImagen;
import ws.imagen.consulta.ManejadorUpdateImageById;
import ws.product.comando.ComandoProducto;
import ws.product.comando.ComandoReferencia;
import ws.product.comando.ComandoSolicitudCrearProducto;
import ws.reference.modelo.dto.ReferenceDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/imagen")
public class ComandoUpdateImagenReferencia {
    private final ManejadorUpdateImageById manejadorUpdateImageById;

    public ComandoUpdateImagenReferencia(ManejadorUpdateImageById manejadorUpdateImageById) {
        this.manejadorUpdateImageById = manejadorUpdateImageById;
    }

    @PostMapping("/update")
    public ResponseEntity<Boolean> updateImagenReferencia(@RequestParam("file") MultipartFile file,
                                                          @RequestParam("referencia")String json){
        ObjectMapper objectMapper = new ObjectMapper();
        ReferenceDTO referencia;
        try {
            referencia = objectMapper.readValue(json, ReferenceDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().body(this.manejadorUpdateImageById.ejecutar(new ComandoSolicitudUpdateImagen(referencia,file)));
    }
}
