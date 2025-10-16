package View.MenusCadastro;

import Controller.PersistenciaSetor;
import Model.Animal;
import Model.PessoaTutora;
import Model.SetorResponsavel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe responsável pelo cadastro de setores no sistema PetzUEFS.
 * Permite criar um setor com ID, nome, endereço e listas vazias de tutores e animais.
 */
public class CadastroSetor {
    private static final String CAMINHO_ARQUIVO = "setor.json";

    /**
     * Realiza o cadastro de um setor.
     * Valida o ID do setor e salva automaticamente no arquivo setor.json.
     */
    public void CadastroSetor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("CADASTRANDO SETOR");
        boolean validateID = false;
        String iD;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<SetorResponsavel> listaSetores = new ArrayList<>();

        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO)) {
            Type tipoLista = new TypeToken<List<SetorResponsavel>>() {}.getType();
            listaSetores = gson.fromJson(reader, tipoLista);
            if (listaSetores == null) listaSetores = new ArrayList<>();
        } catch (Exception e) {
            System.out.println("❌ Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        do {
            System.out.println("Digite o ID (Ex: S001): ");
            String regex = "^S\\d{3}$";
            iD = sc.nextLine();

            if (iD.matches(regex)) {
                validateID = true;
                for (SetorResponsavel s : listaSetores) {
                    if (s.getiD().equalsIgnoreCase(iD)) {
                        System.out.println("Esse ID já existe! Digite outro.");
                        validateID = false;
                        break;
                    }
                }
            } else {
                System.out.println("Formato inválido, o formato correto é 'S' seguido de 3 números! Ex: S001 ou S002...");
                validateID = false;
            }

        } while (!validateID);
        System.out.println("ID OK!");

        System.out.println("Digite o nome: ");
        String nome = sc.nextLine();
        System.out.println("Digite o endereço: ");
        String endereco = sc.nextLine();

        List<PessoaTutora> tutores = new ArrayList<>();
        List<Animal> animals = new ArrayList<>();

        SetorResponsavel setorResponsavel = new SetorResponsavel(iD, nome, endereco, tutores, animals);
        System.out.println("Setor cadastrado com sucesso!");
        PersistenciaSetor persistenciaSetor = new PersistenciaSetor();
        persistenciaSetor.salvarSetor(setorResponsavel);
    }
}

