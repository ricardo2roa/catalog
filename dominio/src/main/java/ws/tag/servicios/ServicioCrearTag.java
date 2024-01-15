package ws.tag.servicios;

import ws.tag.modelo.entidad.SolicitudCrearTag;
import ws.tag.modelo.entidad.Tag;
import ws.tag.puerto.repositorio.RepositorioTag;

public class ServicioCrearTag {
    private final RepositorioTag repositorioTag;

    public ServicioCrearTag(RepositorioTag repositorioTag) {
        this.repositorioTag = repositorioTag;
    }

    public int ejecutar(SolicitudCrearTag solicitudCrearTag){
        int code = this.repositorioTag.calcularCode();
        Tag tag = Tag.crear(code,solicitudCrearTag.getName(),solicitudCrearTag.getLocked(),
                solicitudCrearTag.getDisabled());
        return this.repositorioTag.guardar(tag);
    }
}
