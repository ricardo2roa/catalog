package ws.category.comando.fabrica;

import org.springframework.stereotype.Component;
import ws.category.comando.ComandoSolicitudCrearCategory;
import ws.category.modelo.entidad.SolicitudCrearCategory;

@Component
public class FabricaSolicitudCrearCategory {
    public SolicitudCrearCategory crear(ComandoSolicitudCrearCategory comandoSolicitudCrearCategory){
        return new SolicitudCrearCategory(
                comandoSolicitudCrearCategory.getName(), comandoSolicitudCrearCategory.getLocked(),
                comandoSolicitudCrearCategory.getDisabled()
        );
    }
}
