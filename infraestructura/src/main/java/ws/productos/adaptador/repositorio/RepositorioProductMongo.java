package ws.productos.adaptador.repositorio;

import lombok.extern.java.Log;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ws.brand.modelo.entidad.Brand;
import ws.category.modelo.entidad.Category;
import ws.information.modelo.dto.InformationDTO;
import ws.information.modelo.entidad.Information;
import ws.product.modelo.dto.ProductDTO;
import ws.product.modelo.entidad.Product;
import ws.product.puerto.repositorio.RepositorioProduct;
import ws.reference.modelo.dto.ImageReferenceInfoDTO;
import ws.reference.modelo.entidad.Reference;
import ws.tag.modelo.entidad.Tag;

import java.util.Date;
import java.util.List;

@Repository
@Log
public class RepositorioProductMongo implements RepositorioProduct {

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
    public List<Product> getProducts() {
        Query query = new Query();
        query.with(Sort.by(Sort.Order.asc("name")));
        return this.mongoTemplate.find(query, Product.class,"products");
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
    public ProductDTO updateProduct(Product product) {
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
        return this.mongoTemplate.findAndModify(query,update, ProductDTO.class, "products");
    }

}
