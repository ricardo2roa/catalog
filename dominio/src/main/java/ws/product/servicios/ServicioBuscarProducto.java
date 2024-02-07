package ws.product.servicios;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import ws.brand.modelo.entidad.Brand;
import ws.brand.puerto.repositorio.RepositorioBrand;
import ws.category.modelo.entidad.Category;
import ws.category.puerto.repositorio.RepositorioCategory;
import ws.product.modelo.entidad.Product;
import ws.product.puerto.repositorio.RepositorioProduct;
import ws.tag.modelo.entidad.Tag;
import ws.tag.puerto.repositorio.RepositorioTag;

import java.util.List;

public class ServicioBuscarProducto {
    private final RepositorioProduct repositorioProduct;
    private final RepositorioCategory repositorioCategory;
    private final RepositorioBrand repositorioBrand;
    private final RepositorioTag repositorioTag;

    public ServicioBuscarProducto(RepositorioProduct repositorioProduct, RepositorioCategory repositorioCategory, RepositorioBrand repositorioBrand, RepositorioTag repositorioTag) {
        this.repositorioProduct = repositorioProduct;
        this.repositorioCategory = repositorioCategory;
        this.repositorioBrand = repositorioBrand;
        this.repositorioTag = repositorioTag;
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

    public PageImpl<Product> buscarProductosPages(){
        var list = this.repositorioProduct.getProducts().stream().map(product ->{
            Category category = this.repositorioCategory.obtenerByCode(product.getCategory());
            Brand brand = this.repositorioBrand.obtenerByCode(product.getBrand());
            Tag tag = this.repositorioTag.obtenerByCode(product.getTag());
            return Product.recrear(product.getId(),product.getCode(),tag,category,brand,
                    product.getName(), product.getInformation(),
                    product.getReferences());
        }).toList();
        return new PageImpl<>(list);
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
