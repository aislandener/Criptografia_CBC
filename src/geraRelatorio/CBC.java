package geraRelatorio;

import java.util.ArrayList;
import java.util.List;

public class CBC {
    
    private int[] chave;
    private int[] vetorPosicao;

    public CBC() {
        this.chave = this.binary('t');
        String sChave = "20134657";
        vetorPosicao = new int[8];
        for(int i = 0;i < 8 ;i++){
            vetorPosicao[i] = Integer.parseInt(String.valueOf(sChave.charAt(i)));
        }
    }
    
    public CBC(char chave,String posicao) {
        this.chave = this.binary(chave);
        String sChave = posicao;
        vetorPosicao = new int[8];
        for(int i = 0;i < 8 ;i++){
            vetorPosicao[i] = Integer.parseInt(String.valueOf(sChave.charAt(i)));
        }
    }
    
    public String criptografar(String mensagem){
        List<int[]> textDescrpit = forBinary(mensagem); //Tranforma todos os caracteres em um vetor binario
        List<int[]> textCrip = new ArrayList<>();  //Inicia um vetor vazio para o processamento dos dados criptografado
        textCrip.add(chave);  //Adiciona a chave ou o vetor de inicialização
        for(int i=0;i < textDescrpit.size();i++){ //Cria uma estrutura de repetição para criptografia
            //C(i-1)+p(i)
            int[] temporario = new int[8];  //Cria um vetor para realização do metodo XOR
            int[] temp = new int[8];  //Cria um vetor para inserção apos ordenação
            for(int j = 0; j <8; j++ ){  //Cria uma estrutura de repetição para cada bit
                //Os proximos IFs é pego o texto criptografado e compara com o para criptografar
                if((textCrip.get(i)[j]==1&textDescrpit.get(i)[j]==1)|  //Realização do metodo XOR, caso seja 1 ou 0 em ambos
                        (textCrip.get(i)[j]==0&textDescrpit.get(i)[j]==0)){
                    temporario[j] = 0;
                }
                if((textCrip.get(i)[j]==0&textDescrpit.get(i)[j]==1)|  //Realização do metodo XOR, caso seja 1 e 0 em qualquer um
                        (textCrip.get(i)[j]==1&textDescrpit.get(i)[j]==0)){
                    temporario[j] = 1;
                }
                temp[vetorPosicao[j]] = temporario[j];  //Insere cada bit reordenado com o vetor de posições
            }
            textCrip.add(temp); //Adiciona o vetor criptografado e reordenado no vetor principal
        }
        textCrip.remove(0); //Remove a chave na primeira posição
        return forString(textCrip);  //Retorna um texto o vetor ja criptografado
    }
    
    public String descriptografar(String crip){
        List<int[]> textCrip = forBinary(crip); //Tranforma todos os caracteres em binario
        List<int[]> textDesc = new ArrayList<>(); //Cria um vetor para inserir todos os bits descriptografados
        textCrip.add(0,chave); //inseri o primeiro registro a chave para o primeiro
        //c(i)+c(i-1)
        for(int j = 1; j < textCrip.size();j++){  //Cria um estrututa de repetição para descriptografar o texto
            int[] temporario = new int[8]; //Cria um vetor para inserir apos realocação dos bits
            int[] temp = new int[8]; //Cria um vetor temporario para descriptografar
            for(int i = 0;i<8;i++){  //Repetição para realizar operação com cada bit
                temporario[i] = textCrip.get(j)[vetorPosicao[i]]; //Volta o bit para a posicao original
                //Os proximos IFs é pego o texto criptografado e compara com o para descriptografar
                if((temporario[i]==1&textCrip.get(j-1)[i]==1)|  //Realização do metodo XOR, caso seja 1 ou 0 em ambos
                        (temporario[i]==0&textCrip.get(j-1)[i]==0)){
                    temp[i] = 0;
                }
                if((temporario[i]==1&textCrip.get(j-1)[i]==0)|  //Realização do metodo XOR, caso seja 1 e 0 em qualquer um
                        (temporario[i]==0&textCrip.get(j-1)[i]==1)){
                    temp[i] = 1;
                }
            }
            textDesc.add(temp);  //Adiciona o vetor o texto descriptografado
        }
        return forString(textDesc);  //Retorna o texto descriptografado
    }
    
    
    private List<int[]> forBinary(String texto){
        List<int[]> arrayTexto;
        arrayTexto = new ArrayList<>();
        for(char caracter : texto.toCharArray()){
            arrayTexto.add(binary(caracter));
        }
        return arrayTexto;
    }
    
    private int[] binary(char caracter){
        int[] arrayBinario = new int[8];
            String temp = Integer.toString((int)caracter,2);
            
            if(temp.length() >= 1)
                arrayBinario[7] = Integer.parseInt(String.valueOf(temp.toCharArray()[temp.length()-1]));
            if(temp.length()>= 2)
                arrayBinario[6] = Integer.parseInt(String.valueOf(temp.toCharArray()[temp.length()-2]));
            if(temp.length()>= 3)
                arrayBinario[5] = Integer.parseInt(String.valueOf(temp.toCharArray()[temp.length()-3]));
            if(temp.length()>= 4)
                arrayBinario[4] = Integer.parseInt(String.valueOf(temp.toCharArray()[temp.length()-4]));
            if(temp.length()>= 5)
                arrayBinario[3] = Integer.parseInt(String.valueOf(temp.toCharArray()[temp.length()-5]));
            if(temp.length()>= 6)
                arrayBinario[2] = Integer.parseInt(String.valueOf(temp.toCharArray()[temp.length()-6]));
            if(temp.length()>= 7)
                arrayBinario[1] = Integer.parseInt(String.valueOf(temp.toCharArray()[temp.length()-7]));
            if(temp.length()>= 8)
                arrayBinario[0] = Integer.parseInt(String.valueOf(temp.toCharArray()[temp.length()-8]));
            
            return arrayBinario;
    }
    
    private String forString(List<int[]> binarios){
        //List<int[]> binarios = this.forBinary(text);
        String retorno = "";
        for(int[] binario: binarios){
            int k = Integer.parseInt(String.valueOf(binario[0]+""+binario[1]+""+binario[2]+""+binario[3]+""+binario[4]+""+binario[5]+""+binario[6]+""+binario[7]),2);
            String concatenar = String.valueOf((char) k);
            retorno = retorno.concat(concatenar);
        }
        return retorno;
    }
}
