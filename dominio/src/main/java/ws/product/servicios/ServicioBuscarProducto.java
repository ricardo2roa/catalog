package ws.product.servicios;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import ws.brand.modelo.entidad.Brand;
import ws.brand.puerto.repositorio.RepositorioBrand;
import ws.category.modelo.entidad.Category;
import ws.category.puerto.repositorio.RepositorioCategory;
import ws.product.modelo.entidad.Product;
import ws.product.puerto.repositorio.RepositorioProduct;
import ws.reference.modelo.entidad.Reference;
import ws.reference.puerto.repositorio.RepositorioReference;
import ws.sort.modelo.dto.SortFieldDTO;
import ws.tag.modelo.entidad.Tag;
import ws.tag.puerto.repositorio.RepositorioTag;

import java.util.Comparator;
import java.util.List;

public class ServicioBuscarProducto {
    private static final int PAGE_SIZE = 10;
    private final RepositorioProduct repositorioProduct;
    private final RepositorioCategory repositorioCategory;
    private final RepositorioBrand repositorioBrand;
    private final RepositorioTag repositorioTag;
    private final RepositorioReference repositorioReference;

    public ServicioBuscarProducto(RepositorioProduct repositorioProduct, RepositorioCategory repositorioCategory, RepositorioBrand repositorioBrand, RepositorioTag repositorioTag, RepositorioReference repositorioReference) {
        this.repositorioProduct = repositorioProduct;
        this.repositorioCategory = repositorioCategory;
        this.repositorioBrand = repositorioBrand;
        this.repositorioTag = repositorioTag;
        this.repositorioReference = repositorioReference;
    }

    /*public List<Product> buscarProductos(){
        return this.repositorioProduct.getProducts();/*.stream().map(product -> {
            Category category = this.repositorioCategory.obtenerByCode(product.getCategory());
            Brand brand = this.repositorioBrand.obtenerByCode(product.getBrand());
            Tag tag = this.repositorioTag.obtenerByCode(product.getTag());
            return Product.recrear(product.getId(),product.getCode(),tag,category,brand,
                    product.getName(), product.getInformation(),
                    product.getReferences());
        }).toList();
    }*/

    public PageImpl<Product> buscarProductosPages(int page, String searchText, SortFieldDTO sort, List<String> tagFilters,
                                                  List<String> categoryFilters, List<String> brandFilters){
        var allProducts = this.repositorioProduct.getProducts(page,searchText,tagFilters,categoryFilters,brandFilters).stream().map(product ->{
            Category category = this.repositorioCategory.obtenerByCode(product.getCategory());
            Brand brand = this.repositorioBrand.obtenerByCode(product.getBrand());
            Tag tag = this.repositorioTag.obtenerByCode(product.getTag());
            List<Reference> references = product.getReferences().stream().map(reference->this.repositorioReference.buscarPorId(reference)).toList();
            return Product.recrear(product.getId(),product.getCode(),tag,category,brand,
                    product.getName(), product.getInformation(),
                    references);
        }).toList();

        Comparator<Product> comparator = null;
        if(sort.getField().equals("name")){
            comparator = Comparator.comparing(product -> product.getName().toLowerCase());
        }else if(sort.getField().equals("tag")){
            comparator = Comparator.comparing(product -> product.getFullTag().getName().toLowerCase());
        } else if (sort.getField().equals("category")) {
            comparator = Comparator.comparing(product -> product.getFullCategory().getName().toLowerCase());
        } else if (sort.getField().equals("brand")) {
            comparator = Comparator.comparing(product -> product.getFullBrand().getName().toLowerCase());
        }

        if (sort.getOrder() < 0 && comparator != null) {
            comparator = comparator.reversed();
        }

        if(comparator != null) {
            allProducts = allProducts.stream().sorted(comparator).toList();
        }

        var size = this.repositorioProduct.calcularCode() - 1;
        return new PageImpl<>(
                allProducts,
                PageRequest.of(page,PAGE_SIZE),
                size
        );
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

         Product product = this.repositorioProduct.getProduct(id);

         Category category = this.repositorioCategory.obtenerByCode(product.getCategory());
         Brand brand = this.repositorioBrand.obtenerByCode(product.getBrand());
         Tag tag = this.repositorioTag.obtenerByCode(product.getTag());
         List<Reference> references = product.getReferences().stream().map(reference->this.repositorioReference.buscarPorId(reference)).toList();

         return Product.recrear(product.getId(),product.getCode(),tag,category,brand,
                    product.getName(), product.getInformation(),
                    references);

    }
    public int count(){
        return (this.repositorioProduct.calcularCode() - 1);
    }
}
