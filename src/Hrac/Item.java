package Hrac;

public class Item {
    private String nazev;
    private String popis;
    private boolean jePrenosny;

    public Item(String name, String description, boolean jePrenosny) {
        this.nazev = name;
        this.popis = description;
        this.jePrenosny = jePrenosny();
    }

    public String getNazev() {return nazev;};
    public String getPopis() {return popis;};
    public boolean jePrenosny() {return jePrenosny;};

}
