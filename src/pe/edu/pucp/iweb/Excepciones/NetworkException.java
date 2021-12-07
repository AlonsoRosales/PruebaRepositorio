package pe.edu.pucp.iweb.Excepciones;

public class NetworkException extends  Exception{
    @Override
    public String getMessage() {
        return "La red no esta listada como segura, intente con otra";
    }
}
