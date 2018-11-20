package projetolabirinto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Esta classe consiste em métodos para instanciar um labirinto, verificar se é válido, e resolvê-lo.
 * 
 * @author Vinicius Vinicius Pazinatto
 * @author Daniel Carvalho de Moura
 */
public class Labirinto {
    
    private char[][] labirinto = new char[0][0];
    private int linhasQtd, colunasQtd = 0;
    private Fila<Coordenada> fila;
    private Pilha<Coordenada> caminho;
    private Pilha<Fila<Coordenada>> possibilidades;
    private Coordenada atual;

    /**
     * Getter responsável por retornar o labirinto completo.
     *
     * @return retorna o labirinto armazenado na classe.
     */
    public char[][] getLabirinto()
    {
        return this.labirinto;
    }
    
    /**
     * Getter responsável por retornar o número de linhas do labirinto.
     *
     * @return retorna o linhaQtd armazenado na classe.
     */
    public int getLinhasQtd()
    {
        return this.linhasQtd;
    }
    
    /**
     * Getter responsável por retornar o número de colunas do labirinto.
     *
     * @return retorna o colunasQtd armazenado na classe.
     */
    public int getColunasQtd()
    {
        return this.colunasQtd;
    }
    
    /**
     * Construtor, responsavel por instaciar o caminho que é uma Pilha e as possibilidades que é uma Pilha de Fila ambas do tipo coordenada
     * 
     * @author Vinicius Pazinatto
     * @author Daniel Carvalho de Moura 
     * 
     * @throws Exception se houver algum erro ao instânciar caminho, possibilidades ou o labirinto.
     */
    public Labirinto(String localArqv) throws Exception
    {
       this.montaLabirinto(localArqv);
       caminho = new Pilha<Coordenada> ();
       possibilidades = new Pilha<Fila<Coordenada>> ();
    }
    
    /**
     * SetCaminho, coloca um * no labirinto na coordenada passado por paramentro
     * 
     * @param pos Coordenada do espaço em branco que futuramente será preenchido.
     * 
     * @author Vinicius Pazinatto
     * @author Daniel Carvalho de Moura 
     * 
     */    
    private void setCaminho(Coordenada pos) 
    {
        this.labirinto[pos.getLinha()][pos.getColuna()] = '*';
    }
    
    /**
     * MontaLabirinto, recebe o caminho e o nome do labirinto, ambas Strings, e monta uma matriz que será o labirinto 
     * 
     * @param localLabirinto Nome do local do labirinto na pasta do arquivo  
     * @param nome Nome passado pelo usuário para nomear o labirinto
     * 
     * @author  Vinicius Pazinatto
     * @author  Daniel Carvalho de Moura 
     * 
     * @throws Exception se a entrada estiver numa posição inválida ou o parâmetro for nulo ou vazio.
     */
    private void montaLabirinto(String localLabirinto) throws Exception
    {
        if (localLabirinto == null || localLabirinto.equals(""))
            throw new Exception ("Local do labirinto nulo !");
        
        BufferedReader entrada = new BufferedReader (new FileReader (localLabirinto));
        
        while (entrada.ready())
        {
            String linha = entrada.readLine();
            
            while(this.colunasQtd < linha.length())
                this.colunasQtd++;
            
            this.linhasQtd++;
        }

        this.labirinto = new char[this.linhasQtd][this.colunasQtd];  
        
        entrada = new BufferedReader (new FileReader (localLabirinto));
        int l = 0;
        
        while (entrada.ready())
        {
            String linha = entrada.readLine();

            for (int i=0; i<linha.length(); i++) 
            {
                this.labirinto[l][i] = linha.charAt(i);
                
                if (linha.charAt(i) == 'E')
                {
                    atual = new Coordenada (l,i);
                
                if (!this.validaEntradaESaida(atual))
                    throw new Exception ("Labirinto inválido ! A entrada não está numa posição válida !");
                }
            }
            l++;
        }
        entrada.close();
    }
    
    /**
     * ValidaEntradaESaída, valida se a entrada e saída do labirinto está nos cantos do mesmo.
     * 
     * @param at Coordenada atual da entrada ou saída
     *  
     * @return retorna true se a entrada ou saída está na borda, false se não.
     * 
     * @author  Vinicius Pazinatto
     * @author  Daniel Carvalho de Moura
     *  
     */
    public boolean validaEntradaESaida(Coordenada at)
    {
        boolean ret = true;

        if (at.getColuna() > 0 && at.getColuna() < this.colunasQtd)
            if (at.getLinha() > 0 && at.getLinha() < this.colunasQtd)
                ret = false;
        
        return ret;
    }
    
