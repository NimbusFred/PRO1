import view.ColorMixer;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // SwingUtilities.invokeLater je používána pro bezpečné spuštění komponent Swing z vlákna rozhraní
        SwingUtilities.invokeLater(() -> new ColorMixer().show());
    }
}