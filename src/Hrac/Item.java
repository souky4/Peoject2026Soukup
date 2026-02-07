package Hrac;

public class Item {
    private String nazev;
    private String id;
    private String popis;
    private boolean jePrenosny;

    public Item(String name, String id, boolean jePrenosny,String popis) {
        this.nazev = name;
        this.id = id;
        this.jePrenosny = jePrenosny;
        this.popis = popis;
    }

    public String getNazev() {return nazev;};
    public String getId() {return id;};
    public boolean jePrenosny() {return jePrenosny;};
    public String getpopis() {return popis;};

}
