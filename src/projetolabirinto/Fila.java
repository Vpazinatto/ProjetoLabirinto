package projetolabirinto;

import java.lang.reflect.*;

public class Fila <X> implements Cloneable
{
    private ListaDesordenada<X> lista;

    private X meuCloneDeX (X x)
    {
        X ret=null;
        
        try
        {
            Class<?> classe = x.getClass();
            Class<?>[] tipoParametroFormal = null; // null pq clone tem 0 parametros
            Method metodo = classe.getMethod ("clone", tipoParametroFormal);
            Object[] parametroReal = null; // null pq clone tem 0 parametros
            ret = (X)metodo.invoke (x, parametroReal);
        }
        catch (Exception erro)
        {}

        return ret;
    }

    public Fila () throws Exception
    {
        this.lista = new ListaDesordenada<X> ();
    }

    public void guardeUmItem (X x) throws Exception
    {
        this.insiraNoFinal (x);
    }

    public X getUmItem () throws Exception
    {
        return this.getPrimeiro();
    }

    public void jogueUmItemFora () throws Exception
    {
        this.removePrimeiro ();
    }
    
    private void insiraNoFinal(X x) {

    }

    private X getPrimeiro() {
        
    }

    private void removePrimeiro() {
        
    }


    public boolean vazia ()
    {
        if (this.lista.vazia()) // fazer este método em lista
            return true;
        else
            return false;
    }

    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

        Fila<X> fil = (Fila<X>)obj;

        if (this.qtd!=fil.qtd)
            return false;

        int iThis = this.inicio;
        int iFil  = fil .inicio;

        for (int i=0; i<this.qtd; i++)
        {
                if (!this.item[iThis].equals(fil.item[iFil]))
                    return false;

                iThis++;
                if(iThis==this.item.length)
                    iThis=0;

                iFil++;
                if(iFil==fil.item.length)
                    iFil=0;
        }

        return true;
    }

    public String toString ()
    {
        String ret="";

        int iThis = this.inicio;

        for (int i=0; i<this.qtd; i++)
        {
            ret += this.item[iThis];

            //if (i<this.qtd-1)
            if (iThis!=this.fim)
                ret += ", ";

            iThis++;
            if(iThis==this.item.length)
                iThis=0;
        }

        return ret;
    }

        public int hashCode ()
        {
            int ret = 666; // qualquer valor diferente de zero

            ret = 2*ret + new Integer(this.inicio).hashCode();
            ret = 3*ret + new Integer(this.fim   ).hashCode();
            ret = 5*ret + new Integer(this.qtd   ).hashCode();


	    int iThis = this.inicio;

	    for (int i=0; i<this.qtd; i++)
	    {
	        ret = 7*ret + this.item[iThis].hashCode();

		iThis++;
		if(iThis==this.item.length)
		    iThis=0;
	    }

            // 2* ou 3* ou 5* ou 7* pq tais numeros sao primos
            // pode-se usar sempre o mesmo primo, ou variar

            return ret;
    }

    public Fila (Fila<X> modelo) throws Exception
    {
		if (modelo==null)
		    throw new Exception ("Modelo ausente");

		this.inicio = modelo.inicio;
		this.fim    = modelo.fim;
		this.qtd    = modelo.qtd;
		this.item   = new Object [modelo.item.length];

	    int pos = modelo.inicio;
	    for (int i=0; i<modelo.qtd; i++)
	    {
	        this.item[pos] = modelo.item[pos];

			pos++;
			if(pos==modelo.item.length)
		   		pos=0;
	    }
    }

    public Object clone ()
    {
        Fila<X> ret=null;

        try
        {
            ret = new Fila<X> (this);
        }
        catch (Exception erro)
        {}

        return ret;
    }
}
