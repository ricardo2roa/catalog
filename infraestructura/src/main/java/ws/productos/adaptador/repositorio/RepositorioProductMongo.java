package ws.productos.adaptador.repositorio;

import lombok.extern.java.Log;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ws.brand.modelo.dto.BrandRead;
import ws.brand.modelo.entidad.Brand;
import ws.information.modelo.dto.InformationDTO;
import ws.product.modelo.dto.ProductDTO;
import ws.product.modelo.dto.ProductRead;
import ws.product.modelo.entidad.Product;
import ws.product.puerto.repositorio.RepositorioProduct;

import java.util.ArrayList;
import java.util.List;

@Repository
@Log
public class RepositorioProductMongo implements RepositorioProduct {
    private final int SIZE_PAGE = 10;
    private final MongoTemplate mongoTemplate;

    public RepositorioProductMongo(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public String guardar(Product product) {
        Product productSave = this.mongoTemplate.save(product,"products");
        return productSave.getId();
    }

    @Override
    public int calcularCode() {
        return (int)( this.mongoTemplate.count(new Query(),"products") + 1);
    }

    @Override
    public List<Product> getProducts(int page, String searchText, List<String> tagFilters, List<String> categoryFilters, List<String> brandFilters) {
        Criteria criteria = new Criteria();

        List<Criteria> filters = new ArrayList<>();

        Criteria tagCriteria = new Criteria();
        Criteria categoryCriteria = new Criteria();
        Criteria brandCriteria = new Criteria();
        //Criteria datesCriteria = new Criteria();

        if(!tagFilters.isEmpty()){
            List<Criteria> filtersTag = new ArrayList<>(tagFilters.stream()
                    .map(tag -> Criteria.where("tag")
                            .is(Integer.valueOf(tag))).toList());
            tagCriteria.orOperator(filtersTag);
            filters.add(tagCriteria);
        }
        if(!categoryFilters.isEmpty()){
            List<Criteria> filtersLocked = new ArrayList<>(categoryFilters.stream()
                    .map(category -> Criteria.where("category")
                            .is(Integer.valueOf(category))).toList());
            categoryCriteria.orOperator(filtersLocked);
            filters.add(categoryCriteria);
        }
        if(!brandFilters.isEmpty()){
            List<Criteria> filtersDisabled = new ArrayList<>(brandFilters.stream()
                    .map(brand -> Criteria.where("brand")
                            .is(Integer.valueOf(brand))).toList());
            brandCriteria.orOperator(filtersDisabled);
            filters.add(brandCriteria);
        }

        if(!filters.isEmpty()) criteria.andOperator(filters);

        if(!searchText.isEmpty()){
            criteria.andOperator(Criteria.where("name").regex(searchText,"i"));
        }
        Query query = new Query(criteria);
        var offset = ((long) page * SIZE_PAGE);
        query.skip(offset);
        query.limit(SIZE_PAGE);

        return this.mongoTemplate.find(query, ProductRead.class).stream()
                .map(Product::recrear).toList();
    }

    @Override
    public List<Product> getProducts(String id) {
        Criteria criteria = Criteria.where("id").gt(id);
        Query query = new Query(criteria);
        query.with(Sort.by(Sort.Order.asc("name")));
        return this.mongoTemplate.find(query,Product.class,"products");
    }

    @Override
    public Product getProduct(String id) {
        Criteria criteria = Criteria.where("id").is(id);
        Query query = new Query(criteria);
        return this.mongoTemplate.findOne(query,Product.class,"products");
    }

    @Override
    public Product updateProduct(Product product) {
        Criteria criteria = Criteria.where("_id").is(product.getId());
        Query query = new Query(criteria);
        Update update = new Update();
        update.set("tag",product.getTag());
        update.set("category",product.getCategory());
        update.set("brand",product.getBrand());
        update.set("name",product.getName());
        update.set("information",new InformationDTO(
                product.getInformation().getBenefits(),
                product.getInformation().getFeature(),
                product.getInformation().getDescription()));
        update.set("references",product.getReferences());
        ProductRead response = this.mongoTemplate.findAndModify(query,update, ProductRead.class);
        return Product.recrear(response);
    }

}
