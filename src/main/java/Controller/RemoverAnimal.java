package Controller;

import Model.Animal;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe responsável por remover animais do sistema.
 * <p>
 * Permite excluir um animal do arquivo "animal.json" e
 * atualizar automaticamente os registros relacionados nos arquivos
 * "tutor.json" e "setor.json".
 * </p>
 */
public class RemoverAnimal {

    private static final String CAMINHO_ANIMAL = "animal.json";
    private static final String CAMINHO_TUTOR = "tutor.json";
    private static final String CAMINHO_SETOR = "setor.json";

    /**
     * Exclui um animal pelo seu ID.
     * <p>
     * Remove o animal do arquivo "animal.json" e atualiza
     * os registros relacionados nos arquivos de tutores e setores.
     * </p>
     *
     * @param iDExcluir ID do animal a ser removido.
     */
    public void excluirAnimalPorNome(String iDExcluir) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        List<Animal> listaAnimais = new ArrayList<>();

        // Ler lista de animais do arquivo
        try (FileReader reader = new FileReader(CAMINHO_ANIMAL)) {
            Type tipoLista = new TypeToken<List<Animal>>() {}.getType();
            listaAnimais = gson.fromJson(reader, tipoLista);
            if (listaAnimais == null) listaAnimais = new ArrayList<>();
        } catch (Exception e) {
            System.out.println("❌ Erro ao ler o arquivo animal.json: " + e.getMessage());
            return;
        }

        Animal removedAnimal = null;
        for (Animal a : listaAnimais) {
            if (a.getiD().equalsIgnoreCase(iDExcluir)) {
                removedAnimal = a;
                break;
            }
        }

        // Remover animal da lista
        boolean removido = listaAnimais.removeIf(a -> a.getiD().equalsIgnoreCase(iDExcluir));

        if (removido) {
            try (FileWriter writer = new FileWriter(CAMINHO_ANIMAL)) {
                gson.toJson(listaAnimais, writer);
            } catch (Exception e) {
                System.out.println("❌ Erro ao salvar animal.json: " + e.getMessage());
            }

            String nomeRemovido = (removedAnimal != null) ? removedAnimal.getNome() : null;

            // Atualizar arquivos relacionados
            removerAnimalDeArquivo(iDExcluir, nomeRemovido, CAMINHO_TUTOR, gson);
            removerAnimalDeArquivo(iDExcluir, nomeRemovido, CAMINHO_SETOR, gson);

            System.out.println("🗑️ Animal removido com sucesso de todos os arquivos!");
        } else {
            System.out.println("⚠️ Nenhum animal com esse ID foi encontrado para remoção.");
        }
    }

    /**
     * Remove referências ao animal de outros arquivos JSON (tutores e setores).
     *
     * @param iDExcluir   ID do animal a ser removido.
     * @param nomeRemovido Nome do animal removido (para listas de nomes).
     * @param caminhoArquivo Caminho do arquivo a ser atualizado.
     * @param gson        Instância do Gson para leitura e escrita JSON.
     */
    private void removerAnimalDeArquivo(String iDExcluir, String nomeRemovido, String caminhoArquivo, Gson gson) {
        try (FileReader reader = new FileReader(caminhoArquivo)) {
            Type tipoLista = new TypeToken<List<JsonObject>>() {}.getType();
            List<JsonObject> lista = gson.fromJson(reader, tipoLista);
            if (lista == null) return;

            for (JsonObject objeto : lista) {
                // Remover de arrays "animais" ou "animals"
                removerAnimalDeJsonArray(objeto, "animais", iDExcluir, nomeRemovido);
                removerAnimalDeJsonArray(objeto, "animals", iDExcluir, nomeRemovido);

                // Remover de tutores
                if (objeto.has("pessoaTutoras")) {
                    JsonArray tutores = objeto.getAsJsonArray("pessoaTutoras");
                    for (JsonElement tEl : tutores) {
                        if (!tEl.isJsonObject()) continue;
                        JsonObject tutorObj = tEl.getAsJsonObject();
                        removerAnimalDeJsonArray(tutorObj, "animals", iDExcluir, nomeRemovido);
                        removerAnimalDeJsonArray(tutorObj, "animais", iDExcluir, nomeRemovido);
                    }
                }
            }

            try (FileWriter writer = new FileWriter(caminhoArquivo)) {
                gson.toJson(lista, writer);
            }

        } catch (Exception e) {
            System.out.println("❌ Erro ao atualizar arquivo (" + caminhoArquivo + "): " + e.getMessage());
        }
    }

    /**
     * Remove o animal de um JsonArray de um JsonObject.
     *
     * @param objeto       JsonObject que contém o array.
     * @param chave        Chave do JsonArray ("animais" ou "animals").
     * @param iDExcluir    ID do animal a ser removido.
     * @param nomeRemovido Nome do animal a ser removido.
     */
    private void removerAnimalDeJsonArray(JsonObject objeto, String chave, String iDExcluir, String nomeRemovido) {
        if (!objeto.has(chave)) return;

        JsonArray array = objeto.getAsJsonArray(chave);
        Iterator<JsonElement> it = array.iterator();

        while (it.hasNext()) {
            JsonElement el = it.next();
            if (el.isJsonObject()) {
                JsonObject animal = el.getAsJsonObject();
                if (animal.has("iD") && animal.get("iD").getAsString().equalsIgnoreCase(iDExcluir)) {
                    it.remove();
                }
            } else if (el.isJsonPrimitive() && nomeRemovido != null) {
                if (el.getAsString().equalsIgnoreCase(nomeRemovido)) {
                    it.remove();
                }
            }
        }
    }
}



