package ws.category.adaptador.repositorio;


import org.springframework.data.mongodb.core.MongoTemplate;


import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ws.brand.modelo.dto.BrandDTO;
import ws.category.modelo.dto.CategoryDTO;
import ws.category.modelo.entidad.Category;
import ws.category.puerto.repositorio.RepositorioCategory;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class RepositorioCategoryMongo implements RepositorioCategory {
    private final MongoTemplate mongoTemplate;

    public RepositorioCategoryMongo(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Category obtenerByCode(int code) {
        Criteria criteria = Criteria.where("code").is(code)
                .and("locked").is(false)
                .and("disabled").is(false);
        Query query = new Query(criteria);
        CategoryDTO categoryDTO = this.mongoTemplate.findOne(query, CategoryDTO.class);
        return Category.recrear(categoryDTO.getCode(), categoryDTO.getName(), categoryDTO.getLocked(),
                categoryDTO.getDisabled(), categoryDTO.getDateCreated());
    }

    @Override
    public int guardar(Category category) {
        CategoryDTO categoryNew = this.mongoTemplate.save(new CategoryDTO(category.getCode(), category.getName(),
                category.getLocked(),category.getDisabled(),category.getDateCreated()));
        return categoryNew.getCode();
    }

    @Override
    public Map<Integer, CategoryDTO> obtenerListByCodes(Set<Integer> codes) {
        Criteria criteria = new Criteria();
        if(codes.size() > 1){
            var list = codes.stream().map(item->Criteria.where("code").is(item)).toList();
            criteria.orOperator(list);
        }else{
            var codeList = codes.stream().toList();
            criteria = Criteria.where("code").is(codeList.get(0));
        }
        Query query = new Query(criteria);
        var categoryDtos = this.mongoTemplate.find(query, CategoryDTO.class);
        return categoryDtos.stream().collect(Collectors.toMap(CategoryDTO::getCode, Function.identity()));
    }

    @Override
    public Map<Integer, Category> obtenerListValrep() {
        var categoryDtos = this.mongoTemplate.findAll(CategoryDTO.class);
        return categoryDtos.stream().collect(Collectors.toMap(CategoryDTO::getCode, dto -> Category.recrear(dto)));
    }

    @Override
    public int calcularCode() {
        return (int)(this.mongoTemplate.count(new Query(),"categories")+1);
    }
}
