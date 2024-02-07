package ws.brand.consulta;

import org.springframework.stereotype.Component;
import ws.brand.modelo.entidad.Brand;
import ws.brand.puerto.repositorio.RepositorioBrand;

import java.util.Map;

@Component
public class ManejadorGetValrepBrand {
    private final RepositorioBrand repositorioBrand;

    public ManejadorGetValrepBrand(RepositorioBrand repositorioBrand) {
        this.repositorioBrand = repositorioBrand;
    }

    public Map<Integer, Brand> ejecutar(){
        return this.repositorioBrand.obtenerListValrep();
    }
}
