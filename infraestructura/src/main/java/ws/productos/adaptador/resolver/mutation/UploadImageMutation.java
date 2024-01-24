package ws.productos.adaptador.resolver.mutation;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import jakarta.servlet.http.Part;
import lombok.extern.java.Log;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Log
@Component
public class UploadImageMutation implements GraphQLMutationResolver {

    public Boolean uploadImage(DataFetchingEnvironment enviroment){
        List<Part> files = enviroment.getGraphQlContext().get("parts");
        files.remove(0);
        for (Part file : files) {
            String fullNameFile = file.getSubmittedFileName();
            var ext = fullNameFile.substring(fullNameFile.lastIndexOf("."),fullNameFile.length());
            try {
                file.write("D:\\web-services\\img\\"+file.getName()+ext);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /*int i = 1;
        for (Part part : attachmentParts) {
            String uploadName = "copy" + i;
            try {
                part.write("your path:" + uploadName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            i++;
        }*/
        return true;
    }
}
