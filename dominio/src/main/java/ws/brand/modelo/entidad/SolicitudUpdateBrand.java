package ws.brand.modelo.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
public class SolicitudUpdateBrand {
    private final int code;
    private final String name;
    private final Boolean locked;
    private final Boolean disabled;
    private final Date dateCreated;
}
