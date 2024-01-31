package ws.reference.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Getter
@AllArgsConstructor
@Document("imagesReference")
public class ImageReferenceInfoDTO {
    @MongoId
    @Field("_id")
    private String  id;
    private String  idReference;
    private String  name;
    private Date    dateModify;
}
