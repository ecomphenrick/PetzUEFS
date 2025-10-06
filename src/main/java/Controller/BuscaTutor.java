package Controller;

import Model.PessoaTutora;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BuscaTutor {

    private static final String CAMINHO_ARQUIVO = "tutor.json";

    /**
     * Busca todos os animais com o nome informado no arquivo JSON.
     *
     * @param nome Nome do animal a buscar.
     * @return Lista de animais encontrados. Pode estar vazia se nenhum encontrado.
     */
    public List<PessoaTutora> buscaTutor(String nome) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
                    @Override
                    public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
                        return new JsonPrimitive(src.toString()); // "yyyy-MM-dd"
                    }
                })
                .registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
                    @Override
                    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
                        return LocalDate.parse(json.getAsString());
                    }
                })
                .create();

        List<PessoaTutora> listaTutores = new ArrayList<>();

        // Ler o arquivo JSON com try-with-resources
        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO)) {
            Type tipoLista = new TypeToken<List<PessoaTutora>>() {}.getType();
            listaTutores = gson.fromJson(reader, tipoLista);
            if (listaTutores == null) {
                listaTutores = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao ler o arquivo: " + e.getMessage());
            return new ArrayList<>();
        }

        // Filtrar todos os animais que correspondem ao nome (ignora maiúsculas/minúsculas)
        List<PessoaTutora> encontrados = new ArrayList<>();
        for (PessoaTutora tutor : listaTutores) {
            if (tutor.getNome().equalsIgnoreCase(nome)) {
                encontrados.add(tutor);
            }
        }

        return encontrados; // pode retornar lista vazia se nenhum encontrado
    }
}
