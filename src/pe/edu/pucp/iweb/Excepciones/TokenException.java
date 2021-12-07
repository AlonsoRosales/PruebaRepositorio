package pe.edu.pucp.iweb.Excepciones;

public class TokenException extends  Exception{

    @Override
    public String getMessage() {
        return "El token se encuentra baneado por SCAM";
    }
}
