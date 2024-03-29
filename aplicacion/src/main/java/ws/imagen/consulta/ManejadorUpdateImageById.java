package ws.imagen.consulta;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ws.UploadFiles.servicios.ServicioActualizarImagenReferencia;
import ws.imagen.comando.ComandoSolicitudUpdateImagen;
import ws.imagen.comando.fabrica.FabricaSolicitudUpdateImagenReferencia;
import ws.product.servicios.ServiciosUpdateProduct;

@Component
public class ManejadorUpdateImageById {
    private final ServicioActualizarImagenReferencia serviceUpdateImagen;
    private final FabricaSolicitudUpdateImagenReferencia fabrica;
    public ManejadorUpdateImageById(ServicioActualizarImagenReferencia serviceUpdateImagen, FabricaSolicitudUpdateImagenReferencia fabrica) {
        this.serviceUpdateImagen = serviceUpdateImagen;
        this.fabrica = fabrica;
    }

    public Boolean ejecutar(ComandoSolicitudUpdateImagen comando){
        return  this.serviceUpdateImagen.ejecutar(this.fabrica.crear(comando));
    }
}
