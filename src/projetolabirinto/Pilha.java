package projetolabirinto;

public class Pilha<X> {
    
    private Object[] item;
    private int inicio = 0,
                topo = -1,
                qtd = 0;
    
    public Pilha(int capacidade) throws Exception {
        
        if (capacidade <= 0)
            throw new Exception("Capacidade invalida");
            
        this.item = new Object [capacidade];
    }
    
    public void insereUmItem(X x) throws Exception {        
        this.topo++;
        if (this.topo==item.length)
            this.topo = 0;
        this.item[topo] = x;
        this.qtd++;
    }
    
    public void removeUmItem() throws Exception {       
        this.item[this.inicio] = null;
        this.inicio++;
        if (this.inicio==this.item.length)
            this.inicio=0;
        this.qtd--;
    }
    
    public X getUmItem () throws Exception {
        if (this.qtd==0)
            throw new Exception ("Nada para recuperar");

        return (X)this.item[topo];
    }
    
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
    }
    
}
