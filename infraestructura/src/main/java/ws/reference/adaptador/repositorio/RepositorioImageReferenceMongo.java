package ws.reference.adaptador.repositorio;

import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.stereotype.Repository;
import ws.reference.modelo.dto.ImageReferenceInfoDTO;
import ws.reference.modelo.entidad.ImageReferenceInfo;
import ws.reference.puerto.repositorio.RepositorioImageReference;

import java.util.Date;

@Repository
public class RepositorioImageReferenceMongo implements RepositorioImageReference {
    private final MongoTemplate mongoTemplate;

    public RepositorioImageReferenceMongo(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public String registrarImagen(ImageReferenceInfo imageReferenceInfo) {
        var imageReferenceDto = new ImageReferenceInfoDTO(
                imageReferenceInfo.getId(),imageReferenceInfo.getIdReference(),
                imageReferenceInfo.getName(), imageReferenceInfo.getDateModify());

        Criteria criteria = Criteria.where("idReference")
                .is(imageReferenceDto.getIdReference());
                        /*("_id")
                .is(imageReferenceDto.getId())*/
                //.and("idReference").is(imageReferenceDto.getIdReference());
        Query query = new Query(criteria);
        Update update = new Update();
        update.set("name",imageReferenceDto.getName());
        update.set("dateModify",new Date());
        var imageReference = this.mongoTemplate.findAndModify(query,update, ImageReferenceInfoDTO.class);
        if( imageReference == null) imageReference = this.mongoTemplate.insert(imageReferenceDto, "imagesReference");
        return imageReference.getName();
    }

    @Override
    public String obtenerNameByIdReference(String id) {
        Criteria criteria = Criteria.where("idReference").is(id);
        Query query = new Query(criteria);
        ImageReferenceInfoDTO response = this.mongoTemplate.findOne(query,ImageReferenceInfoDTO.class);
        return response.getName();
    }

    @Override
    public ImageReferenceInfo obtenerImagenInfoByIdReference(String id) {
        Criteria criteria = Criteria.where("idReference").is(id);
        Query query = new Query(criteria);
        ImageReferenceInfoDTO response = this.mongoTemplate.findOne(query,ImageReferenceInfoDTO.class);
        return ImageReferenceInfo.recrear(response);
    }
}
