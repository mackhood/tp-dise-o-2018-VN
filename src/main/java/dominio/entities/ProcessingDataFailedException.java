package dominio.entities;

import java.io.IOException;

public class ProcessingDataFailedException extends IOException {

	public ProcessingDataFailedException(String msg) {
		super(msg);
	}

	public ProcessingDataFailedException(String msg, Throwable t) {
		super(msg, t);
	}
}