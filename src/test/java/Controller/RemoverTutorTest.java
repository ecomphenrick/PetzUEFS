package Controller;

import Model.PessoaTutora;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RemoverTutorTest {

    private static final String CAMINHO_ARQUIVO = "tutor.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private RemoverTutor remover;

    private PessoaTutora tutor1;
    private PessoaTutora tutor2;

    @BeforeEach
    void setUp() throws Exception {
        // Limpar arquivo antes do teste
        new File(CAMINHO_ARQUIVO).delete();
        remover = new RemoverTutor();

        tutor1 = new PessoaTutora("P001", "Maria Silva", "Rua A, 123", "75992658825",
                "maria@gmail.com", List.of("Rex"));
        tutor2 = new PessoaTutora("P002", "João Pereira", "Rua B, 456", "75991234567",
                "joao@gmail.com", List.of("Pitoco"));

        // Salvar os tutores no arquivo
        try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO)) {
            gson.toJson(List.of(tutor1, tutor2), writer);
        }
    }

    @Test
    void testNaoRemoveUltimoTutor() throws Exception {
        // Sobrescrever com apenas 1 tutor
        try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO)) {
            gson.toJson(List.of(tutor1), writer);
        }

        remover.excluirTutor("P001");

        // Ler arquivo
        Type tipoLista = new TypeToken<List<PessoaTutora>>() {}.getType();
        List<PessoaTutora> tutores;
        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO)) {
            tutores = gson.fromJson(reader, tipoLista);
        }

        // Não deve remover o único tutor
        assertEquals(1, tutores.size(), "O único tutor não deve ser removido");
        assertEquals("P001", tutores.get(0).getiD());
    }

    @Test
    void testRemoveTutorComSucesso() throws Exception {
        remover.excluirTutor("P002");

        // Ler arquivo
        Type tipoLista = new TypeToken<List<PessoaTutora>>() {}.getType();
        List<PessoaTutora> tutores;
        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO)) {
            tutores = gson.fromJson(reader, tipoLista);
        }

        // Tutor P002 deve ser removido
        assertEquals(1, tutores.size());
        assertEquals("P001", tutores.get(0).getiD());
    }
}
