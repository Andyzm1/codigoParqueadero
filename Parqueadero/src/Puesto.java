public class Puesto{
    private Carro carro;
    private int numeroPuesto;
    public Puesto( int pPuesto )
    {
        carro = null;
        numeroPuesto = pPuesto;
    }

    public Carro getCarro( )
    {
        return carro;
    }

    public boolean estaOcupado( )
    {
        boolean ocupado = carro != null;
        return ocupado;
    }
    public void parquearCarro( Carro pCarro )
    {
        carro = pCarro;
    }
    public void sacarCarro( )
    {
        carro = null;
    }

    public int darNumeroPuesto( )
    {
        return numeroPuesto;
    }
    public boolean tieneCarroConPlaca( String placa )
    {
        boolean tieneCarro = true;

        if( carro == null )
        {
            tieneCarro = false;
        }
        else if( carro.tienePlaca( placa ) )
        {
            tieneCarro = true;
        }
        else
        {
            tieneCarro = false;
        }

        return tieneCarro;
    }

}
