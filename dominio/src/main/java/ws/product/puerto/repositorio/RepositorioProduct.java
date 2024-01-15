package ws.product.puerto.repositorio;


import ws.product.modelo.dto.ProductDTO;
import ws.product.modelo.entidad.Product;

import java.util.List;

public interface RepositorioProduct{
    String guardar(Product product);
    int calcularCode();
    List<ProductDTO> getProducts();
}
