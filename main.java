import java.util.ArrayList;
import java.util.Scanner;
public class main{
    static class Processador{
        int id;
        long qtdTarefas;
        Processador(int id) {
            this.id = id;
            this.qtdTarefas = 0;
        }
    }
    public static double estabilidade = 0.0001;
    public static ArrayList<Processador> processadores;
    public static int nroProcessadores;
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        processadores = new ArrayList<>();
        System.out.println("Digite o número de processadores: ");
        nroProcessadores = scanner.nextInt();
        for(int i = 0; i < nroProcessadores; i++){
            Processador processador = new Processador(i+1);
            processadores.add(processador);
        }
        System.out.println("Digite quantos milisegundos gostaria de simular: ");
        int tarefas = scanner.nextInt();


    }

    public static void simular(int tarefas){
        while(tarefas > 0){
            recursao(0);
            tarefas--;
        }
    }
    public static void recursao(int i){
        processadores.get(i).qtdTarefas++;
        int iVerdadeiro = i + 1;
        double probabilidadeFicar = 1.0/(nroProcessadores + 1);
        double probabilidade1 = (double)(nroProcessadores + 1 - iVerdadeiro) / (nroProcessadores + 1);
        double r = Math.random();
        if (r < probabilidadeFicar) {
            return;
        } else if (r < probabilidadeFicar + probabilidade1) {
            recursao((i + 1) % nroProcessadores);
        } else {
            recursao((i + 2) % nroProcessadores);
        }
    }
}