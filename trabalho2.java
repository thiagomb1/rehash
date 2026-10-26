
import java.util.Random;

public class trabalho2 {

    public static void main(String[] args) {

        int[] tabela = new int[100];
        boolean[] ocupado = new boolean[100];

        int[] elementos = new int[100];
        int[] colisoesElementos = new int[100];

        Random random = new Random(1090);

        int quantidade = 0;
        int totalColisoes = 0;

       
        for (int i = 0; i < 100; i++) {

            
            if (quantidade >= 90) {
                break;
            }

            int numero = random.nextInt(100001);
            int posicao = numero % 100;
            int colisoes = 0;

            
            while (ocupado[posicao]) {
                colisoes++;
                posicao = (posicao + 1) % 100;
            }

            tabela[posicao] = numero;
            ocupado[posicao] = true;

            elementos[quantidade] = numero;
            colisoesElementos[quantidade] = colisoes;

            totalColisoes += colisoes;
            quantidade++;
        }

        
        int[] top = {-1, -1, -1};

        for (int i = 0; i < quantidade; i++) {

            for (int j = 0; j < 3; j++) {

                if (top[j] == -1 ||
                    colisoesElementos[i] > colisoesElementos[top[j]]) {

                    for (int k = 2; k > j; k--) {
                        top[k] = top[k - 1];
                    }

                    top[j] = i;
                    break;
                }
            }
        }

        System.out.println("=== REHASH ===");
        System.out.println("Elementos inseridos: " + quantidade);
        System.out.println("Ocupacao: " + quantidade + "%");
        System.out.println("Total de colisoes: " + totalColisoes);

        System.out.println("\n=== 3 ELEMENTOS COM MAIS COLISOES ===");

        for (int i = 0; i < 3; i++) {
            int indice = top[i];

            System.out.println(
                "Elemento: " + elementos[indice] +
                " | Colisoes: " + colisoesElementos[indice]
            );
        }

      
        System.out.println("\n=== POSICOES LIVRES ===");

        for (int i = 0; i < 100; i++) {
            if (!ocupado[i]) {
                System.out.println("Posicao livre: " + i);
            }
        }

        
        System.out.println("\n=== TESTE DE BUSCA ===");

        for (int i = 0; i < 3; i++) {

            int numero = elementos[top[i]];
            int posicao = numero % 100;
            boolean encontrado = false;
            int tentativas = 0;

            while (ocupado[posicao] && tentativas < 100) {

                if (tabela[posicao] == numero) {
                    encontrado = true;
                    break;
                }

                posicao = (posicao + 1) % 100;
                tentativas++;
            }

            if (encontrado) {
                System.out.println(
                    "Elemento " + numero +
                    " encontrado na posicao " + posicao
                );
            } else {
                System.out.println(
                    "Elemento " + numero + " nao encontrado"
                );
            }
        }
    }
}
