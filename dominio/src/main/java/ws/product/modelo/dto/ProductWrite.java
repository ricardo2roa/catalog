package ws.product.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import ws.information.modelo.dto.InformationDTO;

import java.util.List;

@Data
@Document("products")
@AllArgsConstructor
public class ProductWrite {
    private int code;
    private int tag;
    private int category;
    private int brand;
    private String name;
    private InformationDTO information;
    private List<String> references;
}
