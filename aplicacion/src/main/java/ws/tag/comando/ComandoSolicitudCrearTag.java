package ws.tag.comando;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ComandoSolicitudCrearTag {
    private String name;
    private Boolean locked;
    private Boolean disabled;
}
