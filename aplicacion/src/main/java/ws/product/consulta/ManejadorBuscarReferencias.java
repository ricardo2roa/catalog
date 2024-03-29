package ws.product.consulta;

import org.springframework.stereotype.Component;
import ws.reference.modelo.entidad.Reference;
import ws.reference.servicios.ServicioObtenerReferencia;

import java.util.List;

@Component
public class ManejadorBuscarReferencias {
    private final ServicioObtenerReferencia servicioObtenerReferencia;

    public ManejadorBuscarReferencias(ServicioObtenerReferencia servicioObtenerReferencia) {
        this.servicioObtenerReferencia = servicioObtenerReferencia;
    }

    public List<Reference> ejecutar(List<String> referencias){
        return referencias.stream().map(this.servicioObtenerReferencia::ejecutar).toList();
    }
}
