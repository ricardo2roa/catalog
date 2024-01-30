package ws.reference.adaptador.repositorio;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ws.reference.modelo.dto.ReferenceDTO;
import ws.reference.modelo.entidad.Reference;
import ws.reference.puerto.repositorio.RepositorioReference;

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
}
