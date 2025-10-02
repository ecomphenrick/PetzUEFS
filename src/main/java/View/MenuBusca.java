package View;

import java.util.Scanner;

public class MenuBusca {
    public void MenuBusca(){
        try{
            Scanner sc = new Scanner(System.in);
            int acao;
            do {
                System.out.println("BUSCA\n");
                System.out.println("0 - ANIMAL");
                System.out.println("1 - PESSOA TUTORA");
                System.out.println("2 - SETOR RESPONSÁVEL");
                System.out.println("3 - SAIR");
                acao = sc.nextInt();
                sc.nextLine();
                switch (acao) {
                    case 0:
                        //Buscar Animal;
                    case 1:
                        //Buscar Pessoa Tutora;
                    case 2:
                        //Buscar Responsável;
                    case 3:
                        System.out.println("Saindo...");
                        break;
                }
            } while (acao != 3);
        }catch (Exception e){
            System.out.println("Erro: " + e);
        }
    }
}
