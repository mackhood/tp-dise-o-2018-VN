package dominio.dispositivo;

public class DispositivoEstandar extends Dispositivo
{


    private DispositivoEstandar(dispositivoEstandarBuilder builder) {
        this.nombre= builder.nombre;
        this.consumoEstimadoPorHora= builder.consumoEstimadoPorHora;
        this.equipoConcreto= builder.equipoConcreto;
        this.esBajoConsumo= builder.esBajoConsumo;
        this.horasDeUso =  builder.horasDeUso;
        this.restriccionMinima= builder.restriccionMinima;
        this.restriccionMaxima= builder.restriccionMaxima;
        this.horasMaximaPorConsumo= builder.horasMaximaPorConsumo;
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



        static  class dispositivoEstandarBuilder {

           private  String nombre;
            private double consumoEstimadoPorHora;
            private String equipoConcreto;
            private boolean esBajoConsumo;
            private double horasDeUso = 0;
            private double restriccionMinima;
            private double restriccionMaxima;
            private double horasMaximaPorConsumo;


            public dispositivoEstandarBuilder(String firstName) {
                this.nombre = firstName;

            }

            public dispositivoEstandarBuilder() {

            }

            public dispositivoEstandarBuilder age(double  consumoEstimadoPorHora) {
                this.consumoEstimadoPorHora = consumoEstimadoPorHora;
                return this;
            }

            public dispositivoEstandarBuilder phone(String equipoConcreto) {
                this.equipoConcreto = equipoConcreto;
                return this;
            }

            public dispositivoEstandarBuilder address(Boolean esBajoConsumo) {
                this.esBajoConsumo = esBajoConsumo;
                return this;
            }



            public dispositivoEstandarBuilder restriccionMinima(Double restriccionMinima) {
                this.restriccionMinima = restriccionMinima;
                return this;
            }


            public dispositivoEstandarBuilder restriccionMaxima(Double restriccionMaxima) {
                this.restriccionMaxima = restriccionMaxima;
                return this;
            }

            public dispositivoEstandarBuilder horasMaximaPorConsumo(Double horasMaximaPorConsumo) {
                this.restriccionMaxima = horasMaximaPorConsumo;
                return this;
            }

            public DispositivoEstandar build() {
                return new DispositivoEstandar(this);
            }

        }


        }













