package mikronpanel;

public class DvojiceCisloBool implements NticeRetezBoolean {
    protected int c;
    protected boolean b;
    
    public DvojiceCisloBool(int c, boolean b) {
        this.c = c;
        this.b = b;
    }
    
    public String toString() {
        return "" + b + "";
    }
    
    public boolean getBoolean() {
        return b;
    }
    
} // konec Dvojice
