package projetolabirinto;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Programa {

    public static void main(String[] args) throws IOException, Exception {

        Labirinto labirinto = new Labirinto();
        String nome = "";
        Pilha<Coordenada> caminho = new Pilha<Coordenada> ();
        Pilha<Fila<Coordenada>> possibilidades = new Pilha<Fila<Coordenada>> ();
        
        Scanner s = new Scanner(System.in);
        File arquivos[];
        File arquivo = new File("arquivos-texto");
        arquivos = arquivo.listFiles();
        
        for(int i = 0; i < arquivos.length; i++){
            System.out.println(arquivos[i].getName());
        }
        
        System.out.println();
        System.out.print("Escolha um dos labirintos acima: ");
        String file = s.nextLine();
        
        for(int i = 0; i < arquivos.length; i++){
            if (arquivos[i].getName().equals(file)) {
                file = arquivos[i].getAbsolutePath();
                nome = arquivos[i].getName();
            }
        }
            
        System.out.println();
        labirinto.montaLabirinto(file, nome);
        labirinto.procuraEntradaESaida();
        labirinto.preencheCaminho();
        labirinto.montaResolvido();
        System.out.println("Saída encontrada, o labirinto resolvido está na pasta!");
        
    }
}
