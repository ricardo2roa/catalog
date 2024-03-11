package ws.category.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@AllArgsConstructor
@Document("categories")
public class CategoryWrite {
    private int code;
    private String name;
    private Boolean locked;
    private Boolean disabled;
    private Date dateCreated;
}
