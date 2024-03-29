package ws.reference.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ws.product.consulta.ManejadorBuscarReferencias;
import ws.reference.modelo.entidad.Reference;

import java.util.List;

@RestController
@RequestMapping("/v1/reference")
public class ControladorBuscarReferencias {
    private final ManejadorBuscarReferencias manejadorBuscarReferencias;

    public ControladorBuscarReferencias(ManejadorBuscarReferencias manejadorBuscarReferencias) {
        this.manejadorBuscarReferencias = manejadorBuscarReferencias;
    }

    @PostMapping
    public ResponseEntity<List<Reference>> obtenerReferencia(@RequestBody List<String> referenciaIds){
        return ResponseEntity.ok(this.manejadorBuscarReferencias.ejecutar(referenciaIds));
    }
}
