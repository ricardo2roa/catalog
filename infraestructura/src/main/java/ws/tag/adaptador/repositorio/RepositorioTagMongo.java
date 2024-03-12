package ws.tag.adaptador.repositorio;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ws.brand.modelo.entidad.DateFilter;
import ws.tag.modelo.dto.TagDTO;
import ws.tag.modelo.dto.TagRead;
import ws.tag.modelo.dto.TagUpdate;
import ws.tag.modelo.entidad.SolicitudUpdateTag;
import ws.tag.modelo.entidad.Tag;
import ws.tag.puerto.repositorio.RepositorioTag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class RepositorioTagMongo implements RepositorioTag {
    private static final int SIZE_PAGE = 10;
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

    /*@Override
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
    }*/

    @Override
    public Map<Integer, Tag> obtenerlistValrep() {
        var tagDTO = this.mongoTemplate.findAll(TagRead.class);
        return tagDTO.stream().collect(Collectors.toMap(TagRead::getCode, Tag::recrear));
    }

    @Override
    public int calcularCode() {
        return (int)(this.mongoTemplate.count(new Query(),"tags") + 1);
    }

    @Override
    public List<Tag> obtenerTodasLasEtiquetas(int page, String searchText, List<String> nameFilters, List<String> lockedFilters, List<String> disabledFilters, List<DateFilter> dateFilters) {
        Criteria criteria = new Criteria();

        List<Criteria> filters = new ArrayList<>();

        Criteria lockedCriteria = new Criteria();
        Criteria disabledCriteria = new Criteria();
        Criteria namesCriteria = new Criteria();
        Criteria datesCriteria = new Criteria();

        if(!nameFilters.isEmpty()){
            List<Criteria> filtersName = new ArrayList<>(nameFilters.stream()
                    .map(code -> Criteria.where("code")
                            .is(Integer.valueOf(code))).toList());
            namesCriteria.orOperator(filtersName);
            filters.add(namesCriteria);
        }
        if(!lockedFilters.isEmpty()){
            List<Criteria> filtersLocked = new ArrayList<>(lockedFilters.stream()
                    .map(locked -> Criteria.where("locked")
                            .is(Boolean.valueOf(locked))).toList());
            lockedCriteria.orOperator(filtersLocked);
            filters.add(lockedCriteria);
        }
        if(!disabledFilters.isEmpty()){
            List<Criteria> filtersDisabled = new ArrayList<>(disabledFilters.stream()
                    .map(disabled -> Criteria.where("disabled")
                            .is(Boolean.valueOf(disabled))).toList());
            disabledCriteria.orOperator(filtersDisabled);
            filters.add(disabledCriteria);
        }
        if(!dateFilters.isEmpty()){
            List<Criteria> filtersDate = new ArrayList<>(dateFilters.stream()
                    .map(dateFilter -> {
                        Criteria criteriaResponse = new Criteria();
                        if (dateFilter.getOption().equals("lt")) {
                            criteriaResponse = Criteria.where("dateCreated").lt(dateFilter.getValue());
                        } else if (dateFilter.getOption().equals("gt")) {
                            criteriaResponse = Criteria.where("dateCreated").gt(dateFilter.getValue());
                        }
                        return criteriaResponse;
                    }).toList());
            datesCriteria.andOperator(filtersDate);
            filters.add(datesCriteria);
        }

        if(!filters.isEmpty()) criteria.andOperator(filters);

        if(!searchText.isEmpty()){
            criteria.andOperator(Criteria.where("name").regex(searchText,"i"));
        }
        Query query = new Query(criteria);
        var offset = ((long) page * SIZE_PAGE);
        query.skip(offset);
        query.limit(SIZE_PAGE);

        return this.mongoTemplate.find(query, TagRead.class).stream()
                .map(Tag::recrear).toList();
    }

    @Override
    public void updateEtiqueta(SolicitudUpdateTag tag, String id) {
        this.mongoTemplate.save(new TagUpdate(new ObjectId(id),tag.getCode(),tag.getName(),tag.getLocked(),
                tag.getDisabled(),tag.getDateCreated()));
    }
}
