package View.MenusPrincipais;

import View.MenusCadastro.CadastroAnimal;

import java.util.Scanner;

public class MenuCadastro {
    public void menuCadastro (){
        try {
            Scanner sc = new Scanner(System.in);
            int acao;
            do {
                System.out.println("CADASTRO\n");
                System.out.println("0 - ANIMAL");
                System.out.println("1 - PESSOA TUTORA");
                System.out.println("2 - SETOR RESPONSÁVEL");
                System.out.println("3 - SAIR");
                acao = sc.nextInt();
                sc.nextLine();
                switch (acao){
                    case 0:
                        CadastroAnimal cadastroAnimal = new CadastroAnimal();
                        cadastroAnimal.CadastroAnimal();
                        break;
                    case 1:
                        //Cadastrar Pessoa Tutora;
                    case 2:
                        //Cadastrar Responsável;
                    case 3:
                        System.out.println("Saindo...");
                        break;
                }
            }while (acao!=3);
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }
}
