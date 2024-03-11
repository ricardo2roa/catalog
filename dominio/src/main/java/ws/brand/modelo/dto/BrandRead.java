package ws.brand.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Getter
@AllArgsConstructor
@Document("brands")
public class BrandRead {
    @MongoId
    @Field("_id")
    private String id;
    private int code;
    private String name;
    private Boolean locked;
    private Boolean disabled;
    private Date dateCreated;
}
