
    public class Carrossel {
        public static void main(String args[]){
            if (args.length == 0) return;
            int nroProcessadores = Integer.parseInt(args[0]);
            double[] listaProcessadores = simulacao(nroProcessadores);
            double total = 0;
            for(int i = 1; i <= nroProcessadores; i++){
                total += listaProcessadores[i];
            }
            int procMax = 1; int procMin = 1;
            for(int i = 2; i <= nroProcessadores; i++){
                if(listaProcessadores[i] > listaProcessadores[procMax]){
                    procMax = i;
                }
                if(listaProcessadores[i] < listaProcessadores[procMin]){
                    procMin = i;
                }
            }
            System.out.printf("Total de pedidos no sistema: %.6f%n", total);
            System.out.printf("Processador com mais pedidos: %d (%.6f)%n", procMax, listaProcessadores[procMax]);
            System.out.printf("Processador com menos pedidos: %d (%.6f)%n", procMin, listaProcessadores[procMin]);
        }

        
        public static double[] simulacao(int nroProcessadores){
            double tolerancia = 1e-9;
            double[] listaProcessadores = new double[nroProcessadores + 1];
            while(true){
                double novaLista[] = new double[nroProcessadores + 1];
                novaLista[1] += 1.0;
                for(int i = 1; i <= nroProcessadores; i++){
                double l = listaProcessadores[i];
                int possibilidade1 = (i % nroProcessadores) + 1; 
                int possibilidade2 = ((i + 1) % nroProcessadores) + 1;
                novaLista[possibilidade1] += l * (nroProcessadores + 1 - i) / (nroProcessadores + 1);
                novaLista[possibilidade2] += l * (i - 1) / (nroProcessadores + 1);
                }
                double resultado = 0;
                for(int i = 1; i <= nroProcessadores; i++){
                    resultado += Math.abs(novaLista[i] - listaProcessadores[i]);
                }
                listaProcessadores = novaLista;
                if(resultado < tolerancia){
                    break;
                }
            }
            return listaProcessadores;
        }
    }
