package Controller;

import Model.PessoaTutora;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por realizar buscas de pessoas tutoras no sistema.
 * <p>
 * Permite buscar tutores pelo nome no arquivo "tutor.json" e retorna
 * uma lista de objetos {@link PessoaTutora} que correspondam ao critério de busca.
 * </p>
 */
public class BuscaTutor {

    /** Caminho do arquivo JSON que armazena os tutores */
    private static final String CAMINHO_ARQUIVO = "tutor.json";

    /**
     * Busca todos os tutores que tenham o nome informado.
     *
     * @param nome Nome do tutor a ser buscado (não diferencia maiúsculas de minúsculas)
     * @return Lista de tutores encontrados com o nome informado.
     *         Retorna uma lista vazia caso nenhum tutor seja encontrado ou ocorra um erro na leitura do arquivo.
     */
    public List<PessoaTutora> buscaTutor(String nome) {
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
            return new ArrayList<>();
        }

        List<PessoaTutora> encontrados = new ArrayList<>();
        for (PessoaTutora tutor : listaTutores) {
            if (tutor.getNome().equalsIgnoreCase(nome)) {
                encontrados.add(tutor);
            }
        }

        return encontrados;
    }
}

