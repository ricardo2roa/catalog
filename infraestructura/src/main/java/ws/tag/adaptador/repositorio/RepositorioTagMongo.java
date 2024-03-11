package ws.tag.adaptador.repositorio;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ws.tag.modelo.dto.TagDTO;
import ws.tag.modelo.entidad.Tag;
import ws.tag.puerto.repositorio.RepositorioTag;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class RepositorioTagMongo implements RepositorioTag {
    private final MongoTemplate mongoTemplate;

    public RepositorioTagMongo(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Tag obtenerByCode(int code) {
        Criteria criteria = Criteria.where("code").is(code)
                .and("locked").is(false)
                .and("disabled").is(false);
        Query query = new Query(criteria);
        TagDTO tagDto = this.mongoTemplate.findOne(query, TagDTO.class);
        return Tag.recrear(tagDto.getCode(), tagDto.getName(), tagDto.getLocked(),
                tagDto.getDisabled(), tagDto.getDateCreated());
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
    public Map<Integer, TagDTO> obtenerListByCodes(Set<Integer> codes) {
        Criteria criteria = new Criteria();
        if(codes.size() > 1){
            var list = codes.stream().map(item->Criteria.where("code").is(item)).toList();
            criteria.orOperator(list);
        }else{
            var codeList = codes.stream().toList();
            criteria = Criteria.where("code").is(codeList.get(0));
        }
        Query query = new Query(criteria);
        var tagDTO = this.mongoTemplate.find(query, TagDTO.class);
        return tagDTO.stream().collect(Collectors.toMap(TagDTO::getCode, Function.identity()));
    }

    @Override
    public Map<Integer, Tag> obtenerlistValrep() {
        var tagDTO = this.mongoTemplate.findAll(TagDTO.class);
        return tagDTO.stream().collect(Collectors.toMap(TagDTO::getCode, dto -> Tag.recrear(dto)));
    }

    @Override
    public int calcularCode() {
        return (int)(this.mongoTemplate.count(new Query(),"tags") + 1);
    }
}
