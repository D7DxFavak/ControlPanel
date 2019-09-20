package mikronpanel;

public class TrojiceRetezCisloBool implements NticeRetezBoolean {
    protected String r;
    protected int c;
    protected boolean b;
    
    
    public TrojiceRetezCisloBool(String r, int c, boolean b) {
        this.r = r;
        this.c = c;
        this.b = b;
    }
    
    public String toString() {
        return r;
    }
    
    public boolean getBoolean() {
        return b;
    }
    
} // konec Trojice
