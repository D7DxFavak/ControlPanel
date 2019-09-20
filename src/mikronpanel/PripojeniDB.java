/*
 * PripojeniDB.java
 *
 * Created on 11. duben 2007, 12:54
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package mikronpanel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

/**
 *
 * @author dave
 */
public class PripojeniDB {

    private static String DBURL = "";
    private static String DBUser = "";
    private static String DBPWD = "";
    protected static Connection con;
    protected static ResultSet rs;
    protected static int nr;
    protected static boolean qr;
    protected static PreparedStatement pstmt;
    protected static Statement stmt;

    /**
     * Creates a new instance of PripojeniDB
     */
    public PripojeniDB() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.con;

    }

    public Statement getStatement() {
        return this.stmt;
    }

    public static boolean jePlatne() {
        try {
            if (con != null) {
                return con.isValid(1);
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Navaze spojeni s databazi.
     * @return 0 kdyz pripojeni probehne v poradku, jinak vrati chybovy kod 1.
     */
    protected static int navazSpojeniDB(String URL, String user, String pwd) {
        if ((user.length() * pwd.length()) == 0) {
            return 1;
        }
        if (URL.length() == 0) {
            return 1;
        }
        try {

            con = DriverManager.getConnection(URL, user, pwd);
            //  con.setAutoCommit(true);
                       /*  System.out.println("URL je: " + URL.toString());
            System.out.println("User je: " + user.toString());
            System.out.println("PWD je: " + pwd.toString());*/
            stmt = con.createStatement();
        } catch (SQLException e) {
            //System.out.println("Mess " + e.getErrorCode());
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    /**
     * Uzavre spojeni s databazi.
     */
    protected static void uzavriSpojeniDB() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Zjisteni stavu pripojeni
    //vraci true, kdyz je pripojeno, jinak false
    protected static synchronized boolean zjistiStavPripojeni() {
        if (con != null) {
            return true;
        }//konec if
        return false;
    } //konec zjisteni stavu pripojeni

    // Vykona SQL dotaz.SELECT
    // Pri chybe vyhodi vyjimku
    protected static synchronized ResultSet dotazS(String dotazSQL) throws SQLException {
        //System.out.println("Pripojeni DB dotaz : " + dotazSQL);
        try {
            pstmt = con.prepareStatement(dotazSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = pstmt.executeQuery();
            //rs.setFetchDirection(rs.FETCH_FORWARD);
        } catch (Exception e) {
            throw new SQLException(e);
        }
        return rs;
    }

    // Vykona SQL dotaz.SELECT
    // Pri chybe vyhodi vyjimku
    // vraci updatable ResultSet
    protected static synchronized ResultSet dotazSUpdatable(String dotazSQL) throws SQLException {
        try {
            pstmt = con.prepareStatement(dotazSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = pstmt.executeQuery();
        } catch (Exception e) {
            throw new SQLException(e);
        }
        return rs;
    }

    /**
     * Vykona SQL dotaz INSERT, UPDATE nebo DELETE
     * vrati pocet nr nebo pri chybe vyhodi vyjimku SQLException
     */
    protected static synchronized int dotazIUD(String dotazSQL) throws SQLException {
        try {
            pstmt = con.prepareStatement(dotazSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            nr = pstmt.executeUpdate();
        } catch (Exception e) {
            throw new SQLException(e);
        }
        return nr;
    }

    /**
     * Vykona Obecny SQL dotaz
     * vrati true nebo pri chybe false
     */
    protected static synchronized boolean dotazObecny(String dotazSQL) throws SQLException {
        try {
            pstmt = con.prepareStatement(dotazSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            qr = pstmt.execute();
        } catch (Exception e) {
            throw new SQLException(e);
        }
        return true;
    }

    // Osetri vyjimku po SELECT nebo spusteni
    // Vrati chybovy kod, pokud je k dispozici
    // Pokud neni k dispozici, vrati 0
    protected static int vyjimkaS(Exception e) {
        e.printStackTrace();
        //doplnit chybovou hlasku
        try {
            pstmt = con.prepareStatement("ROLLBACK");
            qr = pstmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }

        String chHl = e.getMessage();
        if (chHl == null) {
            return 0;
        }

        if (TextFunkce1.testRegEx(chHl.toLowerCase(new Locale("")), "permission")) {
            return 10000;
        }

        if (TextFunkce1.testRegEx(chHl.toLowerCase(new Locale("")), "already exist")) {
            return 10010;
        }

        if (TextFunkce1.testRegEx(chHl.toLowerCase(new Locale("")), "not null")) {
            return 1000;
        }

        int i = chHl.indexOf("+");
        int j = chHl.indexOf("+", i + 1);
        if (i > 0 & j > 0 & j > (i + 1)) {
            //System.out.println("i je " + i);
            //System.out.println("j je " + j);
            try {
                int rc = Integer.parseInt(chHl.substring(i + 1, j));
                return rc;
            } catch (Exception ei) {
                ei.printStackTrace();
            }//konec catch
        }//konec if pro zjisteni retezce
        //e.printStackTrace();
        return 0;
    } //konec vyjimka pro SELECT

    // Osetri vyjimku po INSERT, UPDATE A DELETE
    // Vrati chybovy kod, pokud je k dispozici
    // Pokud neni k dispozici, vrati 0
    // Pri permission denied vrati chybovy kod 1000
    protected static int vyjimkaIUD(Exception e) {
        e.printStackTrace();
        //doplnit chybovou hlasku
        try {
            pstmt = con.prepareStatement("ROLLBACK");
            qr = pstmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }

        String chHl = e.getMessage();
        if (chHl == null) {
            return 0;
        }

        if (TextFunkce1.testRegEx(chHl.toLowerCase(new Locale("")), "permission")) {
            return 10000;
        }

        if (TextFunkce1.testRegEx(chHl.toLowerCase(new Locale("")), "already exist")) {
            return 10010;
        }

        if (TextFunkce1.testRegEx(chHl.toLowerCase(new Locale("")), "not null")) {
            return 1000;
        }

        int i = chHl.indexOf("+");
        int j = chHl.indexOf("+", i + 1);
        if (i > 0 & j > 0 & j > (i + 1)) {
            //System.out.println("i je " + i);
            //System.out.println("j je " + j);
            try {
                int rc = Integer.parseInt(chHl.substring(i + 1, j));
                return rc;
            } catch (Exception ei) {
                ei.printStackTrace();
            }//konec catch
        }//konec if pro zjisteni retezce
        //e.printStackTrace();
        return 0;
    } //konec vyjimka pro INSERT, UPDATE A DELETE
} //Konec Tridy

