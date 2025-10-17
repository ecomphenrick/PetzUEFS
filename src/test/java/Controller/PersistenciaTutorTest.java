package Controller;

import Model.PessoaTutora;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersistenciaTutorTest {

    private static final String CAMINHO_ARQUIVO = "tutor.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private PersistenciaTutor persistencia;
    private PessoaTutora tutor1;
    private PessoaTutora tutor2;

    @BeforeEach
    void setUp() {
        // Apaga o arquivo antes de cada teste
        File arquivo = new File(CAMINHO_ARQUIVO);
        if (arquivo.exists()) {
            arquivo.delete();
        }

        persistencia = new PersistenciaTutor();

        tutor1 = new PessoaTutora(
                "P001",
                "Maria Silva",
                "Rua A, 123",
                "75992658825",
                "maria@gmail.com",
                List.of("Pitoco")
        );

        tutor2 = new PessoaTutora(
                "P002",
                "João Pereira",
                "Avenida Central, 456",
                "987654321",
                "joao@gmail.com",
                List.of("Rex")
        );
    }

    @Test
    void testSalvarTutorArquivoNovo() throws Exception {
        persistencia.salvarTutor(tutor1);

        // Verifica se o arquivo foi criado
        File arquivo = new File(CAMINHO_ARQUIVO);
        assertTrue(arquivo.exists());

        // Verifica se o tutor foi salvo corretamente
        Type tipoLista = new TypeToken<List<PessoaTutora>>() {}.getType();
        List<PessoaTutora> lista;
        try (FileReader reader = new FileReader(arquivo)) {
            lista = gson.fromJson(reader, tipoLista);
        }

        assertEquals(1, lista.size());
        assertEquals("Maria Silva", lista.get(0).getNome());
    }

    @Test
    void testSalvarTutorArquivoExistente() throws Exception {
        // Salva primeiro tutor
        persistencia.salvarTutor(tutor1);
        // Salva segundo tutor
        persistencia.salvarTutor(tutor2);

        // Lê o arquivo e verifica se os dois tutores estão presentes
        Type tipoLista = new TypeToken<List<PessoaTutora>>() {}.getType();
        List<PessoaTutora> lista;
        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO)) {
            lista = gson.fromJson(reader, tipoLista);
        }

        assertEquals(2, lista.size());
        assertEquals("Maria Silva", lista.get(0).getNome());
        assertEquals("João Pereira", lista.get(1).getNome());
    }
}
