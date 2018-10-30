package projetolabirinto;

import java.lang.reflect.*;

public class Pilha<X> {
    
    private ListaDesordenada<X> lista;
    
    private X meuCloneDeX (X x)
    {
        X ret=null;
        
        try
        {
          //ret = (X)x.clone();
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
            
    public Pilha() throws Exception 
    {
        this.lista = new ListaDesordenada<X> ();
    }

    public void guardeUmItem (X x) throws Exception
    {
        this.insiraNoInicio (x);
    }

    public X getUmItem () throws Exception
    {
        return this.getPrimeiro();
    }

    public void jogueUmItemFora () throws Exception
    {
        this.removePrimeiro();
    }
    
    private void insiraNoInicio (X x) throws Exception
    {
        X info = null;
        
        if (x instanceof Cloneable)
            info = this.meuCloneDeX(x);
        else
            info = x;
        
        lista.insiraNoTopo(info);
    }
    
    private X getPrimeiro () throws Exception
    {
        if (lista.vazia())
            throw new Exception ("Lista vazia !");
        X ret = null;
        X primeiro = lista.getPrimeiro();
        
        if (primeiro instanceof Cloneable)
            ret = this.meuCloneDeX(primeiro);
        else
            ret = primeiro;
        
        return ret;
    }
    
    private void removePrimeiro () throws Exception
    {
        lista.removePrimeiro();
    }
    
    /*
    @Override
    public String toString()
    {
        String ret="";

        int iThis = this.inicio;
        for (int i = 0; i < this.qtd; i++)
        {
            ret+= this.item[iThis];

            //if (i==this.qtd-1)
            if (iThis!=this.topo)
                ret += ", ";

            iThis++;
            if(iThis==this.item.length)
                iThis=0;
        }
        
        return ret;
    }*/
    
}
