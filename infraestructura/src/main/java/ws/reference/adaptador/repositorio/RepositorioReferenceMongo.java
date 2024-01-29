package ws.reference.adaptador.repositorio;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import ws.reference.modelo.dto.ReferenceDTO;
import ws.reference.modelo.entidad.Reference;
import ws.reference.puerto.repositorio.RepositorioReference;

@Repository
public class RepositorioReferenceMongo implements RepositorioReference {
    private final MongoTemplate mongoTemplate;

    public RepositorioReferenceMongo(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public String guardar(Reference reference) {
        ReferenceDTO referenceDTO = this.mongoTemplate.save(ReferenceDTO.convertir(reference));
        return referenceDTO.getId();
    }
}
