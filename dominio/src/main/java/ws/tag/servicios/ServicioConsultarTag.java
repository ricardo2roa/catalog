package ws.tag.servicios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import ws.brand.modelo.entidad.DateFilter;
import ws.sort.modelo.dto.SortFieldDTO;
import ws.tag.modelo.entidad.Tag;
import ws.tag.puerto.repositorio.RepositorioTag;

import java.util.Comparator;
import java.util.List;

public class ServicioConsultarTag {
    private static final int PAGE_SIZE = 10;
    private final RepositorioTag repositorioTag;

    public ServicioConsultarTag(RepositorioTag repositorioTag) {
        this.repositorioTag = repositorioTag;
    }

    public Page<Tag> ejecutar(int numberPage, String searchText, SortFieldDTO sort,
                              List<String> nameFilters, List<String> lockedFilters, List<String> disabledFilters,
                              List<DateFilter> dateFilters){

        List<Tag> allTags = this.repositorioTag.obtenerTodasLasEtiquetas(numberPage, searchText, nameFilters, lockedFilters,
                disabledFilters, dateFilters);

        Comparator<Tag> comparator = null;
        if(sort.getField().equals("name")){
            comparator = Comparator.comparing(tag -> tag.getName().toLowerCase());
        }else if(sort.getField().equals("dateCreated")){
            comparator = Comparator.comparing(Tag::getDateCreated);
        } else if (sort.getField().equals("locked")) {
            comparator = Comparator.comparing(Tag::getLocked);
        } else if (sort.getField().equals("disabled")) {
            comparator = Comparator.comparing(Tag::getDisabled);
        }

        if (sort.getOrder() < 0 && comparator != null) {
            comparator = comparator.reversed();
        }

        if(comparator != null) {
            allTags = allTags.stream().sorted(comparator).toList();
        }

        var size = this.repositorioTag.calcularCode() - 1;
        return new PageImpl<>(
                allTags,
                PageRequest.of(numberPage,PAGE_SIZE),
                size
        );
    }
}
