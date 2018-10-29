package projetolabirinto;

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
    
    public boolean vazia() 
    {
        if (this.prim == null)
            return true;
        else
            return false;
    }
    
    public void insiraNoInicio(X i) {
        
    }
    
    public void removePrimeiro() {
        
    }
    
    public void removeUltimo() {
    
    }
    
    public void insiraNoFinal() {
    
    }
    
    public No getPrimeiro() {
        return this.prim;
    }
    
    public No getUltimo() {
        return this.ulti;
    }
}
