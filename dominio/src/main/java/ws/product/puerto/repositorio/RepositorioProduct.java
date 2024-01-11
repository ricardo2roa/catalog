package ws.product.puerto.repositorio;


import ws.product.modelo.entidad.Product;

public interface RepositorioProduct{
    String guardar(Product product);
    int calcularCode();
}
