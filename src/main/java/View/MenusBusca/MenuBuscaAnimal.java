package View.MenusBusca;

import Controller.BuscaAnimal;
import Model.Animal;

import java.util.List;
import java.util.Scanner;

public class MenuBuscaAnimal {

    public void MenuBuscaAnimal() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual o nome do animal? ");
        String nome = sc.nextLine();

        BuscaAnimal buscaAnimal = new BuscaAnimal();
        List<Animal> encontrados = buscaAnimal.buscaAnimais(nome);

        if (encontrados.isEmpty()) {
            System.out.println("⚠️ Nenhum animal encontrado com o nome '" + nome + "'.");
            return;
        }

        // Mostrar todos os animais encontrados
        System.out.println("✅ " + encontrados.size() + " animal(is) encontrado(s):");
        for (int i = 0; i < encontrados.size(); i++) {
            Animal animal = encontrados.get(i);
            System.out.println("[" + i + "] " + animal.getNome() + " (" + animal.getEspecie() + ")");
        }

        System.out.println("\nO que você deseja fazer?");
        System.out.println("0 - Ler detalhes de um animal");
        System.out.println("1 - Atualizar");
        System.out.println("2 - Deletar");
        System.out.println("3 - Sair");
        int acao = sc.nextInt();
        sc.nextLine(); // consumir o enter

        switch (acao) {
            case 0:
                System.out.println("Digite o índice do animal que deseja ver:");
                int index = sc.nextInt();
                sc.nextLine();

                if (index < 0 || index >= encontrados.size()) {
                    System.out.println("⚠️ Índice inválido.");
                    return;
                }

                Animal animal = encontrados.get(index);
                System.out.println("✅ Detalhes do Animal:");
                System.out.println("ID: " + animal.getiD());
                System.out.println("Nome: " + animal.getNome());
                System.out.println("Espécie: " + animal.getEspecie());
                System.out.println("Raça: " + animal.getRaca());
                System.out.println("Data de Nascimento: " + animal.getDataNascimento());
                System.out.println("Sexo: " + animal.getSexo());
                System.out.println("Situação Atual: " + animal.getSituacaoAtual());
                break;

            case 1:
                //Implementar
                break;
            case 2:
                //Implementar
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

