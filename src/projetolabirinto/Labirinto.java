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

    public char[][] getLabirinto()
    {
        return this.labirinto;
    }
    
    public Labirinto() throws Exception
    {
      caminho = new Pilha<Coordenada> ();
      possibilidades = new Pilha<Fila<Coordenada>> ();
    }
    
    public void montaLabirinto(String labirinto) throws Exception
    {
        BufferedReader entrada = new BufferedReader (new FileReader (labirinto));
        
        while (entrada.ready())
        {
            String linha = entrada.readLine();
            
            while(this.colunasQtd < linha.length())
                this.colunasQtd++;
            
            this.linhasQtd++;
        }

        this.labirinto = new char[this.linhasQtd][this.colunasQtd];  
        
        entrada = new BufferedReader (new FileReader (labirinto));
        int l = 0;
        
        while (entrada.ready())
        {
            String linha = entrada.readLine();

            for (int i=0; i<linha.length(); i++) 
            {
                this.labirinto[l][i] = linha.charAt(i);
                if (linha.charAt(i) == 'E') {
                    atual = new Coordenada (l,i);
                    if (atual.getColuna()+1 > this.colunasQtd)
                        throw new Exception("Entrada do labirinto inválida!");
                    
                    if (atual.getColuna()-1 < this.colunasQtd)
                        throw new Exception("Entrada do labirinto inválida!");
                    
                    if (atual.getLinha()-1 < this.linhasQtd)
                        throw new Exception("Entrada do labirinto inválida!");
                    
                    if (atual.getLinha()+1 < this.linhasQtd)
                        throw new Exception("Entrada do labirinto inválida!");    
                }
            }
            
            l++;
        }
        entrada.close();
    }
    
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
    
    public void setCaminho(Coordenada pos) 
    {
        this.labirinto[pos.getLinha()][pos.getColuna()] = '*';
    }
    
    public void procuraAdjacentes() throws Exception 
    {
        fila = new Fila<Coordenada> ();
        
        if (atual.getColuna()+1 <= this.colunasQtd)
            if (this.labirinto[atual.getLinha()][atual.getColuna()+1] == ' '  || this.labirinto[atual.getLinha()][atual.getColuna()+1] == 'S')
               fila.guardeUmItem(new Coordenada(atual.getLinha(), atual.getColuna()+1));
        
        if (atual.getColuna()-1 >= 0)
            if (this.labirinto[atual.getLinha()][atual.getColuna()-1] == ' '  || this.labirinto[atual.getLinha()][atual.getColuna()-1] == 'S')
                fila.guardeUmItem(new Coordenada(atual.getLinha(), atual.getColuna()-1));
        
        if (atual.getLinha()+1 <= this.linhasQtd)
            if (this.labirinto[atual.getLinha()+1][atual.getColuna()] == ' '  || this.labirinto[atual.getLinha()+1][atual.getColuna()] == 'S')
                fila.guardeUmItem(new Coordenada(atual.getLinha()+1, atual.getColuna()));       
        
        if (atual.getLinha()-1 >= 0)
            if (this.labirinto[atual.getLinha()-1][atual.getColuna()] == ' ' || this.labirinto[atual.getLinha()-1][atual.getColuna()] == 'S')
                fila.guardeUmItem(new Coordenada(atual.getLinha()-1, atual.getColuna())); 
    }
    
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
            {
              this.finalizaJogo();
              break;
            }
        }
    }
    
    public void finalizaJogo()
    {
        for(int l=0; l < this.linhasQtd; l++)
        {
            for (int c=0; c < this.colunasQtd; c++)
            {
                System.out.print(this.labirinto[l][c]);
            }
            
            System.out.println("");
        } 
        
        System.out.println();
        System.out.println("Saída do labirinto encontrada!");
    }
}
