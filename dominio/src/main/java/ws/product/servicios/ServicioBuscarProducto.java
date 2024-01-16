package ws.product.servicios;

import ws.product.modelo.dto.ProductDTO;
import ws.product.puerto.repositorio.RepositorioProduct;

import java.util.List;

public class ServicioBuscarProducto {
    private final RepositorioProduct repositorioProduct;

    public ServicioBuscarProducto(RepositorioProduct repositorioProduct) {
        this.repositorioProduct = repositorioProduct;
    }

    public List<ProductDTO> buscarProductos(){
        return this.repositorioProduct.getProducts();
    }

    public List<ProductDTO> buscarProductos(String id){
        return this.repositorioProduct.getProducts(id);
    }

    public int count(){
        return (this.repositorioProduct.calcularCode() - 1);
    }
}
