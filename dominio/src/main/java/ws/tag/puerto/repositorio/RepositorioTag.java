package ws.tag.puerto.repositorio;

import ws.brand.modelo.entidad.Brand;
import ws.brand.modelo.entidad.DateFilter;
import ws.brand.modelo.entidad.SolicitudUpdateBrand;
import ws.tag.modelo.dto.TagDTO;
import ws.tag.modelo.entidad.SolicitudUpdateTag;
import ws.tag.modelo.entidad.Tag;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RepositorioTag {
    Tag obtenerByCode(int code);
    int guardar(Tag tag);
    //Map<Integer, TagDTO> obtenerListByCodes(Set<Integer> codes);
    Map<Integer,Tag> obtenerlistValrep();
    int calcularCode();
    List<Tag> obtenerTodasLasEtiquetas(int page, String searchText, List<String> nameFilters, List<String> lockedFilters,
                                      List<String> disabledFilters, List<DateFilter> dateFilters);

    void updateEtiqueta(SolicitudUpdateTag tag, String id);
}
