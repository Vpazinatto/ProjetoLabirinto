package projetolabirinto;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *@author Vinicius Pazinatto
 * @author Daniel Carvalho de Moura
 */
public class Programa {
    public static void main(String[] args) throws Exception {
        try
        {
            Labirinto labirinto;
            String nome = "";
            Pilha<Coordenada> caminho = new Pilha<Coordenada> ();
            Pilha<Fila<Coordenada>> possibilidades = new Pilha<Fila<Coordenada>> ();
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("===============================================");
            System.out.println("Seja bem vindo ao Resolvedor de Labirintos");
            System.out.println("===============================================");
            System.out.println("");
            
            for(;;)
            {
                try
                {
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

                labirinto = new Labirinto(file);
                System.out.println();

                labirinto.resolveLabirinto();

                PrintWriter saida =
                    new PrintWriter (
                    new FileWriter (
                    "Resolvido_" + nome));

                for (int l = 0; l < labirinto.getLinhasQtd(); l++) {
                    for (int c = 0; c < labirinto.getColunasQtd(); c++) {
                        saida.print(labirinto.getLabirinto()[l][c]);
                    }
                    saida.println();
                }

                saida.close();

                System.out.println("Saída encontrada, o labirinto resolvido está na pasta raiz do programa!");
                }
                catch (Exception e)
                {
                   System.out.println(e.getMessage());
                }
            
             System.out.println ();
             System.out.println ("Deseja resolver outro labirinto? (S/N)");
             char resp = Character.toUpperCase(teclado.readLine().charAt(0));
             if (resp == 'N')
             {
                System.out.println ("Obrigado por jogar, até mais !");
		break;
             }

            }
        }
        catch (Exception e)
        {}
    }
}
