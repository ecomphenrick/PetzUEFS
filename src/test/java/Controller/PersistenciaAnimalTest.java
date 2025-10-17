package Controller;

import Model.Animal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import static org.junit.jupiter.api.Assertions.*;

class PersistenciaAnimalTest {

    private static final String CAMINHO_ARQUIVO = "animal.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private PersistenciaAnimal persistencia;
    private Animal animal1;
    private Animal animal2;

    @BeforeEach
    void setUp() {
        // Apaga o arquivo antes de cada teste
        File arquivo = new File(CAMINHO_ARQUIVO);
        if (arquivo.exists()) {
            arquivo.delete();
        }

        persistencia = new PersistenciaAnimal();

        animal1 = new Animal(
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

        animal2 = new Animal(
                "A002",
                "Bidu",
                "Cão",
                "Poodle",
                "2019-06-10",
                "M",
                "Em tratamento",
                "Modulo 1",
                List.of("João Pereira")
        );
    }

    @Test
    void testSalvarAnimalArquivoNovo() throws Exception {
        persistencia.salvarAnimal(animal1);

        // Verifica se o arquivo foi criado
        File arquivo = new File(CAMINHO_ARQUIVO);
        assertTrue(arquivo.exists());

        // Verifica se o animal foi salvo corretamente
        Type tipoLista = new TypeToken<List<Animal>>() {}.getType();
        List<Animal> lista;
        try (FileReader reader = new FileReader(arquivo)) {
            lista = gson.fromJson(reader, tipoLista);
        }

        assertEquals(1, lista.size());
        assertEquals("Rex", lista.get(0).getNome());
    }

    @Test
    void testSalvarAnimalArquivoExistente() throws Exception {
        // Salva primeiro animal
        persistencia.salvarAnimal(animal1);
        // Salva segundo animal
        persistencia.salvarAnimal(animal2);

        // Lê o arquivo e verifica se os dois animais estão presentes
        Type tipoLista = new TypeToken<List<Animal>>() {}.getType();
        List<Animal> lista;
        try (FileReader reader = new FileReader(CAMINHO_ARQUIVO)) {
            lista = gson.fromJson(reader, tipoLista);
        }

        assertEquals(2, lista.size());
        assertEquals("Rex", lista.get(0).getNome());
        assertEquals("Bidu", lista.get(1).getNome());
    }
}
