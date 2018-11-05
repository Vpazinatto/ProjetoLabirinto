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
        X info = null;
        
        if (x instanceof Cloneable)
            info = this.meuCloneDeX(x);
        else
            info = x;
        
        this.lista.insiraNoTopo(info);
    }

    public X getUmItem () throws Exception
    {
        if (lista.vazia())
            throw new Exception ("Lista vazia !");
        X ret = null;
        X primeiro = this.lista.getUltimo();
        
        if (primeiro instanceof Cloneable)
            ret = this.meuCloneDeX(primeiro);
        else
            ret = primeiro;
        
        return ret;
    }
    
    public void jogueUmItemFora () throws Exception
    {
        this.lista.removeUltimo();
    }
    
    @Override
    public String toString()
    {
        return this.lista.toString();
    }
    
    @Override
    public int hashCode()
    {
        return this.lista.hashCode();
    }
    
    @Override
    public boolean equals(Object obj)
    {
        return this.lista.equals(obj);
    }
}
