package ws.tag.servicios;

import ws.tag.modelo.entidad.SolicitudUpdateTag;
import ws.tag.puerto.repositorio.RepositorioTag;

public class ServicioUpdateTag {
    private final RepositorioTag repositorioTag;

    public ServicioUpdateTag(RepositorioTag repositorioTag) {
        this.repositorioTag = repositorioTag;
    }

    public void ejecutar(SolicitudUpdateTag tag, String id){
        this.repositorioTag.updateEtiqueta(tag, id);
    }
}
