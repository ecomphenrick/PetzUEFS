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

/**
 * Classe responsável por remover tutores do sistema.
 * <p>
 * Permite excluir um tutor do arquivo "tutor.json" desde que
 * não seja o último tutor cadastrado.
 * </p>
 */
public class RemoverTutor {

    private static final String CAMINHO_ARQUIVO = "tutor.json";

    /**
     * Exclui um tutor pelo seu ID.
     * <p>
     * O tutor só será removido se houver outros tutores cadastrados.
     * Caso contrário, uma mensagem de aviso será exibida.
     * </p>
     *
     * @param iDExcluir ID do tutor a ser removido.
     */
    public void excluirTutor(String iDExcluir) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<PessoaTutora> listaTutores = new ArrayList<>();

        // Ler lista de tutores do arquivo
        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO)) {
            Type tipoLista = new TypeToken<List<PessoaTutora>>() {}.getType();
            listaTutores = gson.fromJson(reader, tipoLista);
            if (listaTutores == null) listaTutores = new ArrayList<>();
        } catch (Exception e) {
            System.out.println("❌ Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        // Encontrar tutor a ser removido
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

        // Verificar se é o último tutor
        if (listaTutores.size() <= 1) {
            System.out.println("⚠️ Não é possível remover este tutor, pois não haverá tutores restantes.");
            System.out.println("➡️ Cadastre pelo menos mais um tutor antes de tentar remover este.");
            return;
        }

        // Remover tutor
        listaTutores.remove(tutorRemover);

        // Salvar arquivo atualizado
        try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO)) {
            gson.toJson(listaTutores, writer);
            System.out.println("🗑️ Tutor removido com sucesso!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao salvar o arquivo: " + e.getMessage());
        }
    }
}

