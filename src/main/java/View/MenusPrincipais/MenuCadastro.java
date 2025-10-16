package View.MenusPrincipais;

import View.MenusCadastro.CadastroAnimal;
import View.MenusCadastro.CadastroSetor;
import View.MenusCadastro.CadastroTutor;

import java.util.Scanner;

/**
 * Classe que representa o menu de cadastro do sistema PetzUEFS.
 */
public class MenuCadastro {

    /**
     * Exibe o menu de cadastro e permite ao usuário cadastrar animais, tutores ou setores.
     */
    public void menuCadastro() {
        Scanner sc = new Scanner(System.in);
        int acao;
        try {
            do {
                System.out.println("📝 CADASTRO\n");
                System.out.println("[0] ANIMAL");
                System.out.println("[1] PESSOA TUTORA");
                System.out.println("[2] SETOR RESPONSÁVEL");
                System.out.println("[3] SAIR");
                System.out.print("Escolha uma opção: ");
                acao = sc.nextInt();
                sc.nextLine();
                switch (acao) {
                    case 0:
                        CadastroAnimal cadastroAnimal = new CadastroAnimal();
                        cadastroAnimal.CadastroAnimal();
                        break;
                    case 1:
                        CadastroTutor cadastroTutor = new CadastroTutor();
                        cadastroTutor.CadastroTutor();
                        break;
                    case 2:
                        CadastroSetor cadastroSetor = new CadastroSetor();
                        cadastroSetor.CadastroSetor();
                        break;
                    case 3:
                        System.out.println("👋 Saindo do menu de cadastro...");
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

