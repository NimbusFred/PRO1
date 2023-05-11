package view;

import controller.MyColor;
import model.ColorTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class ColorMixer{
    // Definice komponent GUI
    private JPanel panel;
    private JTextField rField;
    private JTextField gField;
    private JTextField bField;
    private JTextField nameField;
    private JButton insertButton;
    private JButton exportButton;
    private JTable colorTable;
    private ColorTableModel tableModel;
    private JPanel color;
    private JPanel colorDisplay;

    // Konstruktor třídy
    public ColorMixer() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Vytváření jednotlivých komponent a přidání akcí pro tlačítka
        panel.add(createInputPanel());
        panel.add(createColorTable());
        panel.add(createColorDisplay());

        addInsertButtonAction();
        addExportButtonAction();
    }

    //pomocné metody
    // Metoda pro vytvoření vstupního panelu
    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        rField = new JTextField();
        gField = new JTextField();
        bField = new JTextField();
        nameField = new JTextField();
        inputPanel.add(new JLabel("R:"));
        inputPanel.add(rField);
        inputPanel.add(new JLabel("G:"));
        inputPanel.add(gField);
        inputPanel.add(new JLabel("B:"));
        inputPanel.add(bField);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        insertButton = new JButton("Vložit");
        exportButton = new JButton("Export");
        inputPanel.add(insertButton);
        inputPanel.add(exportButton);
        return inputPanel;
    }

    // Metoda pro vytvoření tabulky s barvami
    private JScrollPane createColorTable() {
        tableModel = new ColorTableModel();
        colorTable = new JTable(tableModel);
        colorTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        colorTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && colorTable.getSelectedRow() != -1) {
                MyColor color = tableModel.getColorAt(colorTable.getSelectedRow());
                rField.setText(String.valueOf(color.getR()));
                gField.setText(String.valueOf(color.getG()));
                bField.setText(String.valueOf(color.getB()));
                nameField.setText(color.getName());
                colorDisplay.setBackground(new Color(color.getR(), color.getG(), color.getB()));
            }
        });
        return new JScrollPane(colorTable);
    }

    // Metoda pro vytvoření panelu pro zobrazení vybrané barvy
    private JPanel createColorDisplay() {
        colorDisplay = new JPanel();
        colorDisplay.setPreferredSize(new Dimension(0, 50));
        return colorDisplay;
    }

    // Metoda pro přidání akce pro tlačítko "Vložit"
    private void addInsertButtonAction() {
        insertButton.addActionListener(e -> {
            int r = Integer.parseInt(rField.getText());
            int g = Integer.parseInt(gField.getText());
            int b = Integer.parseInt(bField.getText());
            String name = nameField.getText();
            MyColor color = new MyColor(r, g, b, name);
            tableModel.addColor(color);
        });
    }
    // Metoda pro přidání akce pro tlačítko "Export"
    private void addExportButtonAction() {
        exportButton.addActionListener(e -> {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/data/colors.csv"));
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    MyColor color = tableModel.getColorAt(i);
                    writer.write(color.toHex() + "," + color.getR() + "," + color.getG() + "," + color.getB());
                    writer.newLine();
                }
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
    //metody
    public void show() {
        JFrame frame = new JFrame("Zkouska PRO1 - kadlecek");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
