package projetolabirinto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Labirinto {
    
    private char[][] labirinto = new char[5][8];
    private int linhaAtual, colunaAtual;

    public char[][] getLabirinto() {
        return this.labirinto;
    }
    
    public Labirinto() throws Exception {
       this.montaLabirinto();
       if (this.procuraEntradaESaida() == false)
           throw new Exception("Labirinto inválido !");
    }
    
    public void montaLabirinto() throws Exception {
        
        BufferedReader entrada = new BufferedReader (new FileReader ("F:\\ProjetoLabirinto\\arquivos-texto\\labirinto1.txt"));
        int l = 0;

        while (entrada.ready()) {
            String linha = entrada.readLine();

            for (int i=0; i<linha.length(); i++) {
                labirinto[l][i] = linha.charAt(i);
            }
            
            l++;
        }
        
        entrada.close();
    }
    
    public boolean procuraEntradaESaida() throws Exception {
        
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
    
    public void setAtual(int l, int c) {
        this.linhaAtual = l;
        this.colunaAtual = c;
    }
    
    public void procuraAdjacentes() throws Exception {
        
    }
}
