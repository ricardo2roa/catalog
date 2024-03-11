package ws.category.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Getter
@AllArgsConstructor
@Document("categories")
public class CategoryUpdate {
    @MongoId(FieldType.OBJECT_ID)
    @Field("_id")
    private ObjectId id;
    private int code;
    private String name;
    private Boolean locked;
    private Boolean disabled;
    private Date dateCreated;
}
