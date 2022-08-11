package server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

public class ReadSettingsTest {
    @Test
    @DisplayName("Тест метода main")
    void main() {
        //файл существует
        Assertions.assertTrue(new File("src/main/resources/configServer.properties").isFile());
        //файл может быть прочитан
        Assertions.assertTrue(new File("src/main/resources/configServer.properties").canRead());
    }
}
