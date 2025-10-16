package View.MenusPrincipais;

import View.MenusBusca.MenuBuscaAnimal;
import View.MenusBusca.MenuBuscaSetor;
import View.MenusBusca.MenuBuscaTutor;
import java.util.Scanner;

/**
 * Classe que representa o menu de buscas do sistema PetzUEFS.
 */
public class MenuBusca {

    /**
     * Exibe o menu de busca e permite ao usuário procurar por animais, tutores ou setores.
     */
    public void MenuBusca() {
        Scanner sc = new Scanner(System.in);
        try {
            int acao;
            do {
                System.out.println("🔍 BUSCA\n");
                System.out.println("[0] ANIMAL");
                System.out.println("[1] PESSOA TUTORA");
                System.out.println("[2] SETOR RESPONSÁVEL");
                System.out.println("[3] SAIR");
                System.out.print("Escolha uma opção: ");
                acao = sc.nextInt();
                sc.nextLine();

                switch (acao) {
                    case 0:
                        MenuBuscaAnimal menuBuscaAnimal = new MenuBuscaAnimal();
                        menuBuscaAnimal.MenuBuscaAnimal();
                        break;
                    case 1:
                        MenuBuscaTutor menuBuscaTutor = new MenuBuscaTutor();
                        menuBuscaTutor.MenuBuscaTutor();
                        break;
                    case 2:
                        MenuBuscaSetor menuBuscaSetor = new MenuBuscaSetor();
                        menuBuscaSetor.MenuBuscaSetor();
                        break;
                    case 3:
                        System.out.println("👋 Saindo do menu de buscas...");
                        break;
                    default:
                        System.out.println("⚠️ Opção inválida! Tente novamente.\n");
                        break;
                }
                System.out.println();
            } while (acao != 3);
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }
}

