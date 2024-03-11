package ws.category.adaptador.repositorio;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;


import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ws.brand.modelo.entidad.DateFilter;
import ws.category.modelo.dto.CategoryRead;
import ws.category.modelo.dto.CategoryUpdate;
import ws.category.modelo.dto.CategoryWrite;
import ws.category.modelo.entidad.Category;
import ws.category.modelo.entidad.SolicitudUpdateCategory;
import ws.category.puerto.repositorio.RepositorioCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class RepositorioCategoryMongo implements RepositorioCategory {
    private final int SIZE_PAGE = 10;
    private final MongoTemplate mongoTemplate;

    public RepositorioCategoryMongo(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Category obtenerByCode(int code) {
        Criteria criteria = Criteria.where("code").is(code);
        Query query = new Query(criteria);
        CategoryRead categoryRead = this.mongoTemplate.findOne(query, CategoryRead.class);
        return Category.recrear(categoryRead);
    }

    @Override
    public int guardar(Category category) {
        CategoryWrite categoryNew = this.mongoTemplate.save(new CategoryWrite(category.getCode(), category.getName(),
                category.getLocked(),category.getDisabled(),category.getDateCreated()));
        return categoryNew.getCode();
    }

    /*@Override
    public Map<Integer, Category> obtenerListByCodes(Set<Integer> codes) {
        Criteria criteria = new Criteria();
        if(codes.size() > 1){
            var list = codes.stream().map(item->Criteria.where("code").is(item)).toList();
            criteria.orOperator(list);
        }else{
            var codeList = codes.stream().toList();
            criteria = Criteria.where("code").is(codeList.get(0));
        }
        Query query = new Query(criteria);
        var categoryDtos = this.mongoTemplate.find(query, CategoryRead.class);
        return categoryDtos.stream().collect(Collectors.toMap(Category::getCode, Function.identity()));
    }*/

    @Override
    public Map<Integer, Category> obtenerListValrep() {
        var categoryDtos = this.mongoTemplate.findAll(CategoryRead.class);
        return categoryDtos.stream().collect(Collectors.toMap(CategoryRead::getCode, Category::recrear));
    }

    @Override
    public List<Category> obtenerTodasLasCategorias(int page, String searchText, List<String> nameFilters, List<String> lockedFilters,
                                                         List<String> disabledFilters, List<DateFilter> dateFilters) {

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

        return this.mongoTemplate.find(query, CategoryRead.class).stream()
                .map(Category::recrear).toList();
    }

    @Override
    public int calcularCode() {
        return (int)(this.mongoTemplate.count(new Query(),"categories")+1);
    }

    @Override
    public void updateCategory(SolicitudUpdateCategory category, String id) {
        this.mongoTemplate.save(new CategoryUpdate(new ObjectId(id),category.getCode(),category.getName(),
                category.getLocked(), category.getDisabled(),category.getDateCreated()));
    }
}
