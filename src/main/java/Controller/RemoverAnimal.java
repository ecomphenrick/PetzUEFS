package Controller;

import Model.Animal;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class RemoverAnimal {
    private static final String CAMINHO_ANIMAL = "animal.json";
    private static final String CAMINHO_TUTOR = "tutor.json";
    private static final String CAMINHO_SETOR = "setor.json";

    public void excluirAnimalPorNome(String iDExcluir) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        List<Animal> listaAnimais = new ArrayList<>();

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

        boolean removido = listaAnimais.removeIf(a -> a.getiD().equalsIgnoreCase(iDExcluir));

        if (removido) {
            try (FileWriter writer = new FileWriter(CAMINHO_ANIMAL)) {
                gson.toJson(listaAnimais, writer);
            } catch (Exception e) {
                System.out.println("❌ Erro ao salvar animal.json: " + e.getMessage());
            }

            String nomeRemovido = (removedAnimal != null) ? removedAnimal.getNome() : null;

            removerAnimalDeArquivo(iDExcluir, nomeRemovido, CAMINHO_TUTOR, gson);
            removerAnimalDeArquivo(iDExcluir, nomeRemovido, CAMINHO_SETOR, gson);

            System.out.println("🗑️ Animal removido com sucesso de todos os arquivos!");
        } else {
            System.out.println("⚠️ Nenhum animal com esse ID foi encontrado para remoção.");
        }
    }

    private void removerAnimalDeArquivo(String iDExcluir, String nomeRemovido, String caminhoArquivo, Gson gson) {
        try (FileReader reader = new FileReader(caminhoArquivo)) {
            Type tipoLista = new TypeToken<List<JsonObject>>() {}.getType();
            List<JsonObject> lista = gson.fromJson(reader, tipoLista);
            if (lista == null) return;

            for (JsonObject objeto : lista) {
                if (objeto.has("animais")) {
                    JsonArray animais = objeto.getAsJsonArray("animais");
                    Iterator<JsonElement> it = animais.iterator();
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

                if (objeto.has("animals")) {
                    JsonArray animals = objeto.getAsJsonArray("animals");
                    Iterator<JsonElement> it = animals.iterator();
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

                if (objeto.has("pessoaTutoras")) {
                    JsonArray tutores = objeto.getAsJsonArray("pessoaTutoras");
                    for (JsonElement tEl : tutores) {
                        if (!tEl.isJsonObject()) continue;
                        JsonObject tutorObj = tEl.getAsJsonObject();

                        if (tutorObj.has("animals")) {
                            JsonArray animals = tutorObj.getAsJsonArray("animals");
                            Iterator<JsonElement> it = animals.iterator();
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

                        if (tutorObj.has("animais")) {
                            JsonArray animals = tutorObj.getAsJsonArray("animais");
                            Iterator<JsonElement> it = animals.iterator();
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
                }
            }

            try (FileWriter writer = new FileWriter(caminhoArquivo)) {
                gson.toJson(lista, writer);
            }

        } catch (Exception e) {
            System.out.println("❌ Erro ao atualizar arquivo (" + caminhoArquivo + "): " + e.getMessage());
        }
    }
}


