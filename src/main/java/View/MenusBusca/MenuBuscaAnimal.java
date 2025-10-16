package View.MenusBusca;

import Controller.AtualizarAnimal;
import Controller.BuscaAnimal;
import Controller.RemoverAnimal;
import Model.Animal;

import java.util.List;
import java.util.Scanner;

/**
 * Classe responsável pelo menu de busca de animais.
 * Permite buscar por nome, visualizar detalhes, atualizar ou deletar animais.
 */
public class MenuBuscaAnimal {

    /**
     * Exibe o menu de busca de animais, permite ler detalhes, atualizar ou deletar.
     */
    public void MenuBuscaAnimal() {
        Scanner sc = new Scanner(System.in);
        System.out.println("BUSCA DE ANIMAIS");
        System.out.print("Digite o nome do animal: ");
        String nome = sc.nextLine();

        BuscaAnimal buscaAnimal = new BuscaAnimal();
        List<Animal> encontrados = buscaAnimal.buscaAnimais(nome);

        if (encontrados.isEmpty()) {
            System.out.println("⚠️ Nenhum animal encontrado com o nome '" + nome + "'.");
            return;
        }

        System.out.println("\n✅ " + encontrados.size() + " animal(is) encontrado(s):");
        for (int i = 0; i < encontrados.size(); i++) {
            Animal animal = encontrados.get(i);
            System.out.println("[" + i + "] " + animal.getNome() + " (" + animal.getEspecie() + ", " + animal.getRaca() + ")");
        }

        System.out.println("\nO que você deseja fazer?");
        System.out.println("0 - Ler detalhes de um animal");
        System.out.println("1 - Atualizar");
        System.out.println("2 - Deletar");
        System.out.println("3 - Sair");
        System.out.print("Escolha: ");
        int acao = sc.nextInt();
        sc.nextLine();

        switch (acao) {
            case 0:
                System.out.print("Digite o índice do animal que deseja ver: ");
                int index = sc.nextInt();
                sc.nextLine();

                if (index < 0 || index >= encontrados.size()) {
                    System.out.println("⚠️ Índice inválido.");
                    return;
                }

                Animal animal = encontrados.get(index);
                System.out.println("\n✅ Detalhes do Animal:");
                System.out.println("ID: " + animal.getiD());
                System.out.println("Nome: " + animal.getNome());
                System.out.println("Espécie: " + animal.getEspecie());
                System.out.println("Raça: " + animal.getRaca());
                System.out.println("Data de Nascimento: " + animal.getDataNascimento());
                System.out.println("Sexo: " + animal.getSexo());
                System.out.println("Situação Atual: " + animal.getSituacaoAtual());
                System.out.println("Tutores: " + String.join(", ", animal.getTutores()));
                break;

            case 1:
                AtualizarAnimal atualizarAnimal = new AtualizarAnimal();
                atualizarAnimal.atualizarAnimal(nome);
                break;

            case 2:
                System.out.println("\nAnimais encontrados:");
                for (int i = 0; i < encontrados.size(); i++) {
                    System.out.println("[" + i + "] " + encontrados.get(i).getNome() + " (" +
                            encontrados.get(i).getEspecie() + ", " + encontrados.get(i).getRaca() + ")");
                }

                System.out.print("Digite o índice do animal que deseja deletar: ");
                int index2 = sc.nextInt();
                sc.nextLine();

                if (index2 >= 0 && index2 < encontrados.size()) {
                    String nomeRemover = encontrados.get(index2).getiD();
                    RemoverAnimal removerAnimal = new RemoverAnimal();
                    removerAnimal.excluirAnimalPorNome(nomeRemover);
                    System.out.println("✅ Animal removido com sucesso!");
                } else {
                    System.out.println("⚠️ Índice inválido. Nenhum animal foi removido.");
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



