package clases;

public class Dispositivo 
{
	String nombreGen;
	double kWperH;
	Boolean estaEncendido;
	
	public Dispositivo(String unNombreGen, double _kWperH, Boolean _estaEncendido)
	{
		nombreGen = unNombreGen;
		kWperH = _kWperH;
		estaEncendido = _estaEncendido;
	}
	public String nombreGen()
	{
		return nombreGen;
	}
	public double kWperH()
	{
		return kWperH;
	}
	public Boolean estaEncendido()
	{
		return estaEncendido;
	}
}

