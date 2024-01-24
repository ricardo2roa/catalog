package ws.information.resolver;

import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;
import ws.information.modelo.entidad.Information;
import ws.product.modelo.entidad.Product;
import ws.tag.modelo.entidad.Tag;
@Component
public class InformationResolver implements GraphQLResolver<Product>{
    public Information information(Product product){
        return product.getInformation();
    }
}
