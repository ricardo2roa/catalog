package ws.reference.modelo.entidad;

import ws.reference.modelo.dto.ImageReferenceInfoDTO;

import java.util.Date;

public class ImageReferenceInfo {
    private String  id;
    private String  name;
    private String  idReference;
    private Date dateModify;

    private ImageReferenceInfo(String id, String name, String idReference, Date dateModify) {
        this.id = id;
        this.name = name;
        this.idReference = idReference;
        this.dateModify = dateModify;
    }

    private ImageReferenceInfo(String name, String idReference) {
        this.name = name;
        this.idReference = idReference;
        this.dateModify = new Date();
    }

    public static ImageReferenceInfo crear(String name, String idReference){
        return new ImageReferenceInfo(name,idReference);
    }

    public static ImageReferenceInfo recrear(ImageReferenceInfoDTO image) {
        return new ImageReferenceInfo(image.getId(), image.getName(), image.getIdReference(), image.getDateModify());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIdReference() {
        return idReference;
    }

    public Date getDateModify() {
        return dateModify;
    }
}
