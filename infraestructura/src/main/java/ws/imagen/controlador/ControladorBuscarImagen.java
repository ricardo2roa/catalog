package ws.imagen.controlador;

import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.imagen.consulta.ManejadorBuscarImagen;

@RestController
@RequestMapping("/v1/imagen")
public class ControladorBuscarImagen {
    private final ManejadorBuscarImagen manejadorBuscarImagen;

    public ControladorBuscarImagen(ManejadorBuscarImagen manejadorBuscarImagen) {
        this.manejadorBuscarImagen = manejadorBuscarImagen;
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> buscarImagen(@PathVariable String filename){
        Resource file = this.manejadorBuscarImagen.ejecutar(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\""+file.getFilename()+"\"").body(file);
    }
}
