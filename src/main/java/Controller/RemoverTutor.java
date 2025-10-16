package Controller;

import Model.PessoaTutora;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RemoverTutor {
    private static final String CAMINHO_ARQUIVO = "tutor.json";

    public void excluirTutor(String iDExcluir) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        List<PessoaTutora> listaTutores = new ArrayList<>();

        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO)) {
            Type tipoLista = new TypeToken<List<PessoaTutora>>() {}.getType();
            listaTutores = gson.fromJson(reader, tipoLista);
            if (listaTutores == null) listaTutores = new ArrayList<>();
        } catch (Exception e) {
            System.out.println("❌ Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        PessoaTutora tutorRemover = null;
        for (PessoaTutora t : listaTutores) {
            if (t.getiD().equalsIgnoreCase(iDExcluir)) {
                tutorRemover = t;
                break;
            }
        }

        if (tutorRemover == null) {
            System.out.println("⚠️ Nenhum tutor com esse ID foi encontrado para remoção.");
            return;
        }

        if (listaTutores.size() <= 1) {
            System.out.println("⚠️ Não é possível remover este tutor, pois não haverá tutores restantes.");
            System.out.println("➡️ Cadastre pelo menos mais um tutor antes de tentar remover este.");
            return;
        }

        listaTutores.remove(tutorRemover);

        try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO)) {
            gson.toJson(listaTutores, writer);
            System.out.println("🗑️ Tutor removido com sucesso!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao salvar o arquivo: " + e.getMessage());
        }
    }
}
