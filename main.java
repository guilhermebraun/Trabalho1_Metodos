public class main{
    public static void main(String[] args) {
        if (args.length == 0){
            return;
        } 
        int n = Integer.parseInt(args[0]);
        double[][] matriz = new double[n][n];
        double[] vetor = new double[n];
        for(int i = 0; i < n; i++){
            int processador = i + 1;
            matriz[i][i] = 1.0;
            int anterior1 = ((i - 1) + n) % n;
            int processadorAnterior1 = anterior1 + 1;
            double probabilidade1 = (double)(n + 1 - processadorAnterior1) / (n + 1);
            matriz[i][anterior1] -= probabilidade1;
            int anterior2 = ((i - 2) + n) % n;
            int processadorAnterior2 = anterior2 + 1;
            double probabilidade2 = (double)(processadorAnterior2 - 1) / (n + 1);
            matriz[i][anterior2] -= probabilidade2;
            if (processador == 1) {
                vetor[i] = 1.0;
            }
        }
        for(int coluna = 0; coluna < n; coluna++){
            int melhor = coluna;
            for(int linha = coluna + 1; linha < n; linha++){
                if(Math.abs(matriz[linha][coluna]) > Math.abs(matriz[melhor][coluna])){
                    melhor = linha;
                }
            }
            
            double[] auxLinha = matriz[coluna];
            matriz[coluna] = matriz[melhor];
            matriz[melhor] = auxLinha;
            double auxVetor = vetor[coluna];
            vetor[coluna] = vetor[melhor];  
            vetor[melhor] = auxVetor;

            for(int linha = coluna + 1; linha < n; linha++){
                double fator = matriz[linha][coluna] / matriz[coluna][coluna];
                for(int k = coluna; k < n; k++){
                    matriz[linha][k] -= fator * matriz[coluna][k];
                }
                vetor[linha] -= fator * vetor[coluna];
            }
        }

        double[] resultado = new double[n];

        for(int i = n - 1; i >= 0; i--){
            resultado[i] = vetor[i];
            for(int j = i + 1; j < n; j++){
                resultado[i] -= matriz[i][j] * resultado[j];
            }
            resultado[i] /= matriz[i][i];
        }

        double total = 0;
        int processadorMaior = 0;
        int processadorMenor = 0;

        for(int i = 0; i < n; i++){
            total += resultado[i];
            if(resultado[i] > resultado[processadorMaior]){
                processadorMaior = i;
            }
            if(resultado[i] < resultado[processadorMenor]){
                processadorMenor = i;
            }
        }

        System.out.println("Total de pedidos no sistema: " + total);
        System.out.println("Processador com mais pedidos: " + (processadorMaior + 1) + " com " + resultado[processadorMaior] + " pedidos"); 
        System.out.println("Processador com menos pedidos: " + (processadorMenor + 1) + " com " + resultado[processadorMenor] + " pedidos");
    }
}