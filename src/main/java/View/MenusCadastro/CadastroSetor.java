package View.MenusCadastro;

import Model.SetorResponsavel;

import java.util.Scanner;

public class CadastroSetor {
    public void CadastroSetor (){
        Scanner sc = new Scanner(System.in);
        System.out.println("CADASTRANDO SETOR");
        System.out.println("Digite o ID: ");
        String id = sc.nextLine();
        System.out.println("Digite o nome: ");
        String nome = sc.nextLine();
        System.out.println("Digite o endereço: ");
        String endereco = sc.nextLine();

        SetorResponsavel setorResponsavel = new SetorResponsavel(id, nome, endereco);
    }
}
