package ws.brand.adaptador.repositorio;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ws.brand.modelo.dto.BrandDTO;
import ws.brand.modelo.entidad.Brand;
import ws.brand.puerto.repositorio.RepositorioBrand;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class RepositorioBrandMongo implements RepositorioBrand {
    private final MongoTemplate mongoTemplate;

    public RepositorioBrandMongo(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Brand obtenerByCode(int code) {
        Criteria criteria = Criteria.where("code").is(code)
                .and("locked").is(false)
                .and("disabled").is(false);
        Query query = new Query(criteria);
        BrandDTO brandDto = this.mongoTemplate.findOne(query, BrandDTO.class);
        return Brand.recrear(brandDto.getCode(), brandDto.getName(), brandDto.getLocked(),
                brandDto.getDisabled(),brandDto.getDateCreated());
    }

    public Map<Integer,BrandDTO> obtenerListByCodes(Set<Integer> code) {
        Criteria criteria = new Criteria();
        if(code.size() > 1){
            var list = code.stream().map(item->Criteria.where("code").is(item)).toList();
            criteria.orOperator(list);
        }else{
            var codeList = code.stream().toList();
            criteria = Criteria.where("code").is(codeList.get(0));
        }
        Query query = new Query(criteria);
        var brandDto = this.mongoTemplate.find(query, BrandDTO.class);
        Map<Integer,BrandDTO> response =  brandDto.stream().collect(Collectors.toMap(BrandDTO::getCode, Function.identity()));
        return response;
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
