package ws.reference.adaptador.repositorio;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ws.brand.modelo.dto.BrandWrite;
import ws.category.modelo.dto.CategoryWrite;
import ws.product.modelo.dto.ProductWrite;
import ws.product.modelo.entidad.Product;
import ws.reference.modelo.dto.ReferenceDTO;
import ws.reference.modelo.entidad.Reference;
import ws.reference.puerto.repositorio.RepositorioReference;
import ws.tag.modelo.dto.TagWrite;

import java.util.List;

@Repository
public class RepositorioReferenceMongo implements RepositorioReference {
    private final MongoTemplate mongoTemplate;

    public RepositorioReferenceMongo(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Reference buscarPorId(String id) {
        Criteria criteria = Criteria.where("id").is(id);
        Query query = new Query(criteria);
        return Reference.recrear(this.mongoTemplate.findOne(query,ReferenceDTO.class,"references"));
    }

    @Override
    public String guardar(ReferenceDTO reference) {
        ReferenceDTO referenceDTO = this.mongoTemplate.save(reference,"references");
        return referenceDTO.getId();
    }
    @Override
    public String obtenerInfoParaSKUByIdReferencia(Reference reference){
        Criteria criteria = Criteria.where("references").is(reference.getId());
        Query query = new Query(criteria);
        ProductWrite product = this.mongoTemplate.findOne(query, ProductWrite.class);

        criteria = Criteria.where("code").is(product.getBrand());
        query = new Query(criteria);
        BrandWrite brand = this.mongoTemplate.findOne(query, BrandWrite.class);

        return Product.generarSKUReference(brand.getName(), product.getName(), product.getCode(), reference);
    }

}
