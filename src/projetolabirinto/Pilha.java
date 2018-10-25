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
    
    public void insereUmItem() throws Exception {
        this.qtd++;
    }
    
    public void removeUmItem() throws Exception {
        this.qtd--;
    }
    
    public X getUmItem () throws Exception {
        if (this.qtd==0)
            throw new Exception ("Nada para recuperar");

        return (X)this.item[topo];
    } 
}
