package Hrac;
/**
 * Třída reprezentuje předmět ve hře.
 * Předmět může být přenosný nebo nepřenosný.
 *
 * @author Ondrej Soukup
 */

public class Item {
    private String nazev;
    private String id;
    private String popis;
    private boolean jePrenosny;

    /**
     * Vytvori novy predmet
     *
     * @param name název předmětu
     * @param id identifikátor
     * @param jePrenosny zda je možné předmět sebrat
     * @param popis popis předmětu
     */

    public Item(String name, String id, boolean jePrenosny,String popis) {
        this.nazev = name;
        this.id = id;
        this.jePrenosny = jePrenosny;
        this.popis = popis;
    }

    public String getNazev() {return nazev;};
    public String getId() {return id;};

    /**
     * zjisti zda je predmet prenosny
     *
     * @return true pokud lze předmět sebrat
     */
    public boolean jePrenosny() {return jePrenosny;};


}
