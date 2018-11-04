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
    private int linhaAtual, colunaAtual;
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
                if (linha.charAt(i) == 'E')
                    atual = new Coordenada (l,i);
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
        
        if (this.colunaAtual-1 >= 0)
            if (this.labirinto[atual.getLinha()][atual.getColuna()-1] == ' '  || this.labirinto[atual.getLinha()][atual.getColuna()-1] == 'S')
                fila.guardeUmItem(new Coordenada(atual.getLinha(), atual.getColuna()-1));
        
        if (this.linhaAtual+1 <= this.linhasQtd)
            if (this.labirinto[atual.getLinha()+1][atual.getColuna()] == ' '  || this.labirinto[atual.getLinha()+1][atual.getColuna()] == 'S')
                fila.guardeUmItem(new Coordenada(atual.getLinha()+1, atual.getColuna()));       
        
        if (this.linhaAtual-1 >= 0)
            if (this.labirinto[atual.getLinha()-1][atual.getColuna()] == ' ' || this.labirinto[atual.getLinha()-1][atual.getColuna()] == 'S')
                fila.guardeUmItem(new Coordenada(atual.getLinha()-1, atual.getColuna())); 
        
        
        /*
        if (this.labirinto[this.linhaAtual][this.colunaAtual+1] == ' ')
            fila.guardeUmItem(new Coordenada(this.linhaAtual, this.colunaAtual+1));  
        
        if (this.labirinto[this.linhaAtual][this.colunaAtual-1] == ' ')
            fila.guardeUmItem(new Coordenada(this.linhaAtual, this.colunaAtual-1));
        
        if (this.labirinto[this.linhaAtual+1][this.colunaAtual] == ' ')
            fila.guardeUmItem(new Coordenada(this.linhaAtual+1, this.colunaAtual));
        
        if (this.labirinto[this.linhaAtual-1][this.colunaAtual] == ' ')
            fila.guardeUmItem(new Coordenada(this.linhaAtual-1, this.colunaAtual));
        
        System.out.println(fila);
        */
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
           
           this.labirinto[atual.getLinha()][atual.getColuna()] = ' '; 
           
           fila.guardeUmItem(possibilidades.getUmItem().getUmItem());
           possibilidades.jogueUmItemFora();
         }     
        
         atual = fila.getUmItem();
         fila.jogueUmItemFora();
        
         if (labirinto[atual.getLinha()][atual.getColuna()] == ' ')
          this.setCaminho(atual);
         else
          break;

         caminho.guardeUmItem(atual);
         possibilidades.guardeUmItem(fila);
         
         
        }         
        
        
        
        
        System.out.println(labirinto[1][0]);
        System.out.println(labirinto[1][1]);
        System.out.println(labirinto[1][2]);
        System.out.println(labirinto[1][3]);
        

    }
   
    
}
