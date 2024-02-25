package ws.domain.exception.validador;

import ws.domain.exception.modelo.RequestException;
import ws.domain.exception.modelo.FindDbException;

import java.util.List;

public class ValidarDatos {
    public static void siEsVacioONull(String fieldName, String value){
        Boolean flag = false;
        if(value == null){
            flag = true;
        }else if(value.isEmpty()){
            flag = true;
        }
        if(flag)throw new RequestException("400", ("El campo " + fieldName + " esta vacio"));
    }

    public static void siListaEsNull(String fieldName, List<?> list){
        Boolean flag = false;
        if(list == null){
            flag = true;
        }else if(list.isEmpty()){
            flag = true;
        }
        if(flag)throw new RequestException("400", ("El campo " + fieldName + " esta vacio o nulo"));
    }

    public static void siEsNull(String fieldName, Object value){
        if(value == null){
            throw new RequestException("400", ("El campo " + fieldName + " esta vacio"));
        }
    }

    public static void siEsNullFindDB(String fieldName, Object value){
        if(value == null){
            throw new FindDbException("500", ("El campo " + fieldName + " esta vacio"));
        }
    }

    public static void siEsMayoraCero(String fieldName, int value){
        if(value <= 0){
            throw new RequestException("400", ("El campo " + fieldName + " tiene un valor menor o igual a cero"));
        }
    }

    public static void siEsMayoraCero(String fieldName, long value){
        if(value <= 0){
            throw new RequestException("400", ("El campo " + fieldName + " tiene un valor menor o igual a cero"));
        }
    }
}
