package ws.product.servicios;

import ws.UploadFiles.servicios.ImageSystemStorageService;
import ws.brand.modelo.entidad.Brand;
import ws.brand.puerto.repositorio.RepositorioBrand;
import ws.product.modelo.entidad.Product;
import ws.product.modelo.entidad.SolicitudCrearProducto;
import ws.product.modelo.entidad.SolicitudProducto;
import ws.product.puerto.repositorio.RepositorioProduct;
import ws.reference.modelo.dto.ReferenceDTO;
import ws.reference.modelo.entidad.Reference;
import ws.reference.puerto.repositorio.RepositorioReference;

import java.util.ArrayList;
import java.util.List;

public class ServicioCrearProducto {
    private final RepositorioProduct repositorioProduct;
    private final RepositorioBrand repositorioBrand;
    private final RepositorioReference repositorioReference;

    private final ImageSystemStorageService serviceImageStorage;
    public ServicioCrearProducto(RepositorioProduct repositorioProduct, RepositorioBrand repositorioBrand, RepositorioReference repositorioReference, ImageSystemStorageService serviceImageStorage) {
        this.repositorioProduct = repositorioProduct;
        this.repositorioBrand = repositorioBrand;
        this.repositorioReference = repositorioReference;
        this.serviceImageStorage = serviceImageStorage;
    }

    public String ejecutar(SolicitudCrearProducto solicitudCrearProducto){
        SolicitudProducto producto = solicitudCrearProducto.getProducto();

        Product.validarCampos(producto);
        int code = this.repositorioProduct.calcularCode();
        Brand brand = this.repositorioBrand.obtenerByCode(producto.getBrand());

        Product.validarReferencias(producto.getReferences());
        List<Reference> references = Product.agregarSKUaReferencias(producto.getReferences(),brand.getName(),
                producto.getName(),code);

        List<String> referenceIds = new ArrayList<>();
        references.spliterator().forEachRemaining(reference->{
            var imagesReference = solicitudCrearProducto.getImageReference();
            String id = this.repositorioReference.guardar(ReferenceDTO.convertir(reference));
            referenceIds.add(id);
            this.serviceImageStorage.store(imagesReference.stream()
                    .filter(file-> reference.getCodeImg().equals(file.getOriginalFilename().split("\\.")[0]))
                    .findFirst().get(),reference.getSku());
        });

        Product product = Product.crear(producto,code,referenceIds);
        return this.repositorioProduct.guardar(product);
    }
}
