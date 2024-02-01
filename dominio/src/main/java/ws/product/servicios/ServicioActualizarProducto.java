package ws.product.servicios;

import ws.UploadFiles.servicios.ImageSystemStorageService;
import ws.brand.puerto.repositorio.RepositorioBrand;
import ws.category.puerto.repositorio.RepositorioCategory;
import ws.product.modelo.entidad.Product;
import ws.product.puerto.repositorio.RepositorioProduct;
import ws.reference.puerto.repositorio.RepositorioImageReference;
import ws.reference.puerto.repositorio.RepositorioReference;
import ws.tag.puerto.repositorio.RepositorioTag;

public class ServicioActualizarProducto {
    private final RepositorioProduct repositorioProduct;
    private final RepositorioBrand repositorioBrand;
    private final RepositorioTag repositorioTag;
    private final RepositorioCategory repositorioCategory;
    private final RepositorioReference repositorioReference;

    private final RepositorioImageReference repositorioImageReference;
    private final ImageSystemStorageService serviceImageStorage;

    public ServicioActualizarProducto(RepositorioProduct repositorioProduct, RepositorioBrand repositorioBrand, RepositorioTag repositorioTag, RepositorioCategory repositorioCategory, RepositorioReference repositorioReference, RepositorioImageReference repositorioImageReference, ImageSystemStorageService serviceImageStorage) {
        this.repositorioProduct = repositorioProduct;
        this.repositorioBrand = repositorioBrand;
        this.repositorioTag = repositorioTag;
        this.repositorioCategory = repositorioCategory;
        this.repositorioReference = repositorioReference;
        this.repositorioImageReference = repositorioImageReference;
        this.serviceImageStorage = serviceImageStorage;
    }

    //public Product ejecutar(SolictudActualizarProducto solictudActualizarProducto){}
}
