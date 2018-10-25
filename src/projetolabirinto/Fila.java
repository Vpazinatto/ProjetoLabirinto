package projetolabirinto;

public class Fila<X> {
    
    private Object[] item;
    private int inicio =  0,
                fim    = -1,
                qtd    =  0;
    
    public Fila(int capacidade) throws Exception {   
        if (capacidade <= 0)
            throw new Exception("Capacidade invalida");
            
        this.item = new Object [capacidade];

        for (int i=0; i<this.item.length; i++)
            this.item[i]=null;
    }
    
    public void insereUmItem(X x) throws Exception {
        
        if (x == null)
            throw new Exception("Guardar Oque");
        
        if (this.qtd==this.item.length)
            throw new Exception ("Fila cheia");
        
        this.fim++;
        if (this.fim==item.length)
            this.fim = 0;
        this.item[fim] = x;
        this.qtd++;
    }
    
    public void removeUmItem() throws Exception {
        if (this.qtd==0)
            throw new Exception ("Nada para jogar fora");

        this.item[this.inicio] = null;
        this.inicio++;
        if (this.inicio==this.item.length)
            this.inicio=0;
        this.qtd--;
    }
    
    public X getUmItem () throws Exception {
        if (this.qtd==0)
            throw new Exception ("Nada para recuperar");

        return (X)this.item[inicio];
    } 
    
    @Override
    public String toString() {
        String ret = "";
        return ret;
    }
    
}
