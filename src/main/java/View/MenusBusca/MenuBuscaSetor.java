package View.MenusBusca;

import Controller.AtualizarSetor;
import Controller.BuscaSetor;
import Controller.RemoverSetor;
import Model.SetorResponsavel;

import java.util.List;
import java.util.Scanner;

/**
 * Classe responsável pelo menu de busca de setores.
 * Permite buscar por nome, visualizar detalhes, atualizar ou deletar setores.
 */
public class MenuBuscaSetor {

    /**
     * Exibe o menu de busca de setores, permite ler detalhes, atualizar ou deletar.
     */
    public void MenuBuscaSetor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("BUSCA DE SETORES");
        System.out.print("Digite o nome do setor: ");
        String nome = sc.nextLine();

        BuscaSetor buscaSetor = new BuscaSetor();
        List<SetorResponsavel> encontrados = buscaSetor.buscaSetor(nome);

        if (encontrados.isEmpty()) {
            System.out.println("⚠️ Nenhum setor encontrado com o nome '" + nome + "'.");
            return;
        }

        System.out.println("\n✅ " + encontrados.size() + " setor(es) encontrado(s):");
        for (int i = 0; i < encontrados.size(); i++) {
            SetorResponsavel setor = encontrados.get(i);
            System.out.println("[" + i + "] " + setor.getNome() + " (ID: " + setor.getiD() + ")");
        }

        System.out.println("\nO que você deseja fazer?");
        System.out.println("0 - Ler detalhes de um setor");
        System.out.println("1 - Atualizar");
        System.out.println("2 - Deletar");
        System.out.println("3 - Sair");
        System.out.print("Escolha: ");
        int acao = sc.nextInt();
        sc.nextLine();

        switch (acao) {
            case 0:
                System.out.print("Digite o índice do setor que deseja ver: ");
                int index = sc.nextInt();
                sc.nextLine();

                if (index < 0 || index >= encontrados.size()) {
                    System.out.println("⚠️ Índice inválido.");
                    return;
                }

                SetorResponsavel setor = encontrados.get(index);
                System.out.println("\n✅ Detalhes do Setor:");
                System.out.println("ID: " + setor.getiD());
                System.out.println("Nome: " + setor.getNome());
                System.out.println("Endereço: " + setor.getEndereco());

                System.out.println("Tutores:");
                if (setor.getPessoaTutoras() == null || setor.getPessoaTutoras().isEmpty()) {
                    System.out.println("  Nenhum tutor cadastrado.");
                } else {
                    for (int i = 0; i < setor.getPessoaTutoras().size(); i++) {
                        System.out.println("  - " + setor.getPessoaTutoras().get(i).getNome() +
                                " (ID: " + setor.getPessoaTutoras().get(i).getiD() + ")");
                    }
                }

                System.out.println("Animais:");
                if (setor.getAnimais() == null || setor.getAnimais().isEmpty()) {
                    System.out.println("  Nenhum animal cadastrado.");
                } else {
                    for (int i = 0; i < setor.getAnimais().size(); i++) {
                        System.out.println("  - " + setor.getAnimais().get(i).getNome() +
                                " (" + setor.getAnimais().get(i).getEspecie() + ")");
                    }
                }
                break;

            case 1:
                AtualizarSetor atualizarSetor = new AtualizarSetor();
                atualizarSetor.atualizarSetor(nome);
                break;

            case 2:
                System.out.println("\nSetores encontrados:");
                for (int i = 0; i < encontrados.size(); i++) {
                    System.out.println("[" + i + "] " + encontrados.get(i).getNome() +
                            " (Endereço: " + encontrados.get(i).getEndereco() + ")");
                }

                System.out.print("Digite o índice do setor que deseja deletar: ");
                int index2 = sc.nextInt();
                sc.nextLine();

                if (index2 >= 0 && index2 < encontrados.size()) {
                    String idRemover = encontrados.get(index2).getiD();
                    RemoverSetor removerSetor = new RemoverSetor();
                    removerSetor.excluirSetor(idRemover);
                    System.out.println("✅ Setor removido com sucesso!");
                } else {
                    System.out.println("⚠️ Índice inválido. Nenhum setor foi removido.");
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

