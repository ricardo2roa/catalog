package ws.product.consulta;

import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import ws.product.modelo.entidad.Product;
import ws.product.servicios.ServicioBuscarProducto;
import ws.sort.modelo.dto.SortFieldDTO;

import java.util.List;

@Component
public class ManejadorBuscarProductosPages {
    private final ServicioBuscarProducto servicioBuscarProducto;

    public ManejadorBuscarProductosPages(ServicioBuscarProducto servicioBuscarProducto) {
        this.servicioBuscarProducto = servicioBuscarProducto;
    }

    public PageImpl<Product> ejecutar(int page, String searchText, SortFieldDTO sort, List<String> tagFilters,
                                      List<String> categoryFilters, List<String> brandFilters){
        return this.servicioBuscarProducto.buscarProductosPages(page,searchText,sort,tagFilters,categoryFilters,brandFilters);
    }
}
