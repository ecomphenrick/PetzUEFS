package Controller;

import Model.Animal;
import Model.PessoaTutora;
import Model.SetorResponsavel;
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

class PersistenciaSetorTest {

    private static final String CAMINHO_ARQUIVO = "setor.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private PersistenciaSetor persistencia;
    private SetorResponsavel setor1;
    private SetorResponsavel setor2;
    private PessoaTutora tutor;
    private Animal animal;

    @BeforeEach
    void setUp() {
        // Apaga o arquivo antes de cada teste
        File arquivo = new File(CAMINHO_ARQUIVO);
        if (arquivo.exists()) {
            arquivo.delete();
        }

        persistencia = new PersistenciaSetor();

        // Criando tutor e animal
        tutor = new PessoaTutora(
                "P001",
                "Maria Silva",
                "Rua A, 123",
                "75992658825",
                "maria@gmail.com",
                List.of("Pitoco")
        );

        animal = new Animal(
                "A001",
                "Rex",
                "Cão",
                "Labrador",
                "2020-10-14",
                "M",
                "Disponível para adoção",
                "Biblioteca",
                List.of("Maria Silva")
        );

        // Criando setores
        setor1 = new SetorResponsavel(
                "S001",
                "Modulo 1",
                "UEFS",
                List.of(tutor),
                List.of(animal)
        );

        setor2 = new SetorResponsavel(
                "S002",
                "Modulo 2",
                "UEFS",
                List.of(tutor),
                List.of(animal)
        );
    }

    @Test
    void testSalvarSetorArquivoNovo() throws Exception {
        persistencia.salvarSetor(setor1);

        // Verifica se o arquivo foi criado
        File arquivo = new File(CAMINHO_ARQUIVO);
        assertTrue(arquivo.exists());

        // Verifica se o setor foi salvo corretamente
        Type tipoLista = new TypeToken<List<SetorResponsavel>>() {}.getType();
        List<SetorResponsavel> lista;
        try (FileReader reader = new FileReader(arquivo)) {
            lista = gson.fromJson(reader, tipoLista);
        }

        assertEquals(1, lista.size());
        assertEquals("Adoção", lista.get(0).getNome());
    }

    @Test
    void testSalvarSetorArquivoExistente() throws Exception {
        // Salva primeiro setor
        persistencia.salvarSetor(setor1);
        // Salva segundo setor
        persistencia.salvarSetor(setor2);

        // Lê o arquivo e verifica se os dois setores estão presentes
        Type tipoLista = new TypeToken<List<SetorResponsavel>>() {}.getType();
        List<SetorResponsavel> lista;
        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO)) {
            lista = gson.fromJson(reader, tipoLista);
        }

        assertEquals(2, lista.size());
        assertEquals("Modulo 1", lista.get(0).getNome());
        assertEquals("Modulo 2", lista.get(1).getNome());
    }
}
