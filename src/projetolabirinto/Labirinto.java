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
    
    private char[][] labirinto = new char[5][8];
    private int linhaAtual, colunaAtual;
    private Fila<Coordenada> fila = new Fila<Coordenada> ();

    public char[][] getLabirinto()
    {
        return this.labirinto;
    }
    
    public Labirinto() throws Exception
    {
       this.montaLabirinto();
       if (this.procuraEntradaESaida() == false)
           throw new Exception("Labirinto inválido !");
    }
    
    public void montaLabirinto() throws Exception
    {
        Scanner s = new Scanner(System.in);
        
        System.out.print("Digite o nome do labirinto: ");
        String file = s.nextLine();
        
        File arquivos[];
        File arquivo = new File("arquivos-texto");
        arquivos = arquivo.listFiles();
        for(int i = 0; i < arquivos.length; i++){
            if (arquivos[i].getName().equals(file))
                file = arquivos[i].getAbsolutePath();
        }

        BufferedReader entrada = new BufferedReader (new FileReader (file));
        
        int l = 0;

        while (entrada.ready())
        {
            String linha = entrada.readLine();

            for (int i=0; i<linha.length(); i++) 
            {
                labirinto[l][i] = linha.charAt(i);
                
                if (linha.charAt(i) == 'E')
                    this.setAtual(l, i);
            }
            
            l++;
        }
        
        entrada.close();
    }
    
    public boolean procuraEntradaESaida() throws Exception 
    {
        
        int ret = 0;
        
        for (int linha=0; linha<this.labirinto.length; linha++) {
            for (int coluna=0; coluna<this.labirinto.length; coluna++ ) {
                if (labirinto[linha][coluna] == 'E' || labirinto[linha][coluna] == 'S')
                   ret++;
            }
            
            if (ret==2)
                return true;
        }
        return false;
    }
    
    public void setAtual(int l, int c) 
    {
        this.linhaAtual = l;
        this.colunaAtual = c;
        
        if (this.labirinto[l][c] != 'E')
            this.labirinto[l][c] = '*';
    }
    
   /* public void procuraAdjacentes() throws Exception {
        if (this.labirinto[this.linhaAtual][this.colunaAtual+1] == ' ' && this.colunaAtual+1 != )
            fila.guardeUmItem(new Coordenada(this.linhaAtual, this.colunaAtual+1));  
        
        if (this.labirinto[this.linhaAtual][this.colunaAtual-1] == ' ' && this.colunaAtual-1 != )
            fila.guardeUmItem(new Coordenada(this.linhaAtual, this.colunaAtual-1));
        
        if (this.labirinto[this.linhaAtual+1][this.colunaAtual] == ' ' && this.linhaAtual+1 != -1)
            fila.guardeUmItem(new Coordenada(this.linhaAtual+1, this.colunaAtual));
        
        if (this.labirinto[this.linhaAtual-1][this.colunaAtual] == ' ' && this.linhaAtual-1 != -1)
            fila.guardeUmItem(new Coordenada(this.linhaAtual-1, this.colunaAtual));
        
        System.out.println(fila);
    }*/
}
