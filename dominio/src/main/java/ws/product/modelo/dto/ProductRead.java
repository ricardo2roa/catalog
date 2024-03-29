package ws.product.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;
import ws.information.modelo.dto.InformationDTO;
import java.util.List;

@AllArgsConstructor
@Document("products")
public class ProductRead extends ProductDTO{
    @MongoId
    @Field("_id")
    private String id;
    private int code;
    private int tag;
    private int category;
    private int brand;
    private String name;
    private InformationDTO information;
    private List<String> references;

    public String getId() {
        return id;
    }

    public int getCode() {
        return code;
    }

    public int getTag() {
        return tag;
    }

    public int getCategory() {
        return category;
    }

    public int getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public InformationDTO getInformation() {
        return information;
    }

    public List<String> getReferences() {
        return references;
    }
}
