package ws.tag.consulta;

import org.springframework.stereotype.Component;
import ws.tag.modelo.entidad.Tag;
import ws.tag.puerto.repositorio.RepositorioTag;

import java.util.Map;

@Component
public class ManejadorGetValrepTag {
    private final RepositorioTag repositorioTag;

    public ManejadorGetValrepTag(RepositorioTag repositorioTag) {
        this.repositorioTag = repositorioTag;
    }

    public Map<Integer, Tag> ejecutar(){
        return this.repositorioTag.obtenerlistValrep();
    }
}
