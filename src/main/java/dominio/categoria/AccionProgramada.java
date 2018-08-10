package dominio.categoria;

import dominio.empresa.*;

import java.util.TimerTask;

public class AccionProgramada extends TimerTask {
	
	Empresa empresa; 
	String descripcion;
	
	public AccionProgramada(String unaDescripcion) { this.descripcion = unaDescripcion; } 
	
	public AsignadorDeCategoria asignador() {
		
		return AsignadorDeCategoria.getInstance();
	}

	@Override
	public void run() {
		
		empresa.getClientes().forEach(cliente -> asignador().actualizarPara(cliente));
	}
	
	
	
	
}
