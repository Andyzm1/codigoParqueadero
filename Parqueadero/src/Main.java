import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
    Parqueadero parqueadero =new Parqueadero();
        parqueadero.entrarCarro("PCU8423");
    System.out.println(parqueadero.calcularPuestosLibres());
    System.out.println(parqueadero.buscarPuestoLibre());
        parqueadero.avanzarHora();
        parqueadero.entrarCarro("PCT8425");
        parqueadero.avanzarHora();
        parqueadero.entrarCarro("PBD1154");
    System.out.println(parqueadero.buscarPuestoLibre());
        parqueadero.avanzarHora();
        parqueadero.avanzarHora();
        parqueadero.avanzarHora();
    System.out.println("En promedio estan "+parqueadero.darTiempoPromedio()+" horas "+parqueadero.getCarrosCalculados()+" carros.");
        parqueadero.sacarCarro("PCU8423");
        parqueadero.avanzarHora();
    System.out.println("La placa del carro con más horas es: "+parqueadero.carroMasHoras());
    System.out.println("¿Hay carros parqueados más de ocho horas? "+parqueadero.carroMasDeOchoHoras());
    System.out.println("¿Hay carros con placas iguales? "+parqueadero.carrosMismaPlaca());
    System.out.println("Hay "+parqueadero.carrosConPB()+" carros con placa iniciada en PB.");
        System.out.println("Carros que han estado estacionados por más de tres horas:");
        ArrayList<Carro> carrosMasDeTresHoras = parqueadero.carrosMasDeTresHoras();
        for (Carro carro : carrosMasDeTresHoras) {
            System.out.println("Placa: " + carro.getPlaca() + ", Tiempo de parqueo: " + carro.gettTotal(parqueadero.darHoraActual()) + " horas");
        }

        parqueadero.metodo1();
    System.out.println("Se sacaron "+ parqueadero.desocuparParqueadero()+ " carros, para vaciar el parqueadero.");
        parqueadero.metodo2();

    }
}