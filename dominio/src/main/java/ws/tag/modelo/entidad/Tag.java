package ws.tag.modelo.entidad;

import ws.brand.modelo.entidad.Brand;
import ws.tag.modelo.dto.TagDTO;
import ws.tag.modelo.dto.TagRead;

import java.util.Date;

public class Tag {
    private String id;
    private int code;
    private String name;
    private Boolean locked;
    private Boolean disabled;
    private Date dateCreated;

    private Tag(int code, String name, Boolean locked, Boolean disabled){
        this.code = code;
        this.name = name;
        this.locked = locked;
        this.disabled = disabled;
        this.dateCreated = new Date();
    }

    private Tag(int code, String name, Boolean locked, Boolean disabled,Date dateCreated){
        this.code = code;
        this.name = name;
        this.locked = locked;
        this.disabled = disabled;
        this.dateCreated = dateCreated;
    }

    private Tag(String id, int code, String name, Boolean locked, Boolean disabled,Date dateCreated){
        this.id = id;
        this.code = code;
        this.name = name;
        this.locked = locked;
        this.disabled = disabled;
        this.dateCreated = dateCreated;
    }
    public static Tag crear(int code, String name, Boolean locked, Boolean disabled){
        return new Tag(code,name,locked,disabled);
    }

    public static Tag recrear(int code, String name, Boolean locked, Boolean disabled, Date dateCreated){
        return new Tag(code,name,locked,disabled,dateCreated);
    }

    public static Tag recrear(TagRead dto){
        return new Tag(dto.getId(),dto.getCode(),dto.getName(),dto.getLocked(),dto.getDisabled(),dto.getDateCreated());
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

    public String getId() {
        return id;
    }
}
