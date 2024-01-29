package ws.productos.adaptador.resolver.errorHTTP;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.kickstart.execution.error.GraphQLErrorHandler;
import org.springframework.stereotype.Component;
import ws.productos.adaptador.resolver.errorHTTP.exception.ErrorHTTPException;

import java.util.List;
@Component
public class CustomGraphqlErrorHandler implements GraphQLErrorHandler {

    public List<GraphQLError> processErrors(List<GraphQLError> errors) {
        return errors;
    }

}
