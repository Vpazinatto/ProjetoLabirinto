package projetolabirinto;

public class Coordenada {
    
    private int linha, coluna; 
    
    public void setLinha(int linha) {
        this.linha = linha;
    }
    
    public void setColuna(int coluna) {
        this.coluna = coluna;
    }
    
    public int getLinha() {
        return this.linha;
    }
    
    public int getColuna() {
        return this.coluna;
    }
    
    public Coordenada(int l, int c) throws Exception {
        this.linha = l;
        this.coluna = c;
    }
    
    @Override
    public String toString() {
        return "(" + this.linha + "," + this.coluna + ")";
    }
}
