package projetolabirinto;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Programa {

    public static void main(String[] args) throws IOException, Exception {
        // TODO code application logic here
        
        Labirinto labirinto = new Labirinto();
        Pilha<Coordenada> caminho = new Pilha<Coordenada> ();
        Pilha<Fila<Coordenada>> possibilidades = new Pilha<Fila<Coordenada>> ();
        Fila<Coordenada> fila = new Fila<Coordenada> ();
        
        caminho.guardeUmItem(new Coordenada(1, 1));
        System.out.println(caminho);
        
    }
}
