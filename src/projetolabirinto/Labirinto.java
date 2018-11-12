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

public class Labirinto {
    
    private char[][] labirinto = new char[0][0];
    private int linhasQtd = 0, colunasQtd = 0;
    private Fila<Coordenada> fila;
    private Pilha<Coordenada> caminho;
    private Pilha<Fila<Coordenada>> possibilidades;
    private Coordenada atual;
    private String nome;

    public char[][] getLabirinto()
    {
        return this.labirinto;
    }
    
    /**
     * Construtor, responsavel por instaciar o caminho que é uma Pilha e as possibilidades que é uma Pilha de Fila ambas do tipo coordenada
     * 
     * @author Vinicius Pazinatto
     * @author  Daniel Carvalho de Moura 
     * 
     * @throws Exception 
     */
    public Labirinto() throws Exception
    {
      caminho = new Pilha<Coordenada> ();
      possibilidades = new Pilha<Fila<Coordenada>> ();
    }
    
    /**
     * Coloca um * no labirinto na coordenada passado por paramentro
     * 
     * @param pos Coordenada do espaço em branco que futuramente será preenchido.
     * 
     * @author Vinicius Pazinatto
     * @author  Daniel Carvalho de Moura 
     * 
     */    
    public void setCaminho(Coordenada pos) 
    {
        this.labirinto[pos.getLinha()][pos.getColuna()] = '*';
    }
    
    /**
     * Recebe uma string passada pelo Programa e monta uma matriz que será o labirinto 
     * 
     * @param localLabirinto Nome do local do labirinto na pasta do arquivo  
     * @param nome Nome passado pelo usuário para nomear o labirinto
     * 
     * @author  Vinicius Pazinatto
     * @author  Daniel Carvalho de Moura 
     * 
     * @throws Exception 
     */
    public void montaLabirinto(String localLabirinto, String nome) throws Exception
    {
        this.nome = nome;
        
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
     * Monta o labirinto resolvido em um arquivo .txt e o disponibiliza na main
     * 
     *@author  Vinicius Pazinatto
     *@author  Daniel Carvalho de Moura 
     * 
     * @throws Exception 
     */
    public void montaResolvido() throws Exception {
        PrintWriter saida =
            new PrintWriter (
            new FileWriter (
            "Resolvido_" + this.nome));
        
        for (int l = 0; l < this.linhasQtd; l++) {
            for (int c = 0; c < this.colunasQtd; c++) {
                saida.print(this.labirinto[l][c]);
            }
            saida.println();
        }
        
        saida.close();
    }
    
    /**
     * Valida se a Entra e saída do labirinto está nos cantos do mesmo.
     * 
     * @param at Coordenada atual da entrada ou saída
     *  
     * @return verdadeiro a entrada ou saída está na borda, falso se não.
     * 
     * @author  Vinicius Pazinatto
     * @author  Daniel Carvalho de Moura
     * 
     * @throws Exception 
     */
    public boolean validaEntradaESaida(Coordenada at) throws Exception
    {
        boolean ret = true;

        if (at.getColuna() > 0 && at.getColuna() < this.colunasQtd)
            if (at.getLinha() > 0 && at.getLinha() < this.colunasQtd)
                ret = false;
        
        return ret;
    }
    
    /**
     * Percorre o labirinto e valída se possui saida
     *
     * @return verdadeiro se encontrou a saída falso se não
     * 
     * @author  Vinicius Pazinatto
     * @author  Daniel Carvalho de Moura
     * 
     * @throws Exception 
     */
    public boolean procuraEntradaESaida() throws Exception
    {
        if (this.atual == null)
          throw new Exception ("Labirinto inválido,não existe Entrada !");
        
        for (int linha=0; linha<this.linhasQtd; linha++) {
            for (int coluna=0; coluna<this.colunasQtd; coluna++ ) 
            {
                if (labirinto[linha][coluna] == 'S')
                    return true;
            }
        }
        throw new Exception ("Labirinto inválido,não existe Saída !");
    }
    
    /**
     * Encontra espaços em branco no labirinto e os armazena
     * 
     * @author  Vinicius Pazinatto
     * @author  Daniel Carvalho de Moura
     * 
     * @throws Exception 
     */
    public void procuraAdjacentes() throws Exception 
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
     * Percorre preenchendo os espaços em branco guardados pela fila
     * 
     * @author Vinicius Pazinatto
     * @author  Daniel Carvalho de Moura
     * @throws Exception 
     */
    public void preencheCaminho () throws Exception
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
}
