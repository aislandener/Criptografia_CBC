package testereport;

import geraRelatorio.CBC;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TesteReport { 
    
    private static CBC cbc;
    
    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            
            System.out.println("Digite 1 para o método padrão\nOu 2 para informar a chave e o vetor de posições");
            String retorno = in.readLine();
            if(retorno.equals("1"))
                cbc = new CBC();
            else
                cbc = new CBC('p',"53120764");
            
            System.out.println("Digite o texto para criptografar");
            String str = in.readLine();

            String criptografia = cbc.criptografar(str);
            System.out.println("Texto Criptografado: "+criptografia);
            System.out.println("Texto Descriptografado: "+cbc.descriptografar(criptografia));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
