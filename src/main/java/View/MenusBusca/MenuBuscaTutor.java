package View.MenusBusca;

import Controller.AtualizarTutor;
import Controller.BuscaTutor;
import Controller.RemoverTutor;
import Model.PessoaTutora;

import java.util.List;
import java.util.Scanner;

/**
 * Classe responsável pelo menu de busca de tutores.
 * Permite buscar por nome, visualizar detalhes, atualizar ou deletar tutores.
 */
public class MenuBuscaTutor {

    /**
     * Exibe o menu de busca de tutores, permite ler detalhes, atualizar ou deletar.
     */
    public void MenuBuscaTutor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("BUSCA DE TUTORES");
        System.out.print("Digite o nome do tutor: ");
        String nome = sc.nextLine();

        BuscaTutor buscaTutor = new BuscaTutor();
        List<PessoaTutora> encontrados = buscaTutor.buscaTutor(nome);

        if (encontrados.isEmpty()) {
            System.out.println("⚠️ Nenhum tutor encontrado com o nome '" + nome + "'.");
            return;
        }

        System.out.println("\n✅ " + encontrados.size() + " tutor(es) encontrado(s):");
        for (int i = 0; i < encontrados.size(); i++) {
            PessoaTutora t = encontrados.get(i);
            System.out.println("[" + i + "] " + t.getNome() + " (ID: " + t.getiD() + ")");
        }

        System.out.println("\nO que você deseja fazer?");
        System.out.println("0 - Ler detalhes de um tutor");
        System.out.println("1 - Atualizar");
        System.out.println("2 - Deletar");
        System.out.println("3 - Sair");
        System.out.print("Escolha: ");
        int acao = sc.nextInt();
        sc.nextLine();

        switch (acao) {
            case 0:
                System.out.print("Digite o índice do tutor que deseja ver: ");
                int index = sc.nextInt();
                sc.nextLine();

                if (index < 0 || index >= encontrados.size()) {
                    System.out.println("⚠️ Índice inválido.");
                    return;
                }

                PessoaTutora t = encontrados.get(index);
                System.out.println("\n✅ Detalhes do Tutor:");
                System.out.println("ID: " + t.getiD());
                System.out.println("Nome: " + t.getNome());
                System.out.println("Endereço: " + t.getEndereco());
                System.out.println("Telefone: " + t.getTelefone());
                System.out.println("E-mail: " + t.getEmail());
                System.out.println("Animais sob responsabilidade:");
                if (t.getAnimals() == null || t.getAnimals().isEmpty()) {
                    System.out.println("  Nenhum animal atribuído.");
                } else {
                    for (String animal : t.getAnimals()) {
                        System.out.println("  - " + animal);
                    }
                }
                break;

            case 1:
                AtualizarTutor atualizarTutor = new AtualizarTutor();
                atualizarTutor.atualizarTutor(nome);
                break;

            case 2:
                System.out.println("\nTutores encontrados:");
                for (int i = 0; i < encontrados.size(); i++) {
                    System.out.println("[" + i + "] " + encontrados.get(i).getNome() +
                            " (Endereço: " + encontrados.get(i).getEndereco() + ")");
                }

                System.out.print("Digite o índice do tutor que deseja deletar: ");
                int index2 = sc.nextInt();
                sc.nextLine();

                if (index2 >= 0 && index2 < encontrados.size()) {
                    String idRemover = encontrados.get(index2).getiD();
                    RemoverTutor removerTutor = new RemoverTutor();
                    removerTutor.excluirTutor(idRemover);
                    System.out.println("✅ Tutor removido com sucesso!");
                } else {
                    System.out.println("⚠️ Índice inválido. Nenhum tutor foi removido.");
                }
                break;

            case 3:
                System.out.println("Saindo...");
                break;

            default:
                System.out.println("⚠️ Opção inválida.");
                break;
        }
    }
}

