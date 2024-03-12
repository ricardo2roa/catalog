package ws.tag.controlador;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ws.brand.modelo.entidad.DateFilter;
import ws.category.modelo.entidad.Category;
import ws.infraestructura.exception.modelo.ErrorParserStringToDateException;
import ws.sort.modelo.dto.SortFieldDTO;
import ws.tag.consulta.ManejadorConsultarTags;
import ws.tag.modelo.entidad.Tag;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("v1/tags")
public class ComandoConsultarAllTags {
    private final ManejadorConsultarTags manejadorConsultarTags;

    public ComandoConsultarAllTags(ManejadorConsultarTags manejadorConsultarTags) {
        this.manejadorConsultarTags = manejadorConsultarTags;
    }

    @GetMapping
    public ResponseEntity<Page<Tag>> consultarTodasLasTags(@RequestParam(required = false, defaultValue = "0") int page,
                                                                 @RequestParam(required = false, defaultValue = "") String searchText,
                                                                 @RequestParam(required = false, defaultValue = "name") String sortField,
                                                                 @RequestParam(required = false, defaultValue = "1") String sortOrder,
                                                                 @RequestParam(required = false, defaultValue = "") String nameFilter,
                                                                 @RequestParam(required = false, defaultValue = "") String lockedFilter,
                                                                 @RequestParam(required = false, defaultValue = "") String disabledFilter,
                                                                 @RequestParam(required = false, defaultValue = "") String dateFilter){
        List<String> nameFilters = parsertoList(nameFilter);
        List<String> lockedFilters = parsertoList(lockedFilter);
        List<String> disabledFilters = parsertoList(disabledFilter);
        List<DateFilter> dateFilters = parsertoListDate(dateFilter);

        SortFieldDTO sort = new SortFieldDTO(sortField,Integer.parseInt(sortOrder));
        return ResponseEntity.ok(this.manejadorConsultarTags.ejecutar(page,searchText,sort,nameFilters,lockedFilters,
                disabledFilters,dateFilters));
    }

    private static List<String> parsertoList(String filter){
        if(!filter.isEmpty()) {
            return Arrays.stream(filter.split(",")).toList();
        }else{
            return new ArrayList<>();
        }
    }

    private static List<DateFilter> parsertoListDate(String filter){
        if(!filter.isEmpty()) {
            return Arrays.stream(filter.split(",")).map(element-> stringToDate(element.split("option:")[0],
                    element.split("option:")[1])).toList();
        }else{
            return new ArrayList<>();
        }
    }

    private static DateFilter stringToDate(String date,String option){
        //String dateString = "2024-03-15T05:00:00.000Z";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date dateResponse;
        try {
            dateResponse = formatter.parse(date);
        } catch (ParseException e) {
            throw new ErrorParserStringToDateException("Error al convertir de String a Date");
        }
        return new DateFilter(dateResponse,option.trim());
    }
}
