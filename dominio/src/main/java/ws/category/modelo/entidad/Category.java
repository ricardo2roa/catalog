package ws.category.modelo.entidad;

import ws.category.modelo.dto.CategoryDTO;

import java.util.Date;

public class Category {
    private int code;
    private String name;
    private Boolean locked;
    private Boolean disabled;
    private Date dateCreated;

    private Category(int code, String name,Boolean locked, Boolean disabled){
        this.code = code;
        this.name = name;
        this.locked = locked;
        this.disabled = disabled;
        this.dateCreated = new Date();
    }

    private Category(int code, String name,Boolean locked, Boolean disabled, Date dateCreated){
        this.code = code;
        this.name = name;
        this.locked = locked;
        this.disabled = disabled;
        this.dateCreated = dateCreated;
    }
    public static Category crear(SolicitudCrearCategory solicitudCrearCategory, int code){
        //Validar
        return new Category(code, solicitudCrearCategory.getName(), solicitudCrearCategory.getLocked(),
        solicitudCrearCategory.getDisabled());
    }

    public static Category recrear(int code, String name,Boolean locked, Boolean disabled, Date dateCreated){
        //Validar
        return new Category(code, name, locked,disabled,dateCreated);
    }

    public static Category recrear(CategoryDTO categoryDTO){
        //Validar
        return new Category(categoryDTO.getCode(), categoryDTO.getName(), categoryDTO.getLocked(),
                categoryDTO.getDisabled(),categoryDTO.getDateCreated());
    }
    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Boolean getLocked() {
        return locked;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public Date getDateCreated() {
        return dateCreated;
    }
}
