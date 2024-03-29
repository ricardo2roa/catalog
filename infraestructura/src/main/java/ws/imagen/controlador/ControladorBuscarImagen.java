package ws.imagen.controlador;

import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ws.UploadFiles.modelo.entidad.RecursoImagen;
import ws.imagen.consulta.ManejadorBuscarImagen;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/v1/imagen")
public class ControladorBuscarImagen {
    private final ManejadorBuscarImagen manejadorBuscarImagen;

    public ControladorBuscarImagen(ManejadorBuscarImagen manejadorBuscarImagen) {
        this.manejadorBuscarImagen = manejadorBuscarImagen;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> buscarImagen(@PathVariable String id) {
        Resource file = this.manejadorBuscarImagen.ejecutar(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"");
        return ResponseEntity.ok().headers(headers).body(file);
    }


}
