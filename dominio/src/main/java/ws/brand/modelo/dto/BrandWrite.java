package ws.brand.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
@Getter
@AllArgsConstructor
@Document("brands")
public class BrandWrite {
    private int code;
    private String name;
    private Boolean locked;
    private Boolean disabled;
    private Date dateCreated;
}
