package ws.tag.servicios;

import ws.tag.modelo.dto.TagDTO;
import ws.tag.puerto.repositorio.RepositorioTag;

import java.util.Map;
import java.util.Set;

public class ServicioConsultarTag {
    private final RepositorioTag repositorioTag;

    public ServicioConsultarTag(RepositorioTag repositorioTag) {
        this.repositorioTag = repositorioTag;
    }

    public Map<Integer, TagDTO> getTagsFor(Set<Integer> codes){
        return this.repositorioTag.obtenerListByCodes(codes);
    }
}
