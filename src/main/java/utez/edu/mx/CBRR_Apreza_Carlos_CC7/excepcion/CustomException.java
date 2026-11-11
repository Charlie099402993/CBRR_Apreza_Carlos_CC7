package utez.edu.mx.CBRR_Apreza_Carlos_CC7.excepcion;

public class CustomException extends RuntimeException {
    public CustomException(String mensaje) {
        super(mensaje);
    }
}