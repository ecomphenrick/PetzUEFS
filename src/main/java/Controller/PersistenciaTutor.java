package Controller;

import Model.PessoaTutora;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaTutor {

    private static final String CAMINHO_ARQUIVO = "tutor.json";

    public void salvarTutor(PessoaTutora novoTutor) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        File arquivo = new File(CAMINHO_ARQUIVO);
        List<PessoaTutora> listaTutores = new ArrayList<>();


        if (arquivo.exists()) {
            try (FileReader reader = new FileReader(arquivo)) {
                Type tipoLista = new TypeToken<List<PessoaTutora>>() {}.getType();
                listaTutores = gson.fromJson(reader, tipoLista);
                if (listaTutores == null) {
                    listaTutores = new ArrayList<>();
                }
            } catch (Exception e) {
                System.out.println("⚠️ Erro ao ler o arquivo existente: " + e.getMessage());
            }
        }

        listaTutores.add(novoTutor);

        try (FileWriter writer = new FileWriter(arquivo)) {
            gson.toJson(listaTutores, writer);
            System.out.println("✅ Tutor salvo com sucesso!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao salvar arquivo: " + e.getMessage());
        }

        System.out.println("📁 Caminho do arquivo: " + arquivo.getAbsolutePath());
    }
}

