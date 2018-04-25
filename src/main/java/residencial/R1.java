package residencial;
import clases.*;
import categorias.*;
public class R1 extends Residencial
{
	//TipoResidencia tipo = TipoResidencia.R1;
	public R1()
	{
		cargoFijo = 18.76;
		cargoVariable = 0.644;
		tipo = TipoResidencia.R1;
	}
	@Override
	public Boolean cumpleCondicionConsumoMensual(Cliente unCliente)
	{
		return unCliente.consumoEnergetico() <= 150;
	}
}
