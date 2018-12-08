package dominio.dispositivo;

public enum EstadoDispositivo {

    ENCENDIDO {
        @Override
        public void apagar(DispositivoInteligente disp) {

            disp.cambiarEstado(APAGADO);
        }

        @Override
        public void encender(DispositivoInteligente disp) {

        }

        @Override
        public void ponerModoAhorro(DispositivoInteligente disp) {

            disp.cambiarEstado(MODOAHORRO);
        }

        @Override
        public boolean estaEncendido() {

            return true;
        }

        @Override
        public boolean estaApagado() {

            return false;
        }

        @Override
        public boolean estaEnModoAhorro() {

            return false;
        }
    },

    APAGADO {
        @Override
        public void apagar(DispositivoInteligente disp) {

        }

        @Override
        public void encender(DispositivoInteligente disp) {

            disp.cambiarEstado(ENCENDIDO);
        }

        @Override
        public void ponerModoAhorro(DispositivoInteligente disp) {

            disp.cambiarEstado(MODOAHORRO);
        }

        @Override
        public boolean estaEncendido() {

            return false;
        }

        @Override
        public boolean estaApagado() {

            return true;
        }

        @Override
        public boolean estaEnModoAhorro() {

            return false;
        }
    },

    MODOAHORRO {
        @Override
        public void apagar(DispositivoInteligente disp) {

            disp.cambiarEstado(APAGADO);
        }

        @Override
        public void encender(DispositivoInteligente disp) {

            disp.cambiarEstado(ENCENDIDO);
        }

        @Override
        public void ponerModoAhorro(DispositivoInteligente disp) {

        }

        @Override
        public boolean estaEncendido() {

            return false;
        }

        @Override
        public boolean estaApagado() {

            return false;
        }

        @Override
        public boolean estaEnModoAhorro() {

            return true;
        }
    };

    public abstract void apagar(DispositivoInteligente disp);

    public abstract void encender(DispositivoInteligente disp);

    public abstract void ponerModoAhorro(DispositivoInteligente disp);

    public abstract boolean estaEncendido();

    public abstract boolean estaApagado();

    public abstract boolean estaEnModoAhorro();

}