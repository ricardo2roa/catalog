package ws.category.comando.fabrica;

import org.springframework.stereotype.Component;
import ws.category.comando.ComandoSolicitudUpdateCategory;
import ws.category.modelo.entidad.SolicitudUpdateCategory;

@Component
public class FabricaUpdateCategory {
    public SolicitudUpdateCategory crear(ComandoSolicitudUpdateCategory comandoCategory){
        return new SolicitudUpdateCategory(comandoCategory.getCode(), comandoCategory.getName(), comandoCategory.getLocked(),
        comandoCategory.getDisabled(), comandoCategory.getDateCreated());
    }
}
