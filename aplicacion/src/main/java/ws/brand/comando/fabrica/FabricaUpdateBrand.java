package ws.brand.comando.fabrica;

import org.springframework.stereotype.Component;
import ws.brand.comando.ComandoUpdateBrand;
import ws.brand.modelo.entidad.SolicitudUpdateBrand;

@Component
public class FabricaUpdateBrand {
    public SolicitudUpdateBrand crear(ComandoUpdateBrand comandoUpdateBrand){
        return new SolicitudUpdateBrand(comandoUpdateBrand.getCode(),
                comandoUpdateBrand.getName(),
                comandoUpdateBrand.getLocked(),
                comandoUpdateBrand.getDisabled(),
                comandoUpdateBrand.getDateCreated());
    }
}
