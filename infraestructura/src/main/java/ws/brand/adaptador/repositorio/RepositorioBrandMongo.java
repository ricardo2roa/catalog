package ws.brand.adaptador.repositorio;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ws.brand.modelo.dto.BrandDTO;
import ws.brand.modelo.entidad.Brand;
import ws.brand.modelo.entidad.SolicitudUpdateBrand;
import ws.brand.puerto.repositorio.RepositorioBrand;
import ws.information.modelo.dto.InformationDTO;
import ws.infraestructura.exception.modelo.DuplicateRecordException;
import ws.sort.modelo.dto.SortFieldDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class RepositorioBrandMongo implements RepositorioBrand {
    private final int SIZE_PAGE = 10;
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
    public Map<Integer, Brand> obtenerListValrep() {
        var brandDto = this.mongoTemplate.findAll(BrandDTO.class);
        return brandDto.stream().collect(Collectors.toMap(BrandDTO::getCode, dto -> Brand.recrear(dto)));
    }

    @Override
    public List<Brand> obtenerTodasLasMarcas(int page,Boolean disabled, Boolean locked, List<Integer> codes, String searchText) {
        Criteria criteria = new Criteria();
        if(locked) criteria.andOperator(Criteria.where("locked").is(true));
        if(disabled) criteria.andOperator(Criteria.where("disabled").is(true));
        if(!codes.isEmpty()){
            criteria.orOperator(
                    codes.stream().map(code->Criteria.where("code").is(code)).toList()
            );
        }
        if(!searchText.isEmpty()){
            criteria.andOperator(Criteria.where("name").regex(searchText,"i"));
        }
        Query query = new Query(criteria);
        var offset = ((long) page * SIZE_PAGE);
        query.skip(offset);
        query.limit(SIZE_PAGE);

        return this.mongoTemplate.find(query,BrandDTO.class).stream()
                .map(Brand::recrear).toList();
    }

    @Override
    public Brand obtenerName(String name) {
        return null;
    }

    @Override
    public int guardar(Brand brand) {
        Criteria criteria = Criteria.where("code").is(brand.getCode());
        Query query = new Query(criteria);
        if(this.mongoTemplate.exists(query, BrandDTO.class)) throw new DuplicateRecordException("Ya esta registrado esta marca "+brand.getName());

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

    @Override
    public void updateBrands(List<SolicitudUpdateBrand> brands) {
        brands.spliterator().forEachRemaining(brand -> {
            Criteria criteria = Criteria.where("code").is(brand.getCode());
            Query query = new Query(criteria);
            Update update = new Update();
            update.set("name",brand.getName());
            update.set("disabled",brand.getDisabled());
            update.set("locked",brand.getLocked());
            this.mongoTemplate.findAndModify(query,update,BrandDTO.class);
        });
    }
}
