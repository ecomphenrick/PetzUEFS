package View.MenusPrincipais;

import Controller.ImprimirRelatorio;
import java.util.Scanner;

/**
 * Classe que representa o menu de relatórios do sistema PetzUEFS.
 */
public class MenuRelatorio {

    /**
     * Exibe o menu de relatórios e permite ao usuário imprimir informações de animais, tutores ou setores.
     */
    public void ImprimirRelatorio() {
        Scanner sc = new Scanner(System.in);
        try {
            int acao;
            do {
                System.out.println("📊 Qual relatório você deseja imprimir?");
                System.out.println("[0] ANIMAL");
                System.out.println("[1] TUTORES");
                System.out.println("[2] SETORES");
                System.out.println("[3] SAIR");
                System.out.print("Escolha uma opção: ");
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
                        System.out.println("👋 Saindo do menu de relatórios...");
                        break;
                    default:
                        System.out.println("⚠️ Opção inválida! Tente novamente.\n");
                        break;
                }
                System.out.println();
            } while (acao != 3);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

