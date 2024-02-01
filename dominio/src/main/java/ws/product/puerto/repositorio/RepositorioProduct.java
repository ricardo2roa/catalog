package ws.product.puerto.repositorio;


import ws.product.modelo.dto.ProductDTO;
import ws.product.modelo.entidad.Product;

import java.util.List;

public interface RepositorioProduct{
    String guardar(Product product);
    int calcularCode();
    List<Product> getProducts();
    List<Product> getProducts(String id);
    Product getProduct(String id);
    ProductDTO updateProduct(Product product);
}
