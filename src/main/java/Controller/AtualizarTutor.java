package Controller;

import Model.Animal;
import Model.PessoaTutora;
import View.MenusCadastro.CadastroTutor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AtualizarTutor {
    private static final String CAMINHO_ARQUIVO = "tutor.json";

    public void AtualizaTutor (String nomeBuscado){
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        List<PessoaTutora> listaTutores = new ArrayList<>();

        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO)) {
            Type tipoLista = new TypeToken<List<PessoaTutora>>() {}.getType();
            listaTutores = gson.fromJson(reader, tipoLista);
            if (listaTutores == null) {
                listaTutores = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        for (PessoaTutora t : listaTutores){
            if (t.getNome().equalsIgnoreCase(nomeBuscado)){
                RemoverTutor removerTutor = new RemoverTutor();
                removerTutor.excluirTutor(nomeBuscado);
                System.out.println("Cadastre-o novamente!");
                CadastroTutor cadastroTutor = new CadastroTutor();
                cadastroTutor.CadastroTutor();
                System.out.println("Setor atualizado com sucesso!");

            }else {
                System.out.println("Nenhum animal encontrado com esse nome! ");
            }
        }
    }
}
