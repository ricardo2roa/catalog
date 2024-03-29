package ws.imagen.comando.fabrica;

import org.springframework.stereotype.Component;
import ws.UploadFiles.modelo.entidad.SolicitudUpdateImagenReferencia;
import ws.imagen.comando.ComandoSolicitudUpdateImagen;

@Component
public class FabricaSolicitudUpdateImagenReferencia {
    public SolicitudUpdateImagenReferencia crear(ComandoSolicitudUpdateImagen comando){
        return new SolicitudUpdateImagenReferencia(comando.getReferencia(), comando.getFile());
    }
}
