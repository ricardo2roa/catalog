package ws.product.servicios;

import ws.brand.modelo.entidad.Brand;
import ws.brand.puerto.repositorio.RepositorioBrand;
import ws.product.modelo.entidad.Product;
import ws.product.modelo.entidad.SolicitudCrearProducto;
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
    public ServicioCrearProducto(RepositorioProduct repositorioProduct, RepositorioBrand repositorioBrand, RepositorioReference repositorioReference) {
        this.repositorioProduct = repositorioProduct;
        this.repositorioBrand = repositorioBrand;
        this.repositorioReference = repositorioReference;
    }

    public String ejecutar(SolicitudCrearProducto solicitudCrearProducto){

        Product.validarCampos(solicitudCrearProducto);
        int code = this.repositorioProduct.calcularCode();
        Brand brand = this.repositorioBrand.obtenerByCode(solicitudCrearProducto.getBrand());

        Product.validarReferenciasDTO(solicitudCrearProducto.getReferences());
        List<Reference> references = Product.agregarSKUaReferencias(solicitudCrearProducto.getReferences(),brand.getName(),
                solicitudCrearProducto.getName(),code);

        List<String> referenceIds = new ArrayList<>();
        references.spliterator().forEachRemaining(reference->{
            String id = this.repositorioReference.guardar(ReferenceDTO.convertir(reference));
            referenceIds.add(id);
        });

        Product product = Product.crear(solicitudCrearProducto,code,referenceIds);
        return this.repositorioProduct.guardar(product);
    }
}
