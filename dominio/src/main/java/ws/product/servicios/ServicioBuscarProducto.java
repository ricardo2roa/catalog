package ws.product.servicios;

import ws.brand.modelo.entidad.Brand;
import ws.brand.puerto.repositorio.RepositorioBrand;
import ws.category.modelo.entidad.Category;
import ws.category.puerto.repositorio.RepositorioCategory;
import ws.information.modelo.entidad.Information;
import ws.product.modelo.dto.ProductDTO;
import ws.product.modelo.entidad.Product;
import ws.product.puerto.repositorio.RepositorioProduct;
import ws.reference.modelo.entidad.Reference;
import ws.tag.modelo.entidad.Tag;
import ws.tag.puerto.repositorio.RepositorioTag;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ServicioBuscarProducto {
    private final RepositorioProduct repositorioProduct;

    public ServicioBuscarProducto(RepositorioProduct repositorioProduct) {
        this.repositorioProduct = repositorioProduct;
    }

    public List<Product> buscarProductos(){
        return this.repositorioProduct.getProducts();/*.stream().map(product -> {
            Category category = this.repositorioCategory.obtenerByCode(product.getCategory());
            Brand brand = this.repositorioBrand.obtenerByCode(product.getBrand());
            Tag tag = this.repositorioTag.obtenerByCode(product.getTag());
            return Product.recrear(product.getId(),product.getCode(),tag,category,brand,
                    product.getName(), product.getInformation(),
                    product.getReferences());
        }).toList();*/
    }

    public List<Product> buscarProductos(String id){
        return this.repositorioProduct.getProducts(id);/*.stream().map(product -> {
            Category category = this.repositorioCategory.obtenerByCode(product.getCategory());
            Brand brand = this.repositorioBrand.obtenerByCode(product.getBrand());
            Tag tag = this.repositorioTag.obtenerByCode(product.getTag());
            return Product.recrear(product.getId(),product.getCode(),tag,category,brand,
                    product.getName(), product.getInformation(),
                    product.getReferences());
        }).toList();*/
    }

    public Product buscarProduct(String id){
        return this.repositorioProduct.getProduct(id);
    }
    public int count(){
        return (this.repositorioProduct.calcularCode() - 1);
    }
}
