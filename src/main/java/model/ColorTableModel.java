package model;

import controller.MyColor;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ColorTableModel extends AbstractTableModel {
    // List pro ukládání instancí MyColor
    private List<MyColor> colors;
    // Názvy sloupců tabulky
    private final String[] columnNames = {"R", "G", "B", "Hex", "Name"};

    // Konstruktor třídy vytvoří prázdný list pro ukládání barev
    public ColorTableModel() {
        colors = new ArrayList<>();
    }

    // Metoda pro přidání nové barvy do seznamu a aktualizaci tabulky
    public void addColor(MyColor color) {
        colors.add(color);
        fireTableDataChanged();
    }

    // Metoda pro získání barvy na daném řádku
    public MyColor getColorAt(int row) {
        return colors.get(row);
    }

    // Metoda vrátí počet řádků v tabulce, což odpovídá počtu prvků v seznamu colors
    @Override
    public int getRowCount() {
        return colors.size();
    }

    // Metoda vrátí počet sloupců v tabulce, což odpovídá počtu prvků v poli columnNames
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    // Metoda vrátí hodnotu v daném řádku a sloupci
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MyColor color = colors.get(rowIndex);
        switch (columnIndex) {
            case 0: return color.getR();
            case 1: return color.getG();
            case 2: return color.getB();
            case 3: return color.toHex();
            case 4: return color.getName();
            default: return null;
        }
    }

    // Metoda vrátí název daného sloupce
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
