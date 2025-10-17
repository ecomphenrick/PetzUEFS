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

/**
 * Classe responsável pela persistência de dados dos tutores.
 * <p>
 * Permite salvar novos tutores em um arquivo JSON, mantendo os registros existentes.
 * </p>
 */
public class PersistenciaTutor {

    private static final String CAMINHO_ARQUIVO = "tutor.json";

    /**
     * Salva um novo tutor no arquivo JSON.
     * <p>
     * Se o arquivo já existir, os tutores existentes serão mantidos e o novo será adicionado ao final.
     * Caso o arquivo não exista, será criado um novo.
     * </p>
     *
     * @param novoTutor Objeto PessoaTutora a ser salvo.
     */
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
    }
}


