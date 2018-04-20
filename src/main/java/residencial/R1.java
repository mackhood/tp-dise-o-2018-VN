package residencial;
import clases.*;

public class R1 extends Residencial
{
	//TipoResidencia tipo = TipoResidencia.R1;
	public R1()
	{
		CARGOFIJO = 18.76;
		CARGOVARIABLE = 0.644;
		tipo = TipoResidencia.R1;
	}
	@Override
	public Boolean cumpleCondicionConsumoMensual(Cliente unCliente)
	{
		return unCliente.consumoEnergetico() <= 150;
	}
}
