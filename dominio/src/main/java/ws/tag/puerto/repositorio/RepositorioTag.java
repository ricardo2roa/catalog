package ws.tag.puerto.repositorio;

import ws.tag.modelo.dto.TagDTO;
import ws.tag.modelo.entidad.Tag;

import java.util.Map;
import java.util.Set;

public interface RepositorioTag {
    Tag obtenerByCode(int code);
    int guardar(Tag tag);
    Map<Integer, TagDTO> obtenerListByCodes(Set<Integer> codes);
    int calcularCode();
}
