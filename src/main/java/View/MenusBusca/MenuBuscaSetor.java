package View.MenusBusca;


import Controller.*;
import Model.PessoaTutora;
import Model.SetorResponsavel;

import java.util.List;
import java.util.Scanner;

public class MenuBuscaSetor {
    public void MenuBuscaSetor (){
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual o nome do setor? ");
        String nome = sc.nextLine();

        BuscaSetor buscaSetor = new BuscaSetor();
        List<SetorResponsavel> encontrados = buscaSetor.buscaSetor(nome);

        if (encontrados.isEmpty()) {
            System.out.println("⚠️ Nenhum setor encontrado com o nome '" + nome + "'.");
            return;
        }

        System.out.println("✅ " + encontrados.size() + " setores encontrado(s):");
        for (int i = 0; i < encontrados.size(); i++) {
            SetorResponsavel setor = encontrados.get(i);
            System.out.println("[" + i + "] " + setor.getNome() + " (" + setor.getiD() + ")");
        }

        System.out.println("\nO que você deseja fazer?");
        System.out.println("0 - Ler detalhes de um setor");
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

                SetorResponsavel setor = encontrados.get(index);
                System.out.println("✅ Detalhes do Tutor:");
                System.out.println("ID: " + setor.getiD());
                System.out.println("Nome: " + setor.getNome());
                System.out.println("Endereço: " + setor.getEndereco());
                break;

            case 1:
                AtualizarSetor atualizarSetor = new AtualizarSetor();
                atualizarSetor.AtualizaSetor(nome);
                break;
            case 2:
                if (encontrados.isEmpty()) {
                    System.out.println("❌ Nenhum setor encontrado para remover.");
                } else {
                    System.out.println("Setores encontrados:");
                    for (int i = 0; i < encontrados.size(); i++) {
                        System.out.println("[" + i + "] " + encontrados.get(i).getNome() + " (" + encontrados.get(i).getEndereco() + ")");
                    }

                    System.out.println("Digite o índice do setor que deseja deletar:");
                    int index2 = sc.nextInt();
                    sc.nextLine();

                    if (index2 >= 0 && index2 < encontrados.size()) {
                        String nomeRemover = encontrados.get(index2).getiD();
                        RemoverSetor removerSetor = new RemoverSetor();
                        removerSetor.excluirSetor(nomeRemover);
                        System.out.println("✅ Animal removido com sucesso!");
                    } else {
                        System.out.println("⚠️ Índice inválido. Nenhum animal foi removido.");
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
