package ws.product.puerto.repositorio;

import ws.product.modelo.entidad.Product;
import ws.product.modelo.entidad.SolicitudUpdateProduct;

import java.util.List;

public interface RepositorioProduct{
    String guardar(Product product);
    int calcularCode();
    List<Product> getProducts(int page, String searchText, List<String> tagFilters, List<String> categoryFilters, List<String> brandFilters);
    List<Product> getProducts(String id);
    Product getProduct(String id);
    String updateProduct(SolicitudUpdateProduct product, String id);
}
