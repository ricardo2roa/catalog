package ws.category.modelo.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class SolicitudCrearCategory {
    private final String name;
    private final Boolean locked;
    private final Boolean disabled;
}
