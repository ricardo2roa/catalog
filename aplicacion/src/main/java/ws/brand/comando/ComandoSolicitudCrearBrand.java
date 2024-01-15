package ws.brand.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoSolicitudCrearBrand {
    private String name;
    private Boolean locked;
    private Boolean disabled;
}
