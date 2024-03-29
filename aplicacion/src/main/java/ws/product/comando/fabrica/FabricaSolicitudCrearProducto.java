package ws.product.comando.fabrica;

import org.springframework.stereotype.Component;
import ws.product.comando.ComandoSolicitudCrearProducto;
import ws.product.comando.ComandoProducto;
import ws.product.modelo.entidad.SolicitudCrearProducto;
import ws.product.modelo.entidad.SolicitudProducto;
import static ws.product.comando.ComandoReferencia.convertir;
@Component
public class FabricaSolicitudCrearProducto {
    public SolicitudCrearProducto crear(ComandoSolicitudCrearProducto comandoSolicitudCrearProducto){
        ComandoProducto comandoProducto = comandoSolicitudCrearProducto.getProducto();
        return new SolicitudCrearProducto(
            new SolicitudProducto(comandoProducto.getTag(), comandoProducto.getCategory(), comandoProducto.getBrand(),
                    comandoProducto.getName(), comandoProducto.getInformation(), convertir(comandoProducto.getReferences())),
                comandoSolicitudCrearProducto.getMultipartFiles()
        );
    }
}
