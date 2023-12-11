import java.util.ArrayList;

public class Parqueadero {

    public static final int TAMANO = 40;
    public static final int NO_HAY_PUESTO = -1;
    public static final int PARQUEADERO_CERRADO = -2;
    public static final int CARRO_NO_EXISTE = -3;
    public static final int CARRO_YA_EXISTE = -4;
    public static final int HORA_INICIAL = 6;
    public static final int HORA_CIERRE = 20;
    public static final int TARIFA_INICIAL = 1;

    private Puesto puestos[];
    private double tPromedio;
    private int tarifa, caja, horaActual, cSacados, carrosCalculados, hAvanzadas;
    private boolean abierto;

    public Parqueadero( )
    {
        horaActual = HORA_INICIAL;
        abierto = true;
        tarifa = TARIFA_INICIAL;
        caja = 0;
        puestos = new Puesto[TAMANO];
        for( int i = 0; i < TAMANO; i++ ) {
            puestos[i] = new Puesto(i);
        }

    }

    public String darPlacaCarro( int pPosicion )
    {
        String respuesta = "";
        if( estaOcupado( pPosicion ) )
        {
            respuesta = "Placa: " + puestos[ pPosicion ].getCarro( ).getPlaca( );
        }
        else
        {
            respuesta = "No hay un carro en esta posición";
        }

        return respuesta;
    }

    public int entrarCarro( String pPlaca )
    {
        int resultado = 0;
        if( !abierto )
        {
            resultado = PARQUEADERO_CERRADO;
        }
        else
        {
            int numPuestoCarro = buscarPuestoCarro( pPlaca.toUpperCase( ) );
            if( numPuestoCarro != CARRO_NO_EXISTE )
            {
                resultado = CARRO_YA_EXISTE;
            }

            resultado = buscarPuestoLibre( );
            if( resultado != NO_HAY_PUESTO )
            {
                Carro carroEntrando = new Carro( pPlaca, horaActual );
                puestos[ resultado ].parquearCarro( carroEntrando );
            }
        }
        return resultado;
    }

    public int sacarCarro( String pPlaca )
    {
        int resultado = 0;
        if( !abierto )
        {
            resultado = PARQUEADERO_CERRADO;
        }
        else
        {
            int numPuesto = buscarPuestoCarro( pPlaca.toUpperCase( ) );
            if( numPuesto == CARRO_NO_EXISTE )
            {
                resultado = CARRO_NO_EXISTE;
            }
            else
            {
                Carro carro = puestos[ numPuesto ].getCarro( );
                int nHoras = carro.TiempoEnParqueadero( horaActual );
                int porPagar = nHoras * tarifa;
                caja = caja + porPagar;
                puestos[ numPuesto ].sacarCarro();
                resultado = porPagar;
                cSacados += 1;
            }
        }

        return resultado;
    }

    public int darMontoCaja( )
    {
        return caja;
    }

    public int calcularPuestosLibres( )
    {
        int puestosLibres = 0;
        for( Puesto puesto : puestos )
        {
            if( !puesto.estaOcupado( ) )
            {
                puestosLibres = puestosLibres + 1;
            }
        }
        return puestosLibres;
    }

    public void cambiarTarifa( int nTarifa )
    {
        tarifa = nTarifa;
    }

    public int buscarPuestoLibre( )
    {
        int puesto = NO_HAY_PUESTO;
        for( int i = 0; i < TAMANO && puesto == NO_HAY_PUESTO; i++ )
        {
            if( !puestos[ i ].estaOcupado( ) )
            {
                puesto = i;
            }
        }
        return puesto;
    }

    private int buscarPuestoCarro( String pPlaca )
    {
        int puesto = CARRO_NO_EXISTE;
        for( int i = 0; i < TAMANO && puesto == CARRO_NO_EXISTE; i++ )
        {
            if( puestos[ i ].tieneCarroConPlaca( pPlaca ) )
            {
                puesto = i;
            }
        }
        return puesto;
    }


    public void avanzarHora( )
    {
        if( horaActual <= HORA_CIERRE )
        {
            horaActual = ( horaActual + 1 );
        }
        if( horaActual == HORA_CIERRE )
        {
            abierto = false;
        }
        hAvanzadas += 1;

    }

    public int gethAvanzadas() {
        return hAvanzadas;
    }

    public int darHoraActual( )
    {
        return horaActual;
    }

    public boolean estaAbierto( )
    {
        return abierto;
    }

    public int darTarifa( )
    {
        return tarifa;
    }

