package Controller;

import Model.PessoaTutora;
import Model.SetorResponsavel;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BuscaSetor {

    private static final String CAMINHO_ARQUIVO = "setor.json";

    /**
     * Busca todos os animais com o nome informado no arquivo JSON.
     *
     * @param nome Nome do animal a buscar.
     * @return Lista de animais encontrados. Pode estar vazia se nenhum encontrado.
     */
    public List<SetorResponsavel> buscaSetor(String nome) {
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

        List<SetorResponsavel> listaSetores = new ArrayList<>();

        // Ler o arquivo JSON com try-with-resources
        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO)) {
            Type tipoLista = new TypeToken<List<SetorResponsavel>>() {}.getType();
            listaSetores = gson.fromJson(reader, tipoLista);
            if (listaSetores == null) {
                listaSetores = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao ler o arquivo: " + e.getMessage());
            return new ArrayList<>();
        }

        // Filtrar todos os animais que correspondem ao nome (ignora maiúsculas/minúsculas)
        List<SetorResponsavel> encontrados = new ArrayList<>();
        for (SetorResponsavel setor : listaSetores) {
            if (setor.getNome().equalsIgnoreCase(nome)) {
                encontrados.add(setor);
            }
        }

        return encontrados; // pode retornar lista vazia se nenhum encontrado
    }
}
