package ws.reference.servicios;

import ws.UploadFiles.modelo.entidad.SolicitudCrearImagen;
import ws.UploadFiles.servicios.ImageSystemStorageService;
import ws.reference.puerto.repositorio.RepositorioImageReference;

public class ServicioUploadImageOfReference {
    private final RepositorioImageReference repositorioImageReference;
    private final ImageSystemStorageService servicioUploadImage;

    public ServicioUploadImageOfReference(RepositorioImageReference repositorioImageReference, ImageSystemStorageService servicioUploadImage) {
        this.repositorioImageReference = repositorioImageReference;
        this.servicioUploadImage = servicioUploadImage;
    }

    public String ejecutar(SolicitudCrearImagen imagen){
        return this.servicioUploadImage.store(imagen.getFile(),imagen.getName());
    }
}
