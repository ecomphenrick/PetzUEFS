package View.MenusBusca;


import Controller.AtualizarTutor;
import Controller.BuscaTutor;
import Controller.RemoverSetor;
import Controller.RemoverTutor;
import Model.PessoaTutora;

import java.util.List;
import java.util.Scanner;

public class MenuBuscaTutor {
    public void MenuBuscaTutor (){
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual o nome do tutor? ");
        String nome = sc.nextLine();

        BuscaTutor buscaTutor = new BuscaTutor();
        List<PessoaTutora> encontrados = buscaTutor.buscaTutor(nome);

        if (encontrados.isEmpty()) {
            System.out.println("⚠️ Nenhum Tutor encontrado com o nome '" + nome + "'.");
            return;
        }

        // Mostrar todos os animais encontrados
        System.out.println("✅ " + encontrados.size() + " tutores encontrado(s):");
        for (int i = 0; i < encontrados.size(); i++) {
            PessoaTutora pessoaTutora = encontrados.get(i);
            System.out.println("[" + i + "] " + pessoaTutora.getNome() + " (" + pessoaTutora.getiD() + ")");
        }

        System.out.println("\nO que você deseja fazer?");
        System.out.println("0 - Ler detalhes de um tutor");
        System.out.println("1 - Atualizar");
        System.out.println("2 - Deletar");
        System.out.println("3 - Sair");
        int acao = sc.nextInt();
        sc.nextLine(); // consumir o enter

        switch (acao) {
            case 0:
                System.out.println("Digite o índice do tutor que deseja ver:");
                int index = sc.nextInt();
                sc.nextLine();

                if (index < 0 || index >= encontrados.size()) {
                    System.out.println("⚠️ Índice inválido.");
                    return;
                }

                PessoaTutora pessoaTutora = encontrados.get(index);
                System.out.println("✅ Detalhes do Tutor:");
                System.out.println("ID: " + pessoaTutora.getiD());
                System.out.println("Nome: " + pessoaTutora.getNome());
                System.out.println("Endereço: " + pessoaTutora.getEndereco());
                System.out.println("Telefone: " + pessoaTutora.getTelefone());
                System.out.println("E-mail: " + pessoaTutora.getEmail());
                break;

            case 1:
                AtualizarTutor atualizarTutor = new AtualizarTutor();
                atualizarTutor.atualizarTutor(nome);
                break;
            case 2:
                if (encontrados.isEmpty()) {
                    System.out.println("❌ Nenhum tutor encontrado para remover.");
                } else {
                    System.out.println("Tutores encontrados:");
                    for (int i = 0; i < encontrados.size(); i++) {
                        System.out.println("[" + i + "] " + encontrados.get(i).getNome() + " (" + encontrados.get(i).getEndereco() + ")");
                    }

                    System.out.println("Digite o índice do tutor que deseja deletar:");
                    int index2 = sc.nextInt();
                    sc.nextLine();

                    if (index2 >= 0 && index2 < encontrados.size()) {
                        String nomeRemover = encontrados.get(index2).getiD();
                        RemoverTutor removerTutor = new RemoverTutor();
                        removerTutor.excluirTutor(nomeRemover);
                        System.out.println("✅ Tutor removido com sucesso!");
                    } else {
                        System.out.println("⚠️ Índice inválido. Nenhum tutor foi removido.");
                    }
                }
                break;
            case 3:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("⚠️ Opção inválida.");
                break;
        }
}}
