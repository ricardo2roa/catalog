package ws.productos.controlador;

import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ws.product.consulta.ManejadorBuscarProductosPages;
import ws.product.modelo.entidad.Product;
import ws.sort.modelo.dto.SortFieldDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/productos")
public class ComandoBuscarProductosPages {
    private final ManejadorBuscarProductosPages manejadorBuscarProductosPages;

    public ComandoBuscarProductosPages(ManejadorBuscarProductosPages manejadorBuscarProductosPages) {
        this.manejadorBuscarProductosPages = manejadorBuscarProductosPages;
    }

    @GetMapping
    public ResponseEntity<PageImpl<Product>> buscarProductosPages(@RequestParam(required = false, defaultValue = "0") int page,
                                                                  @RequestParam(required = false, defaultValue = "") String searchText,
                                                                  @RequestParam(required = false, defaultValue = "name") String sortField,
                                                                  @RequestParam(required = false, defaultValue = "1") String sortOrder,
                                                                  @RequestParam(required = false, defaultValue = "") String tagFilter,
                                                                  @RequestParam(required = false, defaultValue = "") String categoryFilter,
                                                                  @RequestParam(required = false, defaultValue = "") String brandFilter){
        List<String> tagFilters = parsertoList(tagFilter);
        List<String> categoryFilters = parsertoList(categoryFilter);
        List<String> brandFilters = parsertoList(brandFilter);

        SortFieldDTO sort = new SortFieldDTO(sortField,Integer.parseInt(sortOrder));
        return ResponseEntity.ok(this.manejadorBuscarProductosPages.ejecutar(page,searchText,sort,tagFilters,categoryFilters,brandFilters));
    }

    private static List<String> parsertoList(String filter){
        if(!filter.isEmpty()) {
            return Arrays.stream(filter.split(",")).toList();
        }else{
            return new ArrayList<>();
        }
    }
}
