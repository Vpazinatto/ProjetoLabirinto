package projetolabirinto;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Programa {

    public static void main(String[] args) throws IOException, Exception {
        // TODO code application logic here
        
        Labirinto labirinto = new Labirinto();
        
        System.out.println(labirinto.procuraEntradaESaida());
        
        /*
        Pilha<Coordenada> caminho = new Pilha<Coordenada> (40);
        Pilha<Fila<Coordenada>> possibilidades = new Pilha<Fila<Coordenada>> (40);
        Fila<Coordenada> fila = new Fila<Coordenada> (3);
        
        caminho.insereUmItem(new Coordenada(1, 1));
        System.out.println(caminho);
        */
    }
}
