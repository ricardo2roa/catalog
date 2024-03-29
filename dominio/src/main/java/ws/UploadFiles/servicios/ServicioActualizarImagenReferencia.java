package ws.UploadFiles.servicios;

import org.springframework.web.multipart.MultipartFile;
import ws.UploadFiles.modelo.entidad.SolicitudUpdateImagenReferencia;
import ws.reference.modelo.dto.ReferenceDTO;
import ws.reference.modelo.entidad.Reference;
import ws.reference.puerto.repositorio.RepositorioImageReference;
import ws.reference.puerto.repositorio.RepositorioReference;

import static ws.reference.modelo.entidad.ImageReferenceInfo.crear;

public class ServicioActualizarImagenReferencia {
    private final RepositorioReference repositorioReference;
    private final ImageSystemStorageService imageSystemStorageService;
    private final RepositorioImageReference repositorioImageReference;
    public ServicioActualizarImagenReferencia(RepositorioReference repositorioReference,
                                              ImageSystemStorageService imageSystemStorageService,
                                              RepositorioImageReference repositorioImageReference) {
        this.repositorioReference = repositorioReference;
        this.imageSystemStorageService = imageSystemStorageService;
        this.repositorioImageReference = repositorioImageReference;
    }

    public boolean ejecutar(SolicitudUpdateImagenReferencia solicitud){
        Reference reference = Reference.recrear(solicitud.getReferencia());
        String sku = this.repositorioReference.obtenerInfoParaSKUByIdReferencia(reference);
        String fileName = this.imageSystemStorageService.store(solicitud.getFile(),sku);
        if(!sku.equals(reference.getSku())) this.imageSystemStorageService.delete(solicitud.getFile().getOriginalFilename(),reference.getSku());
        this.repositorioImageReference.registrarImagen(crear(fileName,solicitud.getReferencia().getId()));
        ReferenceDTO referencedto = new ReferenceDTO(reference.getId(), reference.getPeso(), reference.getPrecio(), sku,
                reference.getStock());
        this.repositorioReference.guardar(referencedto);
        return true;
    }
}
