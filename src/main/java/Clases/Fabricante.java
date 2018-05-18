package Clases;

public class Fabricante {
    int idFabrica;
    int idDispositivo;
    public Fabricante(int identificadorFabrica)
    {
        idFabrica = identificadorFabrica;
    }
    public void recibirIDDispositivo(int idFabricaDispositivo)
    {
        idDispositivo = idFabricaDispositivo;
    }
    public int idFabrica()
    {
        return idFabrica;
    }

    public boolean verificarCoincidenciaIDS()
    {
        return idFabrica == idDispositivo;
    }

}
