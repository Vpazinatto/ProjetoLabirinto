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
    
    private No prim;
    private No ulti;
    
    public ListaDesordenada() {
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
    
    public void insiraNoInicio(X i) throws Exception {
        if (i == null)
            throw new Exception("Erro ao inserir");
        
        X info;
        if (i instanceof Cloneable)
            info = meuCloneDeX (i);
        else
            info = i;
        
        this.prim = new No(info, null);
    }
    
    public void insiraNoFinal(X i) {
        
    }
    
    public void removePrimeiro() {
        
    }
    
    public void removeUltimo() {
    
    }
    
    public X getPrimeiro() {
        return this.prim.info;
    }
    
    public X getUltimo() {
        return this.ulti.info;
    }
}
