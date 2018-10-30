package projetolabirinto;

import java.lang.reflect.*;

public class ListaDesordenada<X> {
    
    private class No
    {
        private X  info;
        private No prox;

        public No (X i, No p)
        {
            this.info = i;
            this.prox = p;
        }

        public X getInfo ()
        {
            return this.info;
        }

        public No getProx ()
        {
            return this.prox;
        }

        public void setInfo (X i)
        {
            this.info = i;
        }

        public void setProx (No p)
        {
            this.prox = p;
        }
    }
    //Var
    private No prim;
    private No ulti;
    
    public ListaDesordenada() 
    {
        this.prim = null;
        this.ulti = null;
    }
    
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
    
    public boolean vazia() 
    {
        if (this.prim == null)
            return true;
        else
            return false;
    }
    
    public void insiraNoTopo(X i) throws Exception 
    {
        if (this.prim == null) {
            this.prim = new No(i, null);
            this.ulti = this.prim;
            return;
        }
        
        No novo = new No(i, this.ulti);
        this.ulti = novo;
    }
    
    public void insiraNoFinal(X i) throws Exception 
    {
        if (this.prim == null) {
            this.prim = new No(i, null);
            this.ulti = this.prim;
            return;
        }
        
        this.ulti = new No(i, null);
    }
    
                
    public void removePrimeiro()  throws Exception
    {
        if (this.prim == null)    
            throw new Exception ("Lista vazia !");
        
        this.prim = this.prim.getProx();
    }
    
    //removeTopo
    public void removeUltimo() throws Exception
    {
        if (this.ulti == null)
            throw new Exception ("Lista vazia !");
        
        this.ulti = this.ulti.getProx();
    }
    
    public X getPrimeiro() throws Exception
    {
        return this.prim.info;
    }
    
    public X getUltimo() throws Exception
    {
        return this.ulti.info;
    }
    
    @Override
    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        if (this.getClass()!=obj.getClass())
            return false;

        ListaDesordenada<X> lis=(ListaDesordenada<X>)obj;

        No atualThis=this.prim, atualLis=lis.prim;

        while (atualThis!=null && atualLis!=null)
        {
                if (!atualThis.getInfo().equals(atualLis.getInfo()))
                        return false;

                atualThis=atualThis.getProx();
                atualLis =atualLis .getProx();
        }

        if (atualThis!=null)
                return false;

        return atualLis == null;
    }

    @Override
    public int hashCode ()
    {
        int ret=666;

        No atual=this.prim;
        while (atual!=null)
        {
            ret = ret*7 + atual.getInfo().hashCode();
            atual= atual.getProx();
        }

        return ret;
    }
    
    @Override
    public String toString ()
    {
        String ret="";

        No atual=this.prim;
        while (atual!=null)
        {
            ret += atual.getInfo()+" ";
            atual= atual.getProx();

        }

        return ret;
    }
}
