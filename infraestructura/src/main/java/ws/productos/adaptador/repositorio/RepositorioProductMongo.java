package ws.productos.adaptador.repositorio;

import lombok.extern.java.Log;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ws.product.modelo.dto.ProductDTO;
import ws.product.modelo.entidad.Product;
import ws.product.puerto.repositorio.RepositorioProduct;

@Repository
@Log
public class RepositorioProductMongo implements RepositorioProduct {

    private final MongoTemplate mongoTemplate;

    public RepositorioProductMongo(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public String guardar(Product product) {

        ProductDTO productSave = this.mongoTemplate.save(new ProductDTO(
                product.getCode(),
                product.getTag(),
                product.getCategory(),
                product.getBrand(),
                product.getName(),
                product.getInformation(),
                product.getReferences()
        ));
        return productSave.getId();
    }

    @Override
    public int calcularCode() {
        //log.info("codigo: "+(this.mongoTemplate.count(new Query(),"products") + 1));
        return (int)( this.mongoTemplate.count(new Query(),"products") + 1);
    }
}
