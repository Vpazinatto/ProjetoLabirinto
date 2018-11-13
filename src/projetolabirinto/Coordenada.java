package projetolabirinto;

/**
* Esta classe consiste em métodos para armazenar e recuperar coordenadas específicas dentro do labirinto.
* 
* 
* @author Vinicius Pazinatto
* @author Daniel Carvalho de Moura
 */
public class Coordenada {
    
    private int linha, coluna; 
    
    /**
     * Setter responsável por setar a linha da coordeanda
     * 
     * @author Vinicius Pazinatto
     * @author Daniel Carvalho de Moura
     * @param linha
     * 
     */
    public void setLinha(int linha) {
        this.linha = linha;
    }
    
    /**
     * Setter responsável por setar a coluna da coordeanda
     * 
     * @author Vinicius Pazinatto
     * @author Daniel Carvalho de Moura 
     * 
     * @param coluna coluna da coordeanda
     * 
     */
    public void setColuna(int coluna) {
        this.coluna = coluna;
    }
    
    /**
     * Getter responsável por retornar a linha da coordeanda
     * 
     * @author Vinicius Pazinatto
     * @author Daniel Carvalho de Moura 
     * 
     * @return retorna a linha da coordeanda
     */
    public int getLinha() {
        return this.linha;
    }
    
    /**
     * Getter responsável por retornar a coluna da coordeanda
     * 
     * @author Vinicius Pazinatto
     * @author Daniel Carvalho de Moura 
     * 
     * @return retorna a coluna da coordeanda
     */
    public int getColuna() {
        return this.coluna;
    }
    
    /**
     * Construtor, Deterima a coordenada
     * 
     * @author Vinicius Pazinatto
     * @author Daniel Carvalho de Moura
     * 
     * @param l Linha da coordeanda
     * @param c Coluna da coordenada
     * @throws Exception
     */
    public Coordenada(int l, int c) throws Exception {
        this.linha = l;
        this.coluna = c;
    }
    
    /**
     * ToString, retorna uma coordenada com a linha e coluna
     * (1, 1)
     * 
     * @author Vinicius Pazinatto
     * @author Daniel Carvalho de Moura 
     * 
     * @return retorna uma string com a linha e coluna da coordenada.
     */
    @Override
    public String toString() {
        return "(" + this.linha + "," + this.coluna + ")";
    }
}
