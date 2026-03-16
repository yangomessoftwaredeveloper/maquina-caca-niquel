
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int banca = 100;
        int aposta;
        int lucroAposta;
        String[] fileira;
        String jogarNovamente;

        System.out.println("**********************************");
        System.out.println("MÁQUINA DE SLOT JAVA - VEM VICIADO");
        System.out.println("Simbolos do Slot: 🍒 🍉 🍋 🧊 🍇");
        System.out.println("**********************************");

        while (banca > 0) {

            System.out.println("Saldo atual na banca é: $" + banca);
            System.out.println();
            System.out.print("Digite o quanto você deseja apostar: ");
            aposta = scanner.nextInt();
            scanner.nextLine();

            if (aposta > banca) {
                System.out.println("Saldo insuficiente.");
                continue;
            } else if (aposta <= 0) {
                System.out.println("A aposta precisa ser no mínimo de $1.");
                continue;
            } else {
                banca = banca - aposta;
            }
            System.out.println("Rodando maquininha...");
            fileira = rodarMaquininha();
            printarFileira(fileira);
            lucroAposta = pegarLucroAposta(fileira, aposta);

            if(lucroAposta > 0){
                System.out.println("Você ganhou $" + lucroAposta);
                banca += lucroAposta;
            }
            else{
                System.out.println("Você perdeu.");
            }

            System.out.print("Quer rodar a máquina novamente? (sim/não): ");
            jogarNovamente = scanner.nextLine().toLowerCase();

            if(!jogarNovamente.equals("sim")){
                break;
            }
        }

        do{
            System.out.println();
            System.out.println("Acabou pro betinha, quebrou a banca!");
            break;
        }while (banca == 0);

        System.out.println("Seu saldo final foi de: $" + banca);
        scanner.close();
    }
    static void printarFileira(String[] fileira) {
        System.out.println("***************");
        System.out.println(" " + String.join(" | ", fileira));
        System.out.println("***************");
    }
    static String[] rodarMaquininha(){

        String[] simbolos = {"🍒", "🍉", "🍋", "🧊", "🍇"};
        String[] fileira = new String[3];
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            fileira[i] = simbolos[random.nextInt(simbolos.length)];
        }
        return fileira;
    }
    static int pegarLucroAposta(String[] fileira, int aposta) {
        if(fileira[0].equals(fileira[1]) && fileira[1].equals(fileira[2])){
            return switch (fileira[1]){
                case "🍒" -> aposta * 3;
                case "🍉" -> aposta * 4;
                case "🍋" -> aposta * 5;
                case "🧊" -> aposta * 10;
                case "🍇" -> aposta * 20;
                default -> 0;
            };
        }
        else if(fileira[0].equals(fileira[1])){
            return switch (fileira[0]){
                case "🍒" -> aposta * 2;
                case "🍉" -> aposta * 3;
                case "🍋" -> aposta * 4;
                case "🧊" -> aposta * 5;
                case "🍇" -> aposta * 10;
                default -> 0;
            };
        }
        else if(fileira[1].equals(fileira[2])){
            return switch (fileira[0]){
                case "🍒" -> aposta * 2;
                case "🍉" -> aposta * 3;
                case "🍋" -> aposta * 4;
                case "🧊" -> aposta * 5;
                case "🍇" -> aposta * 10;
                default -> 0;
            };
        }
        return 0;
    }
}
