package ws.reference.resolver;

import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;
import ws.product.modelo.entidad.Product;
import ws.reference.modelo.entidad.Reference;

import java.util.List;

@Component
public class ReferenceResolver implements GraphQLResolver<Product>{
    public List<Reference> references(Product product){
        return product.getReferences();
    }
}