    /**
     * ProcuraSaida, valida se foi encontrada saída.
     *
     * @return true se encontrou a saída, false se não
     * 
     * @author  Vinicius Pazinatto
     * @author  Daniel Carvalho de Moura
     * 
     * @throws Exception se não tiver saída ou entrada, lança exceção
     */
    private boolean procuraSaida() throws Exception
    {        
        for (int linha=0; linha<this.linhasQtd; linha++) {
            for (int coluna=0; coluna<this.colunasQtd; coluna++ ) 
            {
                if (labirinto[linha][coluna] == 'S')
                    return true;
            }
        }
        return false;
    }
    
    /**
     * ProcuraAdjacentes, busca as coordenadas adjacentes da coordenada atual, e verifica se são espaços vazios, a saída ou parede.Armazena na Fila se for espaço vazio ou a saída.
     * 
     * @author  Vinicius Pazinatto
     * @author  Daniel Carvalho de Moura
     * 
     * @throws Exception se houver erro ao instânciar a fila
     */
    private void procuraAdjacentes() throws Exception 
    {
        fila = new Fila<Coordenada> ();
        
        if (atual.getColuna()+1 < this.colunasQtd)
            if (this.labirinto[atual.getLinha()][atual.getColuna()+1] == ' '  || this.labirinto[atual.getLinha()][atual.getColuna()+1] == 'S')
               fila.guardeUmItem(new Coordenada(atual.getLinha(), atual.getColuna()+1));
        
        if (atual.getColuna()-1 >= 0)
            if (this.labirinto[atual.getLinha()][atual.getColuna()-1] == ' '  || this.labirinto[atual.getLinha()][atual.getColuna()-1] == 'S')
                fila.guardeUmItem(new Coordenada(atual.getLinha(), atual.getColuna()-1));
        
        if (atual.getLinha()+1 < this.linhasQtd)
            if (this.labirinto[atual.getLinha()+1][atual.getColuna()] == ' '  || this.labirinto[atual.getLinha()+1][atual.getColuna()] == 'S')
                fila.guardeUmItem(new Coordenada(atual.getLinha()+1, atual.getColuna()));       
        
        if (atual.getLinha()-1 >= 0)
            if (this.labirinto[atual.getLinha()-1][atual.getColuna()] == ' ' || this.labirinto[atual.getLinha()-1][atual.getColuna()] == 'S')
                fila.guardeUmItem(new Coordenada(atual.getLinha()-1, atual.getColuna())); 
    }
    
    /**
     * PreencheCaminho, percorre o labirinto preenchendo os espaços em branco guardados pela fila, alternando entre modo progressivo e modo regressivo quando necessário.
     * 
     * @author Vinicius Pazinatto
     * @author  Daniel Carvalho de Moura
     * 
     * @throws Exception se houver erros com os métodos de fila,pilha.
     */
    private void preencheCaminho () throws Exception
    {
        //Modo progressivo
        for(;;)
        {
            this.procuraAdjacentes();

            while (fila.vazia())
            {
                //Modo Regressivo
                atual = caminho.getUmItem();
                caminho.jogueUmItemFora();

                this.procuraAdjacentes();
                
                this.labirinto[atual.getLinha()][atual.getColuna()] = '-'; 

                if (!possibilidades.getUmItem().vazia())
                    fila.guardeUmItem(possibilidades.getUmItem().getUmItem());
                
                possibilidades.jogueUmItemFora();
            }     

            if (fila.vazia())
            throw new Exception ("Não existe solução para este labirinto !");
            
            atual = fila.getUmItem();
            fila.jogueUmItemFora();

            if (labirinto[atual.getLinha()][atual.getColuna()] == ' ')
                this.setCaminho(atual);

            caminho.guardeUmItem(atual);
            possibilidades.guardeUmItem(fila);
            
            if (labirinto[atual.getLinha()][atual.getColuna()] == 'S') 
                return;
        }
    }
    
    /**
     * ResolveLabirinto, executa os métodos necessários para resolver o labirinto.
     * 
     * @author Vinicius Pazinatto
     * @author Daniel Carvalho de Moura
     * 
     * @throws Exception se houver erros com os métodos para resolução do labirinto.
     */
    public void resolveLabirinto () throws Exception
    {
        if (!this.procuraSaida())
            throw new Exception ("O labirinto não possui saída válida !");
        this.preencheCaminho();
    }

}
