package ws.imagen.consulta;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import ws.UploadFiles.servicios.ImageSystemStorageService;
import ws.reference.puerto.repositorio.RepositorioImageReference;

@Component
public class ManejadorBuscarImagen {
    private final ImageSystemStorageService serviceSystemStorage;
    private final RepositorioImageReference repositorioImageReference;
    public ManejadorBuscarImagen(ImageSystemStorageService serviceSystemStorage, RepositorioImageReference repositorioImageReference) {
        this.serviceSystemStorage = serviceSystemStorage;
        this.repositorioImageReference = repositorioImageReference;
    }

    public Resource ejecutar(String id){
        String filename = this.repositorioImageReference.obtenerNameByIdReference(id);
        return this.serviceSystemStorage.loadAsResource(filename);
    }
}
