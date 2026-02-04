package Hrac;

public class Item {
    private String nazev;
    private String id;
    private boolean jePrenosny;

    public Item(String name, String id, boolean jePrenosny) {
        this.nazev = name;
        this.id = id;
        this.jePrenosny = jePrenosny();
    }

    public String getNazev() {return nazev;};
    public String getId() {return id;};
    public boolean jePrenosny() {return jePrenosny;};

}
