package projetolabirinto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Labirinto {
    
    private char[][] labirinto = new char[5][8];

    public char[][] getLabirinto() {
        return this.labirinto;
    }
    
    public Labirinto() throws IOException, Exception {
       this.montaLabirinto();
       if (this.procuraEntrada() == false)
           throw new Exception("O Labirinto não possui entrada");
    }
    
    public void montaLabirinto() throws IOException {
        
        BufferedReader entrada = new BufferedReader (new FileReader ("F:\\ProjetoLabirinto\\arquivos-texto\\labirinto1.txt"));
        int l = 0;
        int o=0;

        while (entrada.ready()) {
            String linha = entrada.readLine();

            for (int i=0; i<linha.length(); i++) {
                labirinto[l][i] = linha.charAt(i);
            }
            
            l++;
        }
        
        entrada.close();
    }
    
    public boolean procuraEntrada() throws Exception {
        for (int linha=0; linha<this.labirinto.length; linha++) {
            for (int coluna=0; coluna<this.labirinto.length; coluna++ ) {
                if (labirinto[linha][coluna] == 'E')
                    return true;
            }
        }
        return false;
    }
}
