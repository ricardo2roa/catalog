package ws.productos.adaptador.resolver.mutation;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import jakarta.servlet.http.Part;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ws.productos.adaptador.resolver.errorHTTP.exception.ErrorHTTPException;

import java.io.IOException;
import java.util.*;

@Log
@Component
public class UploadImageMutation /*implements GraphQLMutationResolver */{
    public static final Set<String> LIST_EXT = Set.of(".pdf", ".jpg", ".png", ".jpeg");

    public String uploadImage(DataFetchingEnvironment enviroment){
        List<Part> files = enviroment.getGraphQlContext().get("parts");
        files.remove(0);

        //for (Part file : files) {
        //Solo se puede enviar un archivo por solictud
        if(files.size() > 1) throw new ErrorHTTPException("400",
                "No se puede enviar mas de un archivo en la solicitud");

        Part file = files.get(0);
        String fullNameFile = file.getSubmittedFileName();
        var ext = fullNameFile.substring(fullNameFile.lastIndexOf("."));

        //Comprobación de extension
        if(!LIST_EXT.contains(ext)) throw new ErrorHTTPException("400",
                "La extension del archivo no esta permitida, solo se permite [.pdf, .jpg, .png, .jpeg]");

        try {
            file.write("D:\\web-services\\img\\"+file.getName()+ext);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //}
        return (file.getName()+ext);
    }
}
