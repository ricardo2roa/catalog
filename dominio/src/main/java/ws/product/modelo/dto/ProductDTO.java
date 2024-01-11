package ws.product.modelo.dto;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;
import ws.brand.modelo.dto.BrandDTO;
import ws.information.modelo.dto.InformationDTO;
import ws.reference.modelo.dto.ReferenceDTO;
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
    private BrandDTO brand;
    private String name;
    private InformationDTO information;
    private List<ReferenceDTO> references;

    public ProductDTO(int code, int tag, int category, BrandDTO brand, String name,
                      InformationDTO information, List<ReferenceDTO> references){
        this.code = code;
        this.tag = tag;
        this.category = category;
        this.brand= brand;
        this.name =name;
        this.information = information;
        this.references = references;
    }
}
