/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetolabirinto;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author vinip
 */
public class ProjetoLabirinto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        File file = new File("F:\\ProjetoLabirinto\\arquivos-texto\\labirinto1.txt"); 

        BufferedReader br = new BufferedReader(new FileReader(file));
        
        String labirinto;
        while((labirinto = br.readLine()) != null)
            System.out.println(labirinto);

    }
    
}
