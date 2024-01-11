package ws.product.servicios;

import ws.product.modelo.entidad.Product;
import ws.product.modelo.entidad.SolicitudCrearProducto;
import ws.product.puerto.repositorio.RepositorioProduct;

public class ServicioCrearProducto {
    private final RepositorioProduct repositorioProduct;
    public ServicioCrearProducto(RepositorioProduct repositorioProduct) {
        this.repositorioProduct = repositorioProduct;
    }

    public String ejecutar(SolicitudCrearProducto solicitudCrearProducto){
        int code = this.repositorioProduct.calcularCode();
        Product product = Product.crear(solicitudCrearProducto,code);
        return this.repositorioProduct.guardar(product);
    }
}
