package Controller;

import Model.PessoaTutora;
import Model.SetorResponsavel;
import View.MenusCadastro.CadastroSetor;
import View.MenusCadastro.CadastroTutor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AtualizarSetor {
    private static final String CAMINHO_ARQUIVO = "setor.json";

    public void AtualizaSetor (String nomeBuscado){
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        List<SetorResponsavel> listaSetores = new ArrayList<>();

        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO)) {
            Type tipoLista = new TypeToken<List<SetorResponsavel>>() {}.getType();
            listaSetores = gson.fromJson(reader, tipoLista);
            if (listaSetores == null) {
                listaSetores = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        for (SetorResponsavel s : listaSetores){
            if (s.getNome().equalsIgnoreCase(nomeBuscado)){
                RemoverSetor removerSetor = new RemoverSetor();
                removerSetor.excluirSetor(nomeBuscado);
                System.out.println("Cadastre-o novamente! ");
                CadastroSetor cadastroSetor = new CadastroSetor();
                cadastroSetor.CadastroSetor();
                System.out.println("Setor atualizado com sucesso! ");

            }else {
                System.out.println("Nenhum animal encontrado com esse nome! ");
            }
        }
    }
}
