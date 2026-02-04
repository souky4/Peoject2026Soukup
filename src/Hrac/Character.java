package Hrac;

public abstract  class Character {
    private String jmeno;
    private String id;


    public Character(String jmeno, String id) {
        this.jmeno = jmeno;
        this.id = id;

    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPopis() {
        return id;
    }


}
