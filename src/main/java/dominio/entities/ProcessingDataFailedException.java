package dominio.entities;

import java.io.IOException;

public class ProcessingDataFailedException extends RuntimeException {

	public ProcessingDataFailedException(String msg) {
		super(msg);
	}
}