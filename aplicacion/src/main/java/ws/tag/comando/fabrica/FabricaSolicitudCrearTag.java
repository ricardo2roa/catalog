package ws.tag.comando.fabrica;

import org.springframework.stereotype.Component;
import ws.tag.comando.ComandoSolicitudCrearTag;
import ws.tag.modelo.entidad.SolicitudCrearTag;

@Component
public class FabricaSolicitudCrearTag {
    public SolicitudCrearTag crear(ComandoSolicitudCrearTag comandoSolicitudCrearTag){
        return new SolicitudCrearTag(comandoSolicitudCrearTag.getName(),
                comandoSolicitudCrearTag.getLocked(),comandoSolicitudCrearTag.getDisabled());
    }
}
