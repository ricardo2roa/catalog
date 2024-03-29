package ws.imagen.controlador;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.imagen.consulta.ManejadorBuscarImagen;
import ws.imagen.consulta.ManejadorBuscarInfoImagen;
import ws.reference.modelo.entidad.ImageReferenceInfo;

@RestController
@RequestMapping("/v1/imagen/info")
public class ComandoBuscarImagenInformation {
    private final ManejadorBuscarInfoImagen manejadorBuscarInfoImagen;

    public ComandoBuscarImagenInformation(ManejadorBuscarInfoImagen manejadorBuscarInfoImagen) {
        this.manejadorBuscarInfoImagen = manejadorBuscarInfoImagen;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageReferenceInfo> buscarImagenInfo(@PathVariable String id) {
        ImageReferenceInfo infoImage = this.manejadorBuscarInfoImagen.ejecutar(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return ResponseEntity.ok().headers(headers).body(infoImage);
    }
}
