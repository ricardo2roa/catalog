package ws.brand.adaptador.repositorio;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ws.brand.modelo.dto.BrandRead;
import ws.brand.modelo.dto.BrandUpdate;
import ws.brand.modelo.dto.BrandWrite;
import ws.brand.modelo.entidad.Brand;
import ws.brand.modelo.entidad.DateFilter;
import ws.brand.modelo.entidad.SolicitudUpdateBrand;
import ws.brand.puerto.repositorio.RepositorioBrand;
import ws.infraestructura.exception.modelo.DuplicateRecordException;

import java.util.*;
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
        BrandRead brandDto = this.mongoTemplate.findOne(query, BrandRead.class);
        return Brand.recrear(brandDto);
    }

    public Map<Integer, BrandRead> obtenerListByCodes(Set<Integer> code) {
        Criteria criteria = new Criteria();
        if(code.size() > 1){
            var list = code.stream().map(item->Criteria.where("code").is(item)).toList();
            criteria.orOperator(list);
        }else{
            var codeList = code.stream().toList();
            criteria = Criteria.where("code").is(codeList.get(0));
        }
        Query query = new Query(criteria);
        var brandDto = this.mongoTemplate.find(query, BrandRead.class);
        Map<Integer, BrandRead> response =  brandDto.stream().collect(Collectors.toMap(BrandRead::getCode, Function.identity()));
        return response;
    }

    @Override
    public Map<Integer, Brand> obtenerListValrep() {
        var brandDto = this.mongoTemplate.findAll(BrandRead.class);
        return brandDto.stream().collect(Collectors.toMap(BrandRead::getCode, dto -> Brand.recrear(dto)));
    }

    @Override
    public List<Brand> obtenerTodasLasMarcas(int page, String searchText,List<String> nameFilters,
                                             List<String> lockedFilters, List<String> disabledFilters,
                                             List<DateFilter> dateFilters) {
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

        return this.mongoTemplate.find(query, BrandRead.class).stream()
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
        if(this.mongoTemplate.exists(query, BrandRead.class)) throw new DuplicateRecordException("Ya esta registrado esta marca "+brand.getName());

        BrandWrite brandSave = this.mongoTemplate.save(new BrandWrite(
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
    public void updateBrand(SolicitudUpdateBrand brand, String id) {
        /*Criteria criteria = Criteria.where("_id").is(id);
        Query query = new Query(criteria);
        Update update = new Update();
        update.set("name",brand.getName());
        update.set("disabled",brand.getDisabled());
        update.set("locked",brand.getLocked());*/
        this.mongoTemplate.save(new BrandUpdate(new ObjectId(id),brand.getCode(),brand.getName(),brand.getLocked(),
                brand.getDisabled(),brand.getDateCreated()));

    }
}
