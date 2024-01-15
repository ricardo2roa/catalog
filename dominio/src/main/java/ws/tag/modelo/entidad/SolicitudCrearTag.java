package ws.tag.modelo.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SolicitudCrearTag {
    private final String name;
    private final Boolean locked;
    private final Boolean disabled;
}
