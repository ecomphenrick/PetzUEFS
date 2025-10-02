package View.MenusCadastro;

import Model.PessoaTutora;

import java.util.Scanner;

public class CadastroTutor {
    public void CadastroTutor (){
        Scanner sc = new Scanner(System.in);
        System.out.println("CADASTRANDO PESSOA TUTORA\n");
        System.out.println("Digite o ID: ");
        String id = sc.nextLine();
        System.out.println("Digite o Nome: ");
        String nome = sc.nextLine();
        System.out.println("Digite o endereço: ");
        String endereco = sc.nextLine();
        System.out.println("Digite o telefone: ");
        String telefone = sc.nextLine();
        System.out.println("Digite o e-mail: ");
        String email = sc.nextLine();

        PessoaTutora pessoaTutora = new PessoaTutora(id, nome, endereco, telefone, email);
    }
}
