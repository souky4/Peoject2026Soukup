package Hrac;

public class Character {
    private String jmeno;
    private String popis;
    private String dialog;

    public Character(String jmeno, String popis, String dialog) {
        this.jmeno = jmeno;
        this.popis = popis;
        this.dialog = dialog;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPopis() {
        return popis;
    }

    public String getDialog() {
        return dialog;
    }
}
