package Hrac;

public class NPC extends Character{
    private String dialog;
    public NPC(String jmeno, String id, String dialog) {
        super(jmeno, id);
        this.dialog = dialog;
    }
    public String getDialog() {
        return dialog;
    }
}
