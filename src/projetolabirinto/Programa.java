package projetolabirinto;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Programa {

    public static void main(String[] args) throws IOException, Exception {
        // TODO code application logic here
       //try
       //{ 
        Labirinto labirinto = new Labirinto();
        //labirinto.procuraAdjacentes();
        Pilha<Coordenada> caminho = new Pilha<Coordenada> ();
        Pilha<Fila<Coordenada>> possibilidades = new Pilha<Fila<Coordenada>> ();
        
        Scanner s = new Scanner(System.in);
        File arquivos[];
        File arquivo = new File("arquivos-texto");
        arquivos = arquivo.listFiles();
        
        for(int i = 0; i < arquivos.length; i++){
            System.out.println(arquivos[i].getName());
        }
        
        System.out.println("");
        
        System.out.print("Escolha um dos labirintos acima: ");
        String file = s.nextLine();
        
        for(int i = 0; i < arquivos.length; i++){
            if (arquivos[i].getName().equals(file)) {
                file = arquivos[i].getAbsolutePath();
            }
        }
     
        labirinto.montaLabirinto(file);
        labirinto.procuraEntradaESaida();
        labirinto.preencheCaminho();
        
        BufferedReader entrada = new BufferedReader (new FileReader (file));
        while (entrada.ready())
        {
            String linha = entrada.readLine();
            
            System.out.println(linha);
        }
    }
}
