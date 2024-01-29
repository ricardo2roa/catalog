package ws.exception.validador;

import ws.exception.modelo.RequestException;

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

    public static void siEsNull(String fieldName, Object value){
        Boolean flag = false;
        if(value == null){
            throw new RequestException("400", ("El campo " + fieldName + " esta vacio"));
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
