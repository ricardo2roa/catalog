package ws.product.modelo.dto;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;
import ws.information.modelo.dto.InformationDTO;

import java.util.List;

@Data
@Document("products")
public class ProductDTO {
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
}
