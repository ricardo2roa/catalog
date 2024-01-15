package ws.tag.adaptador.repositorio;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ws.tag.modelo.dto.TagDTO;
import ws.tag.modelo.entidad.Tag;
import ws.tag.puerto.repositorio.RepositorioTag;

@Repository
public class RepositorioTagMongo implements RepositorioTag {
    private final MongoTemplate mongoTemplate;

    public RepositorioTagMongo(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public int guardar(Tag tag) {
        TagDTO tagNuevo = this.mongoTemplate.save(new TagDTO(
                tag.getCode(),
                tag.getName(),
                tag.getLocked(),
                tag.getDisabled(),
                tag.getDateCreated()
        ));
        return tagNuevo.getCode();
    }

    @Override
    public int calcularCode() {
        return (int)(this.mongoTemplate.count(new Query(),"tags") + 1);
    }
}
