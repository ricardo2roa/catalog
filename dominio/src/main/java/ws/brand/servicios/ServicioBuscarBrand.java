package ws.brand.servicios;

import ws.brand.modelo.dto.BrandDTO;
import ws.brand.puerto.repositorio.RepositorioBrand;

import java.util.Map;
import java.util.Set;

public class ServicioBuscarBrand {
    private final RepositorioBrand repositorioBrand;

    public ServicioBuscarBrand(RepositorioBrand repositorioBrand) {
        this.repositorioBrand = repositorioBrand;
    }

    public Map<Integer, BrandDTO> getBrandFor(Set<Integer> brandList){
        return this.repositorioBrand.obtenerListByCodes(brandList);
    }
}
