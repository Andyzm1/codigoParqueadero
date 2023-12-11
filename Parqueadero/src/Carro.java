public class Carro {
    private String placa;

    private int horaLlegada, horaSalida, tTotal;


    public Carro( String placa, int horaLlegada )
    {
        this.placa = placa;
        this.horaLlegada = horaLlegada;
    }

    public String getPlaca() {
        return placa;
    }

    public int getHoraLlegada() {
        return horaLlegada;
    }


    public boolean tienePlaca( String placa )
    {
        boolean tienePlaca = false;
        if( placa.equalsIgnoreCase( placa ) )
        {
            tienePlaca = true;
        }
        else
        {
            tienePlaca = false;
        }
        return tienePlaca;
    }

    public int gettTotal(int horaSalida) {
        if (horaSalida < horaLlegada){
            tTotal = horaSalida + (24-horaLlegada) + 1;}
        else {
            tTotal = horaSalida - horaLlegada + 1;
        }
        return tTotal;
    }

    public int TiempoEnParqueadero(int horaSalida)
    {
        tTotal = horaSalida - horaLlegada + 1;
        return tTotal;
    }


}