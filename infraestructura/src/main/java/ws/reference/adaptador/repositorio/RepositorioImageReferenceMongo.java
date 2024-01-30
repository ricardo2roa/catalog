package ws.reference.adaptador.repositorio;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ws.reference.modelo.dto.ImageReferenceInfo;
import ws.reference.puerto.repositorio.RepositorioImageReference;

@Repository
public class RepositorioImageReferenceMongo implements RepositorioImageReference {
    private final MongoTemplate mongoTemplate;

    public RepositorioImageReferenceMongo(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public String registrarImagen(ImageReferenceInfo imageReferenceInfo) {
        var imageReference = this.mongoTemplate.save(imageReferenceInfo,"imagesReference");
        return imageReference.getPath();
    }
}
