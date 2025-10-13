package View.MenusPrincipais;

import java.util.Scanner;

public class MenuPrincipal {
    public void MenuPrincipal (){
        Scanner sc = new Scanner(System.in);
        int acao;
        try {
            System.out.println("Bem vindos a PetzUEFS!\n");
            do {
                System.out.println("O que você deseja fazer? ");
                System.out.println("0 - CADASTRO");
                System.out.println("1 - BUSCA");
                System.out.println("2 - RELATÓRIO");
                System.out.println("3 - SAIR");
                acao = sc.nextInt();
                sc.nextLine();
                switch (acao){
                    case 0:
                        MenuCadastro menuCadastro = new MenuCadastro();
                        menuCadastro.menuCadastro();
                        break;
                    case 1:
                        MenuBusca menuBusca = new MenuBusca();
                        menuBusca.MenuBusca();
                        break;
                    case 2:
                        MenuRelatorio menuRelatorio = new MenuRelatorio();
                        menuRelatorio.ImprimirRelatorio();
                        break;
                    case 3:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida! ");
                        break;
                }
            }while (acao !=3);
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }
}