    public boolean estaOcupado( int pPuesto )
    {
        boolean ocupado = puestos[ pPuesto ].estaOcupado( );
        return ocupado;
    }

    public int getcSacados() {
        return cSacados;
    }

    public int getCarrosCalculados() {
        return carrosCalculados;
    }


    ///Metodos aumentados

    ///2.1
    public double darTiempoPromedio(){
        double tTotal=0;
        int hTotal = 0;
        carrosCalculados = 0;
        for (int i=0; i<40; i++){
            if( estaOcupado( i ) )
            {
                hTotal += puestos[ i ].getCarro( ).gettTotal(darHoraActual());
                carrosCalculados +=1;
            }
            else
            {
                hTotal+=0;
                carrosCalculados +=0;
            }
        }
        tTotal = (double) hTotal / carrosCalculados;
        return tTotal;
    }

    ///2.2
    public String carroMasHoras() {
        String placaMayor = null;
        int poscion = 0, horas = 0, mayorHoras = 0;

        for (int i = 0; i < 40; i++) {
            if (estaOcupado(i)) {
                if (puestos[i].getCarro() != null) {
                    horas = puestos[i].getCarro().gettTotal(darHoraActual());
                    if (horas > mayorHoras) {
                        mayorHoras = horas;
                        poscion = i;
                        placaMayor = puestos[poscion].getCarro().getPlaca();
                    }
                }
            }
        }

        if (placaMayor == null) {

            placaMayor = "No hay carros en el parqueadero";
        }
        return placaMayor;
    }
    ///2.3
    public boolean carroMasDeOchoHoras(){
        boolean masOcho=false;
        int horas;
        for (int i=0; i<40; i++){
            if( estaOcupado( i ) )
            {
                horas = puestos[ i ].getCarro( ).gettTotal(darHoraActual());
                if (horas>8){
                    masOcho = true;
                }
            }

        }
        return masOcho;
    }
    ///2.4
    public ArrayList<Carro> carrosMasDeTresHoras() {
        ArrayList<Carro> carrosMasDeTresHoras = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            if (estaOcupado(i)) {
                int tParqueo = puestos[i].getCarro().gettTotal(darHoraActual());

                if (tParqueo > 3) {
                    carrosMasDeTresHoras.add(puestos[i].getCarro());

                }
            }
        }
        return carrosMasDeTresHoras;
    }

    ///2.5
    public boolean carrosMismaPlaca() {
        boolean iguales = false;
        String placa = null;
        String placa2 = null;

        for (int i = 0; i < 40; i++) {
            if (estaOcupado(i)) {
                placa = puestos[i].getCarro().getPlaca();

                if (i + 1 < 40 && estaOcupado(i + 1)) {
                    placa2 = puestos[i + 1].getCarro().getPlaca();

                    if (placa==placa2) {
                        iguales = true;
                        break;
                    }
                }
            }
        }

        return iguales;
    }
    ///3.1
    public int carrosConPB(){
        int cCarrosPB=0;
        String placa;

        for (int i = 0; i < 40; i++) {
            if (estaOcupado(i)) {
                placa = puestos[i].getCarro().getPlaca();

                if (placa.toUpperCase().startsWith("PB")) {
                    cCarrosPB += 1;

                }
            }
        }
        return cCarrosPB;
    }

///3.2
public boolean hayCarroCon24Horas() {

        for (int i = 0; i < 40; i++) {
        if (estaOcupado(i)) {
            int tParqueado = puestos[i].getCarro().gettTotal(darHoraActual());

            if (tParqueado >= 24) {
                return true;
            }
        }
    }
    return false;
}
    ///3.3
    public void metodo1( )
    {
        if (hayCarroCon24Horas()){
            System.out.println("Cantidad de carros con placa PB: "+carrosConPB());
            System.out.println("Hay algun carro parqueado por 24 horas o más: Sí.");
        }else {
            System.out.println("Cantidad de carros con placa PB: "+carrosConPB());
            System.out.println("Hay algun carro parqueado por 24 horas o más: No.");
        }
    }
    ///3.4
    public int desocuparParqueadero(){
        int inicialSacados=cSacados;
        for (int i=0; i<40; i++ ){
            if (estaOcupado(i)){
            sacarCarro(puestos[i].getCarro().getPlaca());}
        }
        int vaciado= cSacados-inicialSacados;
        return vaciado;
    }

    ///3.5
    public void metodo2( )
    {
        System.out.println("Cantidad de carros sacados: "+getcSacados());
    }

}
