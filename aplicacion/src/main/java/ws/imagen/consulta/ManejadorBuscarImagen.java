package ws.imagen.consulta;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import ws.UploadFiles.servicios.ImageSystemStorageService;

@Component
public class ManejadorBuscarImagen {
    private final ImageSystemStorageService serviceSystemStorage;

    public ManejadorBuscarImagen(ImageSystemStorageService serviceSystemStorage) {
        this.serviceSystemStorage = serviceSystemStorage;
    }

    public Resource ejecutar(String filename){
        return this.serviceSystemStorage.loadAsResource(filename);
    }
}
