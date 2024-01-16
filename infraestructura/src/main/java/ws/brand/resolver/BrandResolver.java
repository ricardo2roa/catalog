package ws.brand.resolver;

import graphql.kickstart.tools.GraphQLResolver;
import ws.brand.modelo.dto.BrandDTO;
import ws.product.modelo.dto.ProductDTO;

public class BrandResolver implements GraphQLResolver<ProductDTO>{
    public BrandDTO brand(ProductDTO product){
        BrandDTO actualBrand = product.getBrand();
        return new BrandDTO(actualBrand.getCode(),
                actualBrand.getName(), actualBrand.getLocked(),
                actualBrand.getDisabled(), actualBrand.getDateCreated());
    }
}
