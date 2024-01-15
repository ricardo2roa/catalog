package ws.productos.controlador;

import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import ws.product.consulta.ManejadorBuscarProductos;
import ws.product.modelo.dto.ProductDTO;

import java.util.List;

@Controller
public class ComadoBuscarProductos {
    private final ManejadorBuscarProductos manejadorBuscarProductos;

    public ComadoBuscarProductos(ManejadorBuscarProductos manejadorBuscarProductos) {
        this.manejadorBuscarProductos = manejadorBuscarProductos;
    }

    /*@GetMapping("/hola")
    public Map<String,String> prueba(){
        Map<String,String> response = new HashMap<>();
        response.put("Hola","Hola");
        return response;
    }*/
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @SchemaMapping(typeName = "Query", value = "allProducts")
    public List<ProductDTO> findAll(){
        return this.manejadorBuscarProductos.ejecutar();
    }
}
