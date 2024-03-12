package ws.tag.comando.fabrica;

import org.springframework.stereotype.Component;
import ws.tag.comando.ComandoUpdateTag;
import ws.tag.modelo.entidad.SolicitudUpdateTag;

@Component
public class FabricaSolicitudUpdateTag {
    public SolicitudUpdateTag crear(ComandoUpdateTag comandoUpdateTag){
        return new SolicitudUpdateTag(comandoUpdateTag.getCode(), comandoUpdateTag.getName(), comandoUpdateTag.getLocked(),
                comandoUpdateTag.getDisabled(),comandoUpdateTag.getDateCreated());
    }
}
