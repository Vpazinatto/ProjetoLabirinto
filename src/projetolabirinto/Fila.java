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
    
    public void insereUmItem() throws Exception {
        
        this.qtd++;
    }
    
    public void removeUmItem() throws Exception {
        this.qtd--;
    }
    
    public X getUmItem () throws Exception {
        if (this.qtd==0)
            throw new Exception ("Nada para recuperar");

        return (X)this.item[inicio];
    } 
    
}
