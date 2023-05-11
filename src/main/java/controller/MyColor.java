package controller;

public class MyColor {
    // Deklarace proměnných pro RGB komponenty a název barvy
    private int r;
    private int g;
    private int b;
    private String name;

    // Konstruktor třídy MyColor, který přijímá RGB hodnoty a název barvy
    public MyColor(int r, int g, int b, String name) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.name = name;
    }

    // Gettery a settery pro RGB komponenty a název barvy
    // Gettery vrací hodnotu dané proměnné, settery nastavují hodnotu dané proměnné

    public int getR() { return r; } //getter pro komponentu R
    public void setR(int r) { this.r = r; }

    public int getG() { return g; } //getter pro komponentu G
    public void setG(int g) { this.g = g; }

    public int getB() { return b; } //getter pro komponentu B
    public void setB(int b) { this.b = b; }

    public String getName() { return name; } //getter pro komponentu Name
    public void setName(String name) { this.name = name; }

    // Metoda pro konverzi RGB hodnot na hexadecimální formát
    public String toHex() {
        return String.format("#%02x%02x%02x", r, g, b);
    }

}
