package dominio.entities;

public class ProcessingDataFailedException extends RuntimeException {

    public ProcessingDataFailedException(String msg) {
        super(msg);
    }
}