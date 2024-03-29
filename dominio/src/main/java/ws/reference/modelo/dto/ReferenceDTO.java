package ws.reference.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;
import ws.reference.modelo.entidad.Reference;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document("references")
public class ReferenceDTO {
    @MongoId
    @Field("_id")
    private String id;
    private long peso;
    private long precio;
    private String sku;
    private int stock;

    public static ReferenceDTO convertir(Reference reference){
        return new ReferenceDTO(reference.getId(), reference.getPeso(), reference.getPrecio(),
                reference.getSku(), reference.getStock());
    }
}
