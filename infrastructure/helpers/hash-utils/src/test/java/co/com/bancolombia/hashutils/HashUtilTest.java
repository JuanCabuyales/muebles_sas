package co.com.bancolombia.hashutils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HashUtilTest {

    @Test
    void generarHashMD5_deberiaRetornarHashCorrecto() {
        // Arrange
        String input = "abc123";

        // Act
        String hash = HashUtil.generarHashMD5(input);

        // Assert
        assertEquals("e99a18c428cb38d5f260853678922e03", hash); // MD5 calculado desde herramienta externa
    }

    @Test
    void generarHashMD5_entradaVacia_deberiaRetornarHashDeCadenaVacia() {
        // Act
        String hash = HashUtil.generarHashMD5("");

        // Assert
        assertEquals("d41d8cd98f00b204e9800998ecf8427e", hash); // hash de cadena vacía
    }

    @Test
    void generarHashMD5_entradaNula_lanzaNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            HashUtil.generarHashMD5(null);
        });
    }
}
