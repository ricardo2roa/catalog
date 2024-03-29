package ws.product.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ws.information.modelo.dto.InformationDTO;

@Getter
@AllArgsConstructor
public class ComandoUpdateProduct{
    private int tag;
    private int category;
    private int brand;
    private String name;
    private InformationDTO information;
}
