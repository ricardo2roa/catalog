package ws.reference.adaptador.resolver;

import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;
import ws.product.modelo.entidad.Product;
import ws.reference.modelo.entidad.Reference;
import ws.reference.puerto.repositorio.RepositorioReference;

import java.util.List;

@Component
public class ReferenceResolver implements GraphQLResolver<Product>{
    private final RepositorioReference repositorioReference;

    public ReferenceResolver(RepositorioReference repositorioReference) {
        this.repositorioReference = repositorioReference;
    }

    public List<Reference> references(Product product){
        return product.getReferences().stream()
                .map(id->this.repositorioReference.buscarPorId(id)).toList();
    }
}
