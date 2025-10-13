package View.MenusPrincipais;

import Controller.ImprimirRelatorio;

import java.util.Scanner;

public class MenuRelatorio {
    public void ImprimirRelatorio(){
        Scanner sc = new Scanner(System.in);
        try {
            int acao;
            do {
                System.out.println("Qual relatório voce deseja imprimir? ");
                System.out.println("0 - ANIMAL");
                System.out.println("1 - TUTORES");
                System.out.println("2 - SETORES");
                System.out.println("3 - SAIR");
                acao = sc.nextInt();
                sc.nextLine();
                ImprimirRelatorio imprimirRelatorio = new ImprimirRelatorio();
                switch (acao) {
                    case 0:
                        imprimirRelatorio.ImprimirAnimal();
                        break;
                    case 1:
                        imprimirRelatorio.ImprimirTutor();
                        break;
                    case 2:
                        imprimirRelatorio.ImprimirSetor();
                        break;
                    case 3:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida, tente novamente.");
                        break;
                }
            } while (acao != 3);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
