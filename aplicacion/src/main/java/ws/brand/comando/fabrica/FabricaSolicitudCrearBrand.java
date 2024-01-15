package ws.brand.comando.fabrica;

import org.springframework.stereotype.Component;
import ws.brand.comando.ComandoSolicitudCrearBrand;
import ws.brand.modelo.entidad.SolicitudCrearBrand;

@Component
public class FabricaSolicitudCrearBrand {
    public SolicitudCrearBrand crear(ComandoSolicitudCrearBrand comandoSolicitudCrearBrand){
        return new SolicitudCrearBrand(
                comandoSolicitudCrearBrand.getName(), comandoSolicitudCrearBrand.getLocked(),
                comandoSolicitudCrearBrand.getDisabled()
        );
    }
}
