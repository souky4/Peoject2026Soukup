package Hrac;

public class Character {
    private String jmeno;
    private String id;
    private String dialog;

    public Character(String jmeno, String id, String dialog) {
        this.jmeno = jmeno;
        this.id = id;
        this.dialog = dialog;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPopis() {
        return id;
    }

    public String getDialog() {
        return dialog;
    }
}
