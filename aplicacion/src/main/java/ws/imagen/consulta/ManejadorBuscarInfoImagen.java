package ws.imagen.consulta;

import org.springframework.stereotype.Component;
import ws.reference.modelo.entidad.ImageReferenceInfo;
import ws.reference.puerto.repositorio.RepositorioImageReference;

@Component
public class ManejadorBuscarInfoImagen {
    private final RepositorioImageReference imageReference;

    public ManejadorBuscarInfoImagen(RepositorioImageReference imageReference) {
        this.imageReference = imageReference;
    }

    public ImageReferenceInfo ejecutar(String id){
        return this.imageReference.obtenerImagenInfoByIdReference(id);
    }
}
