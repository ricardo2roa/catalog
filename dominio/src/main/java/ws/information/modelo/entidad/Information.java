package ws.information.modelo.entidad;

import lombok.Data;
import ws.information.modelo.dto.InformationDTO;

@Data
public class Information {
    private String benefits;
    private String feature;
    private String  description;

    private Information(String benefits, String feature, String description){
        this.benefits = benefits;
        this.feature = feature;
        this.description = description;
    }

    public static Information crear(String benefits, String feature, String description){
        return new Information(benefits, feature, description);
    }

    public static Information crear(InformationDTO informationDTO){
        return new Information(informationDTO.getBenefits(), informationDTO.getFeature(),
                informationDTO.getFeature());
    }
}
