package dominio.dispositivo;

public class DispositivoEstandar extends Dispositivo {

    public DispositivoEstandar(String nombre, double consumoEstimadoPorHora) {

        this.nombre = nombre;
        this.consumoEstimadoPorHora = consumoEstimadoPorHora;
    }

    public DispositivoEstandar(String nombre, double restriccionMinima, double restriccionMaxima)
    {
        this.nombre = nombre;
        this.restriccionMinima = restriccionMinima;
        this.restriccionMaxima = restriccionMaxima;
    }
    public DispositivoEstandar(String nombre, String unEquipoConcreto, boolean esBajoConsumo, double consumoEstimadoPorHora, double restriccionMinima, double restriccionMaxima)
    {
        this.nombre = nombre;
        this.equipoConcreto = unEquipoConcreto;
        this.esBajoConsumo = esBajoConsumo;
        this.consumoEstimadoPorHora = consumoEstimadoPorHora;
        this.restriccionMinima = restriccionMinima;
        this.restriccionMaxima = restriccionMaxima;
    }
    public DispositivoEstandar(String nombre, String unEquipoConcreto, boolean esBajoConsumo, double consumoEstimadoPorHora)
    {
        this.nombre = nombre;
        this.equipoConcreto = unEquipoConcreto;
        this.esBajoConsumo = esBajoConsumo;
        this.consumoEstimadoPorHora = consumoEstimadoPorHora;
    }
    public DispositivoEstandar(String nombre, String unEquipoConcreto, boolean esBajoConsumo)
    {
        this.nombre = nombre;
        this.equipoConcreto = unEquipoConcreto;
        this.esBajoConsumo = esBajoConsumo;
    }

     public double getHorasDeUso () {
        return horasDeUso;
     }

    public double getConsumoEstimadoPorHora() {
        return consumoEstimadoPorHora;
    }

    public String getNombre() {

        return nombre;
    }


    public void serUsado(double cantHorasEstimativa) {
        horasDeUso = horasDeUso + cantHorasEstimativa;
    }



    public double getConsumoTotal() {
        return this.consumoEstimadoPorHora * this.horasDeUso;
    }

    @Override
    public boolean esInteligente() {
        return false;
    }

    public int getPuntos() {
        return 0;
    }

    public void serUsado(int cantHorasEstimativa) {
        horasDeUso = horasDeUso + cantHorasEstimativa;
    }
}
