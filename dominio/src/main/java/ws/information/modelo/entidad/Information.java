package ws.information.modelo.entidad;

import lombok.Data;

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
}
