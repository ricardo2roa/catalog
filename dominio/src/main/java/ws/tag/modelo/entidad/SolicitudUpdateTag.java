package ws.tag.modelo.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class SolicitudUpdateTag {
    private final int code;
    private final String name;
    private final Boolean locked;
    private final Boolean disabled;
    private final Date dateCreated;
}
