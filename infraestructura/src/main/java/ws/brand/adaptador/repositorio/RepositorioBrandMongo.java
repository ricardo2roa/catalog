package ws.brand.adaptador.repositorio;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ws.brand.modelo.dto.BrandDTO;
import ws.brand.modelo.entidad.Brand;
import ws.brand.puerto.repositorio.RepositorioBrand;

@Repository
public class RepositorioBrandMongo implements RepositorioBrand {
    private final MongoTemplate mongoTemplate;

    public RepositorioBrandMongo(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Brand obtenerByCode(int code) {
        return null;
    }

    @Override
    public Brand obtenerName(String name) {
        return null;
    }

    @Override
    public int guardar(Brand brand) {
        BrandDTO brandSave = this.mongoTemplate.save(new BrandDTO(
              brand.getCode(),
              brand.getName(),
              brand.getLocked(),
              brand.getDisabled(),
              brand.getDateCreated()
        ));
        return brandSave.getCode();
    }

    @Override
    public int calcularCode() {
        return (int)( this.mongoTemplate.count(new Query(),"brands") + 1);
    }
}
