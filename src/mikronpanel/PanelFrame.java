/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * PanelFrame.java
 *
 * Created on 15.2.2010, 17:04:03
 */
package mikronpanel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author 7Data Gotzy
 */
public class PanelFrame extends javax.swing.JFrame {

    protected Vector vrPopisy1;
    protected Vector vsPopisy1;
    protected Vector vrAktivniPruvodka1;
    protected Vector vsAktivniPruvodka1;
    protected Vector pruvodky;
    protected Vector v;
    private boolean[][] indexyStroje;
    private boolean[][] indexyStrojeDruhy;
    private boolean[][] indexyTransakce;
    private Vector strojeTransakceNactene;
    private Vector strojeTransakce;
    private Vector sloupceNactene;
    private Vector sloupce;
    protected Vector vsStrojeDruhy1;
    protected Vector vrStrojeDruhy1;
    protected Vector vsStroje1;
    protected Vector vrStroje1;
    protected Vector vsZamStrojeLog1;
    protected Vector vrZamStrojeLog1;
    protected Vector vsOperaceZaznamy1;
    protected Vector vrOperaceZaznamy1;
    protected Vector vsOperaceZaznamyNactene1;
    protected Vector vrOperaceZaznamyNactene1;
    protected Vector vrAktivniZaznamy1;
    protected TridaZaznam1 aktivniZaznamy;
    protected long pruvodka_id = -1;
    private int zamestnanec_id = -1;
    protected tabulkaModelStrojeDruhy1 tabulkaModelStrojeDruhy1;
    protected tabulkaModelStroje1 tabulkaModelStroje1;
    protected tabulkaModelTransakce1 tabulkaModelTransakce1;
    protected tabulkaModelVykresyNazvy1 tabulkaModelVykresyNazvy1;
    protected TableModel tableModelTransakce1;
    protected TableModel tableModelStrojeDruhy1;
    protected TableModel tableModelStroje1;
    protected TableModel tableModelVykresyNazvy1;
    protected TridaPruvodka1 pruvodka;
    protected ListSelectionModel lsmTransakce1, lsmStrojeDruhy1, lsmStroje1;
    protected TableModelListener tmlUdalosti;
    protected ListSelectionListener lslUdalosti;
    protected MouseListener mlUdalosti;
    protected ActionListener alUdalosti;
    protected FocusListener flUdalosti;
    protected DocumentListener dlUdalosti;
    protected int narezanoKS;
    protected java.text.DateFormat df = java.text.DateFormat.getDateInstance();

    /**
     * Creates new form PanelFrame
     */
    public PanelFrame() {
        initComponents();

        vrPopisy1 = new Vector();
        vsPopisy1 = new Vector();

        vrStrojeDruhy1 = new Vector();
        vsStrojeDruhy1 = new Vector();

        vrStroje1 = new Vector();
        vsStroje1 = new Vector();

        vsZamStrojeLog1 = new Vector();
        vrZamStrojeLog1 = new Vector();

        vsAktivniPruvodka1 = new Vector();
        vrAktivniPruvodka1 = new Vector();

        vsOperaceZaznamy1 = new Vector();
        vrOperaceZaznamy1 = new Vector();

        vsOperaceZaznamyNactene1 = new Vector();
        vrOperaceZaznamyNactene1 = new Vector();

        vrAktivniZaznamy1 = new Vector();
        strojeTransakceNactene = new Vector();

        strojeTransakce = new Vector();
        sloupceNactene = new Vector();

        sloupce = new Vector();
        pruvodky = new Vector();

        JScrollBar bar1 = new JScrollBar();
        bar1.setPreferredSize(new Dimension(40,
                bar1.getPreferredSize().height));

        jScrollPane1.setVerticalScrollBar(bar1);

        JScrollBar bar3 = new JScrollBar();
        bar3.setPreferredSize(new Dimension(40,
                bar3.getPreferredSize().height));

        jScrollPane3.setVerticalScrollBar(bar3);

        // this.setSize(1370, 760);
        this.setSize(getBounds().getSize());
        addWindowListener(new WindowAdapter() {

            public void windowOpened(WindowEvent e) {
                setExtendedState(MAXIMIZED_BOTH);
            }
        });

        this.setVisible(true);
        // nastavTridyObsluhyUdalosti();
        nastavParametryTabulek();
        nastavTridyObsluhyUdalosti();
        nastavPosluchaceUdalostiTabulky();
        indexyStroje = new boolean[1][1];
        indexyStroje[0][0] = false;

        indexyStrojeDruhy = new boolean[1][1];
        indexyStrojeDruhy[0][0] = false;

        indexyTransakce = new boolean[1][1];
        indexyTransakce[0][0] = false;

        //System.out.println("nastavTabulkuStroje1()");
        nastavTabulkuStroje1();
        //System.out.println("nastavTabulkuStrojeDruhy1()");
        nastavTabulkuStrojeDruhy1();
        vsechnyStrojeButton1.setEnabled(false);
        vsechnyStrojeButton1.setVisible(false);
        //System.out.println("konec nastavTabulku");

        //ZamestnanecTextField1.setText("50000037");
        //PruvodkaTextField1.setText("10012299");
        narezanoKS = -1;

    }

    protected void nastavParametryTabulek() {

        tabulkaModelStrojeDruhy1 = new tabulkaModelStrojeDruhy1();

        jTablePruvodkyStrojeDruhy1.setModel(tabulkaModelStrojeDruhy1);
        jTablePruvodkyStrojeDruhy1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTablePruvodkyStrojeDruhy1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        tabulkaModelStroje1 = new tabulkaModelStroje1();

        jTablePruvodkyStroje1.setModel(tabulkaModelStroje1);
        jTablePruvodkyStroje1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTablePruvodkyStroje1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        PruvodkaTextField1.getDocument().addDocumentListener(dlUdalosti);

        lsmStrojeDruhy1 = jTablePruvodkyStrojeDruhy1.getSelectionModel();
        lsmStroje1 = jTablePruvodkyStroje1.getSelectionModel();
        lsmTransakce1 = jTablePruvodkyTransakce1.getSelectionModel();

        tableModelTransakce1 = jTablePruvodkyTransakce1.getModel();
        tableModelStrojeDruhy1 = jTablePruvodkyStrojeDruhy1.getModel();
        tableModelStroje1 = jTablePruvodkyStroje1.getModel();
    }

    protected void nastavTridyObsluhyUdalosti() {
        lslUdalosti = new LSLUdalosti();
    }

    protected void nastavPosluchaceUdalostiTabulky() {
        lsmStrojeDruhy1.addListSelectionListener(lslUdalosti);
        lsmTransakce1.addListSelectionListener(lslUdalosti);
        lsmStroje1.addListSelectionListener(lslUdalosti);
    }

    protected void nastavTabulkuStrojeDruhy1() {

        TableColumn column = null;
        column = jTablePruvodkyStrojeDruhy1.getColumnModel().getColumn(0);
        column.setPreferredWidth(jScrollPane1.getWidth() - 3);
        column.setCellRenderer(new TableVyberRenderer1(indexyStrojeDruhy));
        column.setCellEditor(new TableVyberEditor1(new JCheckBox()));

        JTableHeader strojeDruhy = jTablePruvodkyStrojeDruhy1.getTableHeader();
        strojeDruhy.setFont(new java.awt.Font("DejaVu Sans", 0, 20));
        jTablePruvodkyStrojeDruhy1.setTableHeader(strojeDruhy);

    }// konec nastavTabulkuBT1

    protected void nastavTabulkuTransakce1(int sloupcePocet, int druh) {
        TableColumn column = null;
        //int sirka = 300/sloupce;
        // System.out.println(" transakce " + 400 / sloupce);
        tabulkaModelVykresyNazvy1 = new tabulkaModelVykresyNazvy1();
        jTableVykresyNazvy1.setModel(tabulkaModelVykresyNazvy1);
        tableModelVykresyNazvy1 = jTableVykresyNazvy1.getModel();

        for (int i = 0; i < sloupcePocet; i++) {

            column = jTablePruvodkyTransakce1.getColumnModel().getColumn(i);
            // System.out.println(sloupcePocet + " transakce x " + jTablePruvodkyTransakce1.getWidth());
            column.setPreferredWidth(735 / sloupcePocet);
            column.setCellRenderer(new TableVyberRenderer1(indexyTransakce));

            column.setCellEditor(new TableVyberEditor1(new JCheckBox()));

            try {
                TridaZaznam1 aktivZazn = (TridaZaznam1) vrAktivniZaznamy1.get(i);
                column.setHeaderRenderer(new MyTableHeaderRenderer(tabulkaModelTransakce1.columnNames[i], aktivZazn.getCislo_vykresu()));
            } catch (Exception e) {
                column.setHeaderRenderer(new MyTableHeaderRenderer(tabulkaModelTransakce1.columnNames[i], CisloVykresuLabel1.getText()));
            }

            column = jTableVykresyNazvy1.getColumnModel().getColumn(i);
            column.setHeaderRenderer(new MyTableNoHeaderRenderer());
        }

        JTableHeader transakce = jTablePruvodkyTransakce1.getTableHeader();
        transakce.setFont(new java.awt.Font("DejaVu Sans", 0, 20));
        jTablePruvodkyTransakce1.setTableHeader(transakce);

        //column.setCellRenderer(new TableVyberRenderer1());
        //column.setCellEditor(new TableVyberEditor1(new JCheckBox()));
    }// konec nastavTabulkuBT1

    protected void nastavTabulkuStroje1() {
        TableColumn column = null;
        column = jTablePruvodkyStroje1.getColumnModel().getColumn(0);
        column.setPreferredWidth(jScrollPane3.getWidth() - 3);
        column.setCellRenderer(new TableVyberRenderer1(indexyStroje));
        column.setCellEditor(new TableVyberEditor1(new JCheckBox()));

        JTableHeader stroje = jTablePruvodkyStroje1.getTableHeader();
        stroje.setFont(new java.awt.Font("DejaVu Sans", 0, 20));
        jTablePruvodkyStroje1.setTableHeader(stroje);

    }// konec nastavTabulkuBT1

    protected void nastavVyberTabulkyStrojeDruhy1() {
        vrStroje1.removeAllElements();
        jTablePruvodkyStrojeDruhy1.setDefaultRenderer(Object.class, new TableVyberRenderer1(indexyStrojeDruhy));
        int pocetZaznamu = 0;
        if (jTablePruvodkyStrojeDruhy1.getSelectedRow() >= 0) {
            int[] radky = jTablePruvodkyStrojeDruhy1.getSelectedRows();
            for (int row = 0; row < jTablePruvodkyStrojeDruhy1.getSelectedRowCount(); row++) {
                if (indexyStrojeDruhy[radky[row]][0] == false) {
                    indexyStrojeDruhy[radky[row]][0] = true;
                } else {
                    indexyStrojeDruhy[radky[row]][0] = false;
                }
            }

            for (int i = 0; i < indexyStrojeDruhy.length; i++) {
                if (indexyStrojeDruhy[i][0] == true) {
                    pocetZaznamu++;
                }
            }
            int[] druh_stroje_id = new int[pocetZaznamu];
            int indexSloupce = 0;
            for (int row = 0; row < indexyStrojeDruhy.length; row++) {
                if (indexyStrojeDruhy[row][0] == true) {
                    druh_stroje_id[indexSloupce] = ((Integer) ((Vector) vrStrojeDruhy1.get(row)).get(0)).intValue();
                    indexSloupce++;
                }
            }

            jTablePruvodkyStrojeDruhy1.setDefaultRenderer(Object.class, new TableVyberRenderer1(indexyStrojeDruhy));
            vyplnPruvodku(druh_stroje_id);
            nactiStroje(druh_stroje_id);
        }

        vrOperaceZaznamy1.add(vsOperaceZaznamy1);

        jTablePruvodkyStrojeDruhy1.setVisible(false);
        jTablePruvodkyStrojeDruhy1.setDefaultRenderer(Object.class, new TableVyberRenderer1(indexyStroje));
        jTablePruvodkyStrojeDruhy1.setVisible(true);

        tabulkaModelStroje1 = new tabulkaModelStroje1();

        jTablePruvodkyStroje1.setModel(tabulkaModelStroje1);
        jTablePruvodkyStroje1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jTablePruvodkyStroje1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        nastavTabulkuStroje1();

        tabulkaModelStroje1.oznamZmenu();
    }

    protected void nastavVyberTabulkyStroje1() {
        vrOperaceZaznamy1.removeAllElements();
        vsOperaceZaznamy1.removeAllElements();
        sloupce.removeAllElements();
        strojeTransakce.removeAllElements();
        // String[] sloupce = null;

        if (jTablePruvodkyStroje1.getSelectedRow() >= 0) {
            int[] radky = jTablePruvodkyStroje1.getSelectedRows();

            int pocetZaznamu = 0;

            for (int row = 0; row < jTablePruvodkyStroje1.getSelectedRowCount(); row++) {
                // System.out.println("Radky " + radky[row] + " x " + indexyStroje[radky[row]][0]  );
                if (indexyStroje[radky[row]][0] == false) {
                    indexyStroje[radky[row]][0] = true;
                } else {
                    indexyStroje[radky[row]][0] = false;
                }
            }

            for (int i = 0; i < indexyStroje.length; i++) {
                // System.out.println(i + " Oznaceno  " + indexyStroje[i][0]);
                if (indexyStroje[i][0] == true) {
                    pocetZaznamu++;
                }
            }
            indexyTransakce = new boolean[2][pocetZaznamu + vrOperaceZaznamyNactene1.size()];
            //sloupce = new String[pocetZaznamu];
            // strojeTransakce = new Vector();
            int indexSloupce = 0;

            for (int row = 0; row < indexyStroje.length; row++) {
                if (indexyStroje[row][0] == true) {
                    int strojeDruhId = (Integer) ((Vector) vrStroje1.get(row)).get(2);
                    if ((strojeDruhId == 9) || (strojeDruhId == 5)
                            || (strojeDruhId == 11) || (strojeDruhId == 12)
                            || (strojeDruhId == 13)) {
                        vsOperaceZaznamy1 = new Vector();
                        vsOperaceZaznamy1.add(1);
                        vsOperaceZaznamy1.add("provedeno");
                    } else {
                        vsOperaceZaznamy1 = new Vector();
                        vsOperaceZaznamy1.add(1);
                        vsOperaceZaznamy1.add("začátek práce");
                    }

                    vrOperaceZaznamy1.add(vsOperaceZaznamy1);

                    if (strojeDruhId == 9) {
                        vsOperaceZaznamy1 = new Vector();
                        vsOperaceZaznamy1.add(8);
                        vsOperaceZaznamy1.add("přířez navíc");

                        vrOperaceZaznamy1.add(vsOperaceZaznamy1);
                    } else {
                        vsOperaceZaznamy1 = new Vector();
                        vsOperaceZaznamy1.add(7);
                        vsOperaceZaznamy1.add("");

                        vrOperaceZaznamy1.add(vsOperaceZaznamy1);
                    }

                    sloupce.add((String) ((Vector) vrStroje1.get(row)).get(1));
                    strojeTransakce.add((Integer) ((Vector) vrStroje1.get(row)).get(0));
                    indexSloupce++;
                }
            }

        }

        // System.out.println("Stroje druhy renderer");
        // jTablePruvodkyStroje1.clearSelection();
        jTablePruvodkyStroje1.setVisible(false);

        jTablePruvodkyStroje1.setDefaultRenderer(Object.class, new TableVyberRenderer1(indexyStroje));
        jTablePruvodkyStroje1.setVisible(true);
        // System.out.println("Stroje druhy renderer10");
        tabulkaModelTransakce1 = new tabulkaModelTransakce1();

        jTablePruvodkyTransakce1.setModel(tabulkaModelTransakce1);
        jTablePruvodkyTransakce1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jTablePruvodkyTransakce1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        if ((sloupce != null) && (sloupceNactene != null)) {
            nastavTabulkuTransakce1(sloupce.size() + sloupceNactene.size(), 1);
        } else if ((sloupce != null) && (sloupceNactene == null)) {
            nastavTabulkuTransakce1(sloupce.size(), 2);
        } else if ((sloupce == null) && (sloupceNactene != null)) {
            nastavTabulkuTransakce1(sloupceNactene.size(), 3);
        }
        tabulkaModelTransakce1.oznamZmenu();

    }

    protected void nastavVyberTabulkyTransakce1() {
        jTablePruvodkyStrojeDruhy1.setDefaultRenderer(Object.class, new TableVyberRenderer1(indexyTransakce));
        int pocetZaznamu = 0;
        if (jTablePruvodkyTransakce1.getSelectedRow() >= 0) {
            int[] radky = jTablePruvodkyTransakce1.getSelectedRows();
            int[] sloupce = jTablePruvodkyTransakce1.getSelectedColumns();
            //System.out.println("indexy transakce : " + indexyTransakce.length + " x " + indexyTransakce[0].length);
            for (int row = 0; row < jTablePruvodkyTransakce1.getSelectedRowCount(); row++) {
                for (int column = 0; column < jTablePruvodkyTransakce1.getSelectedColumnCount(); column++) {
                    if (indexyTransakce[radky[row]][sloupce[column]] == false) {
                        indexyTransakce[radky[row]][sloupce[column]] = true;
                        if (radky[row] == 0) {
                            indexyTransakce[1][sloupce[column]] = false;
                        } else if (radky[row] == 1) {
                            indexyTransakce[0][sloupce[column]] = false;
                        }
                    } else {
                        indexyTransakce[radky[row]][sloupce[column]] = false;
                    }
                }
            }

            for (int i = 0; i < indexyTransakce.length; i++) {
                if (indexyTransakce[i][0] == true) {
                    pocetZaznamu++;
                }
            }
        }

        jTablePruvodkyTransakce1.clearSelection();
        jTablePruvodkyTransakce1.setVisible(false);
        jTablePruvodkyTransakce1.setDefaultRenderer(Object.class, new TableVyberRenderer1(indexyTransakce));
        jTablePruvodkyTransakce1.setVisible(true);
    }

    private void vyplnPruvodku(int[] druh_stroje_id) {
        try {
            for (int i = 0; i < druh_stroje_id.length; i++) {
                ResultSet pruvodka1 = PripojeniDB.dotazS(
                        "SELECT pracovni_postup_pruvodka_pruvodka_id, "
                        + "pracovni_postup_pruvodka_poradi, "
                        + "druhy_stroju_nazev, "
                        + "druhy_stroju_id, "
                        + "pracovni_postup_pruvodka_popis, "
                        + "pruvodky_stavy_pracovniho_postupu_popis, "
                        + "zamestnanci_jmeno, "
                        + "zamestnanci_prijmeni "
                        + "FROM spolecne.pracovni_postup_pruvodka "
                        + "CROSS JOIN spolecne.vazba_pracovni_postup_pruvodka_zamestnanci "
                        + "CROSS JOIN spolecne.pruvodky_stavy_pracovniho_postupu "
                        + "CROSS JOIN spolecne.druhy_stroju "
                        + "CROSS JOIN spolecne.zamestnanci "
                        + "WHERE spolecne.pracovni_postup_pruvodka.pracovni_postup_pruvodka_id = spolecne.vazba_pracovni_postup_pruvodka_zamestnanci.vazba_pracovni_postup_pruvodka_zamestnanci_postup_id "
                        + "AND spolecne.vazba_pracovni_postup_pruvodka_zamestnanci.vazba_pracovni_postup_pruvodka_zamestnanci_stav_id = spolecne.pruvodky_stavy_pracovniho_postupu.pruvodky_stavy_pracovniho_postupu_id "
                        + "AND spolecne.pracovni_postup_pruvodka.pracovni_postup_pruvodka_druh_stroje_id = spolecne.druhy_stroju.druhy_stroju_id "
                        + "AND druhy_stroju_id = " + druh_stroje_id[i] + " "
                        + "AND spolecne.vazba_pracovni_postup_pruvodka_zamestnanci.vazba_pracovni_postup_pruvodka_zamestnanci_zamestnanci_id = spolecne.zamestnanci.zamestnanci_id "
                        + "AND pracovni_postup_pruvodka_pruvodka_id = " + pruvodka_id + " "
                        + "ORDER BY pracovni_postup_pruvodka_poradi");
                while (pruvodka1.next()) {
                    vsPopisy1 = new Vector();
                    vsPopisy1.add(new Long(pruvodka1.getLong(1)));
                    vsPopisy1.add(new Integer(pruvodka1.getInt(2)));
                    vsPopisy1.add(pruvodka1.getString(3));
                    vsPopisy1.add(new Integer(pruvodka1.getInt(4)));
                    vsPopisy1.add(pruvodka1.getString(5));
                    vsPopisy1.add(pruvodka1.getString(6));
                    vsPopisy1.add(pruvodka1.getString(7) + " " + pruvodka1.getString(8));
                    vrPopisy1.add(vsPopisy1);
                }
            }
        } // konec try
        catch (Exception e) {
            e.printStackTrace();
        } // konec catch        
    }

    private void nactiHlavicku(boolean nactenaPruvodka) {
        if (nactenaPruvodka == true) {
            try {
                ResultSet pruvodka1 = PripojeniDB.dotazS(
                        "SELECT pruvodky_nazev, "
                        + "vykresy_cislo "
                        + "vykresy_revize "
                        + "FROM spolecne.pruvodky "
                        + "CROSS JOIN spolecne.vykresy "
                        + "WHERE vykresy_id = pruvodky_cislo_vykresu "
                        + "AND pruvodky_id = " + pruvodka_id);
                while (pruvodka1.next()) {
                    NazevSoucastiLabel1.setText(pruvodka1.getString(1));
                    try {
                        CisloVykresuLabel1.setText(pruvodka1.getString(2) + " " + pruvodka1.getString(3));
                    } catch (Exception e) {
                        CisloVykresuLabel1.setText(pruvodka1.getString(2));
                    }
                    // tabulkaModelPopisy1.oznamUpdateRadkyPozice(vrPopisy1.size() - 1);
                }
                ResultSet zamestnanec1 = PripojeniDB.dotazS(
                        "SELECT zamestnanci_jmeno, "
                        + "zamestnanci_prijmeni "
                        + "FROM spolecne.zamestnanci "
                        + "WHERE zamestnanci_id = " + zamestnanec_id);
                while (zamestnanec1.next()) {
                    ZamestnanecLabel1.setText(zamestnanec1.getString(1) + " " + zamestnanec1.getString(2));
                }
            } // konec try
            catch (Exception e) {
                e.printStackTrace();
                // PripojeniDB.vyjimkaS(e);
            } // konec catch
        } else {
            try {
                ResultSet zamestnanec1 = PripojeniDB.dotazS(
                        "SELECT zamestnanci_jmeno, "
                        + "zamestnanci_prijmeni "
                        + "FROM spolecne.zamestnanci "
                        + "WHERE zamestnanci_id = " + zamestnanec_id);
                while (zamestnanec1.next()) {
                    ZamestnanecLabel1.setText(zamestnanec1.getString(1) + " " + zamestnanec1.getString(2));

                }
            } // konec try
            catch (Exception e) {
                e.printStackTrace();
                // PripojeniDB.vyjimkaS(e);
            } // konec catch
        }
    }

    private void nactiTransakce(boolean nactenaPruvodka) {
        System.out.println("nacti Trans " + nactenaPruvodka);
        if (nactenaPruvodka == true) {
            pruvodky.removeAllElements();
            vrAktivniPruvodka1.removeAllElements();
            vsAktivniPruvodka1.removeAllElements();
            vsOperaceZaznamyNactene1.removeAllElements();
            vrOperaceZaznamyNactene1.removeAllElements();
            vrAktivniZaznamy1.removeAllElements();
            sloupceNactene.removeAllElements();

            strojeTransakceNactene.removeAllElements();
            try {
                ResultSet pruvodky2 = PripojeniDB.dotazS(
                        "SELECT "
                        + "zamestnanci_stroje_transakce_stroje_id, "
                        + "zamestnanci_stroje_transakce_zamestnanci_id, "
                        + "zamestnanci_stroje_transakce_id, "
                        + "stroje_nazev, "
                        + "zamestnanci_stroje_transakce_druh_id, "
                        + "zamestnanci_stroje_transakce_pruvodky_id, "
                        + "zamestnanci_stroje_transakce_log_timestamp, "
                        + "druhy_transakce_popis, "
                        + "vykresy_cislo "
                        + "FROM "
                        + "(SELECT DISTINCT ON "
                        + "(zamestnanci_stroje_transakce_stroje_id) zamestnanci_stroje_transakce_stroje_id, "
                        + "zamestnanci_stroje_transakce_zamestnanci_id, "
                        + "zamestnanci_stroje_transakce_id, "
                        + "stroje_nazev, "
                        + "zamestnanci_stroje_transakce_druh_id, "
                        + "zamestnanci_stroje_transakce_pruvodky_id, "
                        + "zamestnanci_stroje_transakce_log_timestamp, "
                        + "druhy_transakce_popis, "
                        + "vykresy_cislo "
                        + "FROM (SELECT "
                        + "zamestnanci_stroje_transakce_stroje_id, "
                        + "zamestnanci_stroje_transakce_zamestnanci_id, "
                        + "zamestnanci_stroje_transakce_id, "
                        + "stroje_nazev, "
                        + "zamestnanci_stroje_transakce_druh_id, "
                        + "zamestnanci_stroje_transakce_pruvodky_id, "
                        + "zamestnanci_stroje_transakce_log_timestamp, "
                        + "druhy_transakce_popis, "
                        + "vykresy_cislo "
                        + "FROM spolecne.zamestnanci_stroje_transakce "
                        + "CROSS JOIN spolecne.stroje "
                        + "CROSS JOIN spolecne.pruvodky "
                        + "CROSS JOIN spolecne.vykresy "
                        + "CROSS JOIN spolecne.druhy_transakce "
                        + "WHERE zamestnanci_stroje_transakce_pruvodky_id = " + pruvodka_id + " "
                        + "AND stroje_id = zamestnanci_stroje_transakce_stroje_id "
                        + "AND zamestnanci_stroje_transakce_pruvodky_id = pruvodky_id "
                        + "AND pruvodky_cislo_vykresu = vykresy_id "
                        + "AND zamestnanci_stroje_transakce_zamestnanci_id = " + zamestnanec_id + " "
                        + "AND zamestnanci_stroje_transakce_druh_id = druhy_transakce_id "
                        + "ORDER BY zamestnanci_stroje_transakce_stroje_id, zamestnanci_stroje_transakce_log_timestamp DESC ) AS r) AS t "
                        + "WHERE zamestnanci_stroje_transakce_druh_id  <> 4 ");
                while (pruvodky2.next()) {
                    aktivniZaznamy = new TridaZaznam1();
                    aktivniZaznamy.setStroje_id((new Integer(pruvodky2.getInt(1))).intValue());
                    aktivniZaznamy.setZamestnanci_id((new Integer(pruvodky2.getInt(2))).intValue());
                    aktivniZaznamy.setTransakce_id((new Long(pruvodky2.getLong(3))).longValue());

                    aktivniZaznamy.setStroje_nazev(pruvodky2.getString(4));
                    aktivniZaznamy.setDruh_id((new Integer(pruvodky2.getInt(5))).intValue());
                    aktivniZaznamy.setPruvodky_id((new Long(pruvodky2.getLong(6))).longValue());
                    aktivniZaznamy.setTimestamp(df.format(pruvodky2.getDate(7)));
                    aktivniZaznamy.setDruh_nazev(pruvodky2.getString(8));
                    aktivniZaznamy.setCislo_vykresu(pruvodky2.getString(9));

                    vrAktivniZaznamy1.add(aktivniZaznamy);
                }
            } // konec try
            catch (Exception e) {
                e.printStackTrace();
                // PripojeniDB.vyjimkaS(e);
            } // konec catch

            //System.out.println("Aktivni zaznamy size : " + vrAktivniZaznamy1.size());
            indexyTransakce = new boolean[4][vrAktivniZaznamy1.size()];
            vrOperaceZaznamyNactene1 = new Vector();

            for (int i = 0; i < vrAktivniZaznamy1.size(); i++) {
                aktivniZaznamy = (TridaZaznam1) vrAktivniZaznamy1.get(i);
                sloupceNactene.add(aktivniZaznamy.getStroje_nazev());

                // System.out.println(i + " druh prace " + aktivniZaznamy.getDruh_nazev() + " x " + aktivniZaznamy.getDruh_id());
                if ((aktivniZaznamy.getDruh_id() == 1) || (aktivniZaznamy.getDruh_id() == 3)
                        || (aktivniZaznamy.getDruh_id() == 5) || (aktivniZaznamy.getDruh_id() == 6)) {
                    vsOperaceZaznamyNactene1 = new Vector();
                    vsOperaceZaznamyNactene1.add(2);
                    vsOperaceZaznamyNactene1.add("začátek přerušení");
                    vrOperaceZaznamyNactene1.add(vsOperaceZaznamyNactene1);
                    vsOperaceZaznamyNactene1 = new Vector();
                    vsOperaceZaznamyNactene1.add(4);
                    vsOperaceZaznamyNactene1.add("konec práce");
                    vrOperaceZaznamyNactene1.add(vsOperaceZaznamyNactene1);
                } else if (aktivniZaznamy.getDruh_id() == 2) {
                    vsOperaceZaznamyNactene1 = new Vector();
                    vsOperaceZaznamyNactene1.add(3);
                    vsOperaceZaznamyNactene1.add("konec přerušení");
                    vrOperaceZaznamyNactene1.add(vsOperaceZaznamyNactene1);
                    vsOperaceZaznamyNactene1 = new Vector();
                    vsOperaceZaznamyNactene1.add(7);
                    vsOperaceZaznamyNactene1.add("");
                    vrOperaceZaznamyNactene1.add(vsOperaceZaznamyNactene1);
                }
                strojeTransakceNactene.add(aktivniZaznamy.getStroje_id());

                // System.out.println(i + " Stroje transakce " + strojeTransakce[i]);
            }
            tabulkaModelTransakce1 = new tabulkaModelTransakce1();
            jTablePruvodkyTransakce1.setModel(tabulkaModelTransakce1);
            jTablePruvodkyTransakce1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            jTablePruvodkyTransakce1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            nastavTabulkuTransakce1(sloupceNactene.size(), 3);

            tabulkaModelTransakce1.oznamZmenu();
        } else if (nactenaPruvodka == false) {
            pruvodky.removeAllElements();
            vrAktivniPruvodka1.removeAllElements();
            vsAktivniPruvodka1.removeAllElements();
            vsOperaceZaznamyNactene1.removeAllElements();
            vrOperaceZaznamyNactene1.removeAllElements();
            vrAktivniZaznamy1.removeAllElements();
            sloupceNactene.removeAllElements();
            strojeTransakceNactene.removeAllElements();
            try {
                //aktivniZaznamy = new TridaZaznam1[pruvodky.size()];
                ResultSet pruvodky2 = PripojeniDB.dotazS(
                        "SELECT "
                        + "zamestnanci_stroje_transakce_stroje_id, "
                        + "zamestnanci_stroje_transakce_zamestnanci_id, "
                        + "zamestnanci_stroje_transakce_id, "
                        + "stroje_nazev, "
                        + "zamestnanci_stroje_transakce_druh_id, "
                        + "zamestnanci_stroje_transakce_pruvodky_id, "
                        + "zamestnanci_stroje_transakce_log_timestamp, "
                        + "druhy_transakce_popis, "
                        + "vykresy_cislo "
                        + "FROM "
                        + "(SELECT DISTINCT ON "
                        + "(zamestnanci_stroje_transakce_stroje_id) zamestnanci_stroje_transakce_stroje_id, "
                        + "zamestnanci_stroje_transakce_zamestnanci_id, "
                        + "zamestnanci_stroje_transakce_id, "
                        + "stroje_nazev, "
                        + "zamestnanci_stroje_transakce_druh_id, "
                        + "zamestnanci_stroje_transakce_pruvodky_id, "
                        + "zamestnanci_stroje_transakce_log_timestamp, "
                        + "druhy_transakce_popis, "
                        + "vykresy_cislo "
                        + "FROM (SELECT "
                        + "zamestnanci_stroje_transakce_stroje_id, "
                        + "zamestnanci_stroje_transakce_zamestnanci_id, "
                        + "zamestnanci_stroje_transakce_id, "
                        + "stroje_nazev, "
                        + "zamestnanci_stroje_transakce_druh_id, "
                        + "zamestnanci_stroje_transakce_pruvodky_id, "
                        + "zamestnanci_stroje_transakce_log_timestamp, "
                        + "druhy_transakce_popis, "
                        + "vykresy_cislo "
                        + "FROM spolecne.zamestnanci_stroje_transakce "
                        + "CROSS JOIN spolecne.stroje "
                        + "CROSS JOIN spolecne.druhy_transakce "
                        + "CROSS JOIN spolecne.pruvodky "
                        + "CROSS JOIN spolecne.vykresy "
                        + "WHERE stroje_id = zamestnanci_stroje_transakce_stroje_id "
                        + "AND zamestnanci_stroje_transakce_zamestnanci_id = " + zamestnanec_id + " "
                        + "AND zamestnanci_stroje_transakce_druh_id = druhy_transakce_id "
                        + "AND zamestnanci_stroje_transakce_pruvodky_id = pruvodky_id "
                        + "AND pruvodky_cislo_vykresu = vykresy_id "
                        + "ORDER BY zamestnanci_stroje_transakce_stroje_id, zamestnanci_stroje_transakce_log_timestamp DESC ) AS r) AS t "
                        + "WHERE zamestnanci_stroje_transakce_druh_id  <> 4 ");

                while (pruvodky2.next()) {
                    aktivniZaznamy = new TridaZaznam1();
                    aktivniZaznamy.setStroje_id((new Integer(pruvodky2.getInt(1))).intValue());
                    aktivniZaznamy.setZamestnanci_id((new Integer(pruvodky2.getInt(2))).intValue());
                    aktivniZaznamy.setTransakce_id((new Long(pruvodky2.getLong(3))).longValue());
                    aktivniZaznamy.setStroje_nazev(pruvodky2.getString(4));
                    aktivniZaznamy.setDruh_id((new Integer(pruvodky2.getInt(5))).intValue());
                    aktivniZaznamy.setPruvodky_id((new Long(pruvodky2.getLong(6))).longValue());
                    aktivniZaznamy.setTimestamp(df.format(pruvodky2.getDate(7)));
                    aktivniZaznamy.setDruh_nazev(pruvodky2.getString(8));
                    aktivniZaznamy.setCislo_vykresu(pruvodky2.getString(9));

                    vrAktivniZaznamy1.add(aktivniZaznamy);
                }

            } // konec try
            catch (Exception e) {
                e.printStackTrace();
                // PripojeniDB.vyjimkaS(e);
            } // konec catch

            //System.out.println("Aktivni zaznamy size : " + vrAktivniZaznamy1.size());
            indexyTransakce = new boolean[4][vrAktivniZaznamy1.size()];
            vrOperaceZaznamyNactene1 = new Vector();
            for (int i = 0; i < vrAktivniZaznamy1.size(); i++) {
                aktivniZaznamy = (TridaZaznam1) vrAktivniZaznamy1.get(i);
                sloupceNactene.add(aktivniZaznamy.getStroje_nazev());

                if ((aktivniZaznamy.getDruh_id() == 1) || (aktivniZaznamy.getDruh_id() == 3)
                        || (aktivniZaznamy.getDruh_id() == 5) || (aktivniZaznamy.getDruh_id() == 6)) {
                    vsOperaceZaznamyNactene1 = new Vector();
                    vsOperaceZaznamyNactene1.add(2);
                    vsOperaceZaznamyNactene1.add("začátek přerušení");
                    vrOperaceZaznamyNactene1.add(vsOperaceZaznamyNactene1);
                    vsOperaceZaznamyNactene1 = new Vector();
                    vsOperaceZaznamyNactene1.add(4);
                    vsOperaceZaznamyNactene1.add("konec práce");
                    vrOperaceZaznamyNactene1.add(vsOperaceZaznamyNactene1);
                } else if (aktivniZaznamy.getDruh_id() == 2) {
                    vsOperaceZaznamyNactene1 = new Vector();
                    vsOperaceZaznamyNactene1.add(3);
                    vsOperaceZaznamyNactene1.add("konec přerušení");
                    vrOperaceZaznamyNactene1.add(vsOperaceZaznamyNactene1);
                    vsOperaceZaznamyNactene1 = new Vector();
                    vsOperaceZaznamyNactene1.add(7);
                    vsOperaceZaznamyNactene1.add("");
                    vrOperaceZaznamyNactene1.add(vsOperaceZaznamyNactene1);
                }
                strojeTransakceNactene.add(aktivniZaznamy.getStroje_id());
            }

            tabulkaModelTransakce1 = new tabulkaModelTransakce1();
            jTablePruvodkyTransakce1.setModel(tabulkaModelTransakce1);
            jTablePruvodkyTransakce1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            jTablePruvodkyTransakce1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            nastavTabulkuTransakce1(sloupceNactene.size(), 3);
            tabulkaModelTransakce1.oznamZmenu();
            pruvodka_id = -1;
        }
    }

    protected void nactiStroje(int[] druh_stroje) {
        System.out.println("nactiStroje");
        vsStroje1.removeAllElements();
        //nacist data
        try {
            for (int i = 0; i < druh_stroje.length; i++) {
                ResultSet stroje1 = PripojeniDB.dotazS(
                        "SELECT zamestnanci_stroje_transakce_stroje_id, "
                        + "zamestnanci_stroje_transakce_zamestnanci_id, "
                        + "druhy_stroju_id, "
                        + "zamestnanci_stroje_transakce_id, stroje_nazev, stroje_druh_stroje, "
                        + "zamestnanci_stroje_transakce_druh_id, "
                        + "zamestnanci_stroje_transakce_pruvodky_id, "
                        + "zamestnanci_stroje_transakce_log_timestamp, "
                        + "druhy_transakce_popis FROM (SELECT DISTINCT ON (zamestnanci_stroje_transakce_stroje_id) "
                        + "zamestnanci_stroje_transakce_stroje_id, zamestnanci_stroje_transakce_zamestnanci_id, "
                        + "zamestnanci_stroje_transakce_id, stroje_nazev, stroje_druh_stroje, zamestnanci_stroje_transakce_druh_id, "
                        + "zamestnanci_stroje_transakce_pruvodky_id, zamestnanci_stroje_transakce_log_timestamp, "
                        + "druhy_transakce_popis FROM (SELECT zamestnanci_stroje_transakce_stroje_id, "
                        + "zamestnanci_stroje_transakce_zamestnanci_id, zamestnanci_stroje_transakce_id, "
                        + "stroje_nazev, stroje_druh_stroje, zamestnanci_stroje_transakce_druh_id, zamestnanci_stroje_transakce_pruvodky_id, "
                        + "zamestnanci_stroje_transakce_log_timestamp, druhy_transakce_popis "
                        + "FROM spolecne.zamestnanci_stroje_transakce CROSS JOIN spolecne.stroje "
                        + "CROSS JOIN spolecne.druhy_transakce WHERE stroje_id = zamestnanci_stroje_transakce_stroje_id "
                        + "AND zamestnanci_stroje_transakce_druh_id = druhy_transakce_id "
                        + "ORDER BY zamestnanci_stroje_transakce_stroje_id, zamestnanci_stroje_transakce_log_timestamp DESC ) AS r) AS t "
                        + "CROSS JOIN spolecne.druhy_stroju "
                        + "WHERE zamestnanci_stroje_transakce_druh_id  = 4 "
                        + "AND druhy_stroju_id = stroje_druh_stroje "
                        + "AND druhy_stroju_id = " + druh_stroje[i] + " "
                        + "ORDER BY druhy_stroju_priorita");

                while (stroje1.next()) {
                    vsStroje1 = new Vector();
                    vsStroje1.add(new Integer(stroje1.getInt(1)).intValue());
                    vsStroje1.add(stroje1.getString(5));
                    vsStroje1.add(new Integer(stroje1.getInt(3)).intValue());
                    vrStroje1.add(vsStroje1);
                }
            }
            // }
        } // konec try
        catch (Exception e) {
            e.printStackTrace();
            // PripojeniDB.vyjimkaS(e);
        } // konec catch

        indexyStroje = new boolean[vrStroje1.size()][1];
        for (int i = 0; i < vrStroje1.size(); i++) {
            indexyStroje[i][0] = false;
        }
        nastavTabulkuStroje1();
        tabulkaModelStroje1.oznamZmenu();
    }

    protected void nactiPruvodku(boolean nactenaPruvodka) {
        vrPopisy1.removeAllElements();
        vsPopisy1.removeAllElements();
        vsStrojeDruhy1.removeAllElements();
        vrStrojeDruhy1.removeAllElements();

        //nacist data
        try {           
            ResultSet pruvodka1 = PripojeniDB.dotazS(
                    "SELECT pracovni_postup_pruvodka_pruvodka_id, pracovni_postup_pruvodka_poradi, druhy_stroju_nazev, druhy_stroju_id, "
                    + "pracovni_postup_pruvodka_popis "
                    + "FROM spolecne.pracovni_postup_pruvodka "
                    + "CROSS JOIN spolecne.druhy_stroju "
                    + "WHERE spolecne.pracovni_postup_pruvodka.pracovni_postup_pruvodka_druh_stroje_id = spolecne.druhy_stroju.druhy_stroju_id "
                    + "AND pracovni_postup_pruvodka_pruvodka_id = " + pruvodka_id + " "
                    + "ORDER BY pracovni_postup_pruvodka_poradi");
            while (pruvodka1.next()) {
                vsPopisy1 = new Vector();
                vsPopisy1.add(new Long(pruvodka1.getLong(1)));
                vsPopisy1.add(new Integer(pruvodka1.getInt(2)));
                vsPopisy1.add(pruvodka1.getString(3));
                vsPopisy1.add(new Integer(pruvodka1.getInt(4)));
                vsPopisy1.add(pruvodka1.getString(5));
               /* vsPopisy1.add(pruvodka1.getString(6));
                vsPopisy1.add(pruvodka1.getString(7) + " " + pruvodka1.getString(8));*/
                vrPopisy1.add(vsPopisy1);
                // tabulkaModelPopisy1.oznamUpdateRadkyPozice(vrPopisy1.size() - 1);
            }

            for (int i = 0; i < vrPopisy1.size(); i++) {
                boolean pridat = true;
                for (int j = 0; j < vrStrojeDruhy1.size(); j++) {
                    if (((Integer) ((Vector) vrStrojeDruhy1.get(j)).get(0)).intValue()
                            == ((Integer) ((Vector) vrPopisy1.get(i)).get(3)).intValue()) {
                        pridat = false;
                    }
                    vsStrojeDruhy1 = new Vector();
                }
                if (pridat == true) {
                    vsStrojeDruhy1 = new Vector();
                    vsStrojeDruhy1.add(((Integer) ((Vector) vrPopisy1.get(i)).get(3)).intValue());
                    vsStrojeDruhy1.add((String) ((Vector) vrPopisy1.get(i)).get(2));
                    vrStrojeDruhy1.add(vsStrojeDruhy1);
                }
            }
            // }
        } // konec try        
        catch (Exception e) {
            e.printStackTrace();
            // PripojeniDB.vyjimkaS(e);
        } // konec catch
        indexyStrojeDruhy = new boolean[vrStrojeDruhy1.size()][1];
        for (int i = 0; i < vrStrojeDruhy1.size(); i++) {
            indexyStrojeDruhy[i][0] = false;
        }
        nastavTabulkuStrojeDruhy1();
        tabulkaModelStrojeDruhy1.oznamZmenu();
    }

    protected void nactiVsechnyStroje() {
        vsStrojeDruhy1.removeAllElements();
        vrStrojeDruhy1.removeAllElements();
        //nacist data
        try {
            ResultSet pruvodka1 = PripojeniDB.dotazS(
                    "SELECT druhy_stroju_id, "
                    + "druhy_stroju_nazev "
                    + "FROM spolecne.druhy_stroju "
                    + "ORDER BY druhy_stroju_priorita");
            while (pruvodka1.next()) {
                vsStrojeDruhy1 = new Vector();
                vsStrojeDruhy1.add(pruvodka1.getInt(1));
                vsStrojeDruhy1.add(pruvodka1.getString(2));
                vrStrojeDruhy1.add(vsStrojeDruhy1);
                // tabulkaModelPopisy1.oznamUpdateRadkyPozice(vrPopisy1.size() - 1);
            }
        } // konec try
        catch (Exception e) {
            e.printStackTrace();
        } // konec catch
        indexyStrojeDruhy = new boolean[vrStrojeDruhy1.size()][1];
        for (int i = 0; i < vrStrojeDruhy1.size(); i++) {
            indexyStrojeDruhy[i][0] = false;
        }
        nastavTabulkuStrojeDruhy1();
        tabulkaModelStrojeDruhy1.oznamZmenu();
    }

    protected void udrzbaStroje() {
        PruvodkaTextField1.setText("150");
        vsStrojeDruhy1.removeAllElements();
        vrStrojeDruhy1.removeAllElements();

        pruvodka_id = Long.valueOf(PruvodkaTextField1.getText().trim()).longValue();
        zamestnanec_id = Integer.valueOf(ZamestnanecTextField1.getText().trim()).intValue();
        nactiPruvodku(true);
        nactiHlavicku(true);
        nactiTransakce(true);
        vsechnyStrojeButton1.setEnabled(true);
        vsechnyStrojeButton1.setVisible(true);

        nactiVsechnyStroje();

        //nacist data
        try {
            /*ResultSet pruvodka1 = PripojeniDB.dotazS(
                    "SELECT pracovni_postup_pruvodka_pruvodka_id, "
                    + "pracovni_postup_pruvodka_poradi, "
                    + "druhy_stroju_nazev, "
                    + "druhy_stroju_id, "
                    + "pracovni_postup_pruvodka_popis, "
                    + "pruvodky_stavy_pracovniho_postupu_popis, "
                    + "zamestnanci_jmeno, "
                    + "zamestnanci_prijmeni "
                    + "FROM spolecne.pracovni_postup_pruvodka "
                    + "CROSS JOIN spolecne.vazba_pracovni_postup_pruvodka_zamestnanci "
                    + "CROSS JOIN spolecne.pruvodky_stavy_pracovniho_postupu "
                    + "CROSS JOIN spolecne.druhy_stroju "
                    + "CROSS JOIN spolecne.zamestnanci "
                    + "WHERE spolecne.pracovni_postup_pruvodka.pracovni_postup_pruvodka_id = spolecne.vazba_pracovni_postup_pruvodka_zamestnanci.vazba_pracovni_postup_pruvodka_zamestnanci_postup_id "
                    + "AND spolecne.vazba_pracovni_postup_pruvodka_zamestnanci.vazba_pracovni_postup_pruvodka_zamestnanci_stav_id = spolecne.pruvodky_stavy_pracovniho_postupu.pruvodky_stavy_pracovniho_postupu_id "
                    + "AND spolecne.pracovni_postup_pruvodka.pracovni_postup_pruvodka_druh_stroje_id = spolecne.druhy_stroju.druhy_stroju_id "
                    + "AND spolecne.vazba_pracovni_postup_pruvodka_zamestnanci.vazba_pracovni_postup_pruvodka_zamestnanci_zamestnanci_id = spolecne.zamestnanci.zamestnanci_id "
                    + "AND pracovni_postup_pruvodka_pruvodka_id = " + 150);*/
             ResultSet pruvodka1 = PripojeniDB.dotazS(
                    "SELECT pracovni_postup_pruvodka_pruvodka_id, pracovni_postup_pruvodka_poradi, druhy_stroju_nazev, druhy_stroju_id, "
                    + "pracovni_postup_pruvodka_popis "
                    + "FROM spolecne.pracovni_postup_pruvodka "
                    + "CROSS JOIN spolecne.druhy_stroju "
                    + "WHERE spolecne.pracovni_postup_pruvodka.pracovni_postup_pruvodka_druh_stroje_id = spolecne.druhy_stroju.druhy_stroju_id "
                    + "AND pracovni_postup_pruvodka_pruvodka_id = " + 150 + " "
                    + "ORDER BY pracovni_postup_pruvodka_poradi");
            while (pruvodka1.next()) {
                vsPopisy1 = new Vector();
                vsPopisy1.add(new Long(pruvodka1.getLong(1)));
                vsPopisy1.add(new Integer(pruvodka1.getInt(2)));
                vsPopisy1.add(pruvodka1.getString(3));
                vsPopisy1.add(new Integer(pruvodka1.getInt(4)));
                vsPopisy1.add(pruvodka1.getString(5));
               /* vsPopisy1.add(pruvodka1.getString(6));
                vsPopisy1.add(pruvodka1.getString(7) + " " + pruvodka1.getString(8));*/
                vrPopisy1.add(vsPopisy1);
                // tabulkaModelPopisy1.oznamUpdateRadkyPozice(vrPopisy1.size() - 1);
            }

            for (int i = 0; i < vrPopisy1.size(); i++) {
                boolean pridat = true;
                for (int j = 0; j < vrStrojeDruhy1.size(); j++) {
                    if (((Integer) ((Vector) vrStrojeDruhy1.get(j)).get(0)).intValue()
                            == ((Integer) ((Vector) vrPopisy1.get(i)).get(3)).intValue()) {
                        pridat = false;
                    }
                    vsStrojeDruhy1 = new Vector();
                }
                if (pridat == true) {
                    vsStrojeDruhy1 = new Vector();
                    vsStrojeDruhy1.add(((Integer) ((Vector) vrPopisy1.get(i)).get(3)).intValue());
                    vsStrojeDruhy1.add((String) ((Vector) vrPopisy1.get(i)).get(2));
                    vrStrojeDruhy1.add(vsStrojeDruhy1);

                }
            }

            // }
        } // konec try
        catch (Exception e) {
            e.printStackTrace();
            // PripojeniDB.vyjimkaS(e);
        } // konec catch

        indexyStrojeDruhy = new boolean[vrStrojeDruhy1.size()][1];
        for (int i = 0; i < vrStrojeDruhy1.size(); i++) {
            indexyStrojeDruhy[i][0] = false;
        }
        nastavTabulkuStrojeDruhy1();

        //nastavTabulkuStrojeDruhy1();
        tabulkaModelStrojeDruhy1.oznamZmenu();
    }

    private boolean aktivniStroj(int stroj_id) {
        try {
            vrStroje1.removeAllElements();
            ResultSet stroje1 = PripojeniDB.dotazS(
                    "SELECT stroje_ip FROM spolecne.stroje WHERE stroje_id = " + stroj_id);
            String IPadress = "";
            while (stroje1.next()) {
                IPadress = stroje1.getString(1);
            }
            InetAddress address = InetAddress.getByName(IPadress);
            if (address.isReachable(3000)) {
                return true;
            } else {
                return false;
            }
        } // konec try
        catch (Exception e) {
            e.printStackTrace();
            PripojeniDB.vyjimkaS(e);
            return false;
        } // konec catch
    }

    protected void ulozTransakce() {
        String dotaz = "";
        long transakce_id = -1;
        int stroje_id = -1;
        int druh_id = -1;
        int druh1_id = -1;
        int chyba = 0;
        boolean odectena = true;
        for (int row = 0; row < indexyTransakce.length; row++) {
            for (int column = 0; column < indexyTransakce[0].length; column++) {
                if (indexyTransakce[row][column] == true) {
                    if (vrAktivniZaznamy1.size() > 0) {
                        if (column < vrAktivniZaznamy1.size()) {
                            pruvodka_id = ((TridaZaznam1) vrAktivniZaznamy1.get(column)).getPruvodky_id();
                        }
                    }
                    if (column < strojeTransakceNactene.size()) {
                        stroje_id = (Integer) strojeTransakceNactene.get(column);
                        druh_id = (Integer) (((Vector) vrOperaceZaznamyNactene1.elementAt(2 * column + row)).elementAt(0));
                    } else {
                        stroje_id = (Integer) strojeTransakce.get(column - strojeTransakceNactene.size());
                        druh_id = (Integer) (((Vector) vrOperaceZaznamy1.elementAt(2 * (column - (vrOperaceZaznamyNactene1.size() / 2)) + row)).elementAt(0));
                    }
                    System.out.println("SELECT stroje_druh_stroje FROM spolecne.stroje WHERE stroje_id = " + stroje_id);
                    int druhStroje = SQLFunkceObecne2.selectINTPole("SELECT stroje_druh_stroje FROM spolecne.stroje WHERE stroje_id = " + stroje_id);
                    if (druhStroje == 9) {

                        MaterialPila materialPila = new MaterialPila(this, true, pruvodka_id);
                        int narezanoSklad = materialPila.narezanoSklad;

                        long skladTransakceId = -1;
                        try {
                            ResultSet q = PripojeniDB.dotazS("SELECT vykresy_cislo, pruvodky_polotovar_pocet_kusu, pruvodky_polotovar_id, pruvodky_material_prumerna_delka, pruvodky_narezano_sklad, pruvodky_odectena  "
                                    + "FROM spolecne.pruvodky "
                                    + "CROSS JOIN spolecne.vykresy "
                                    + "WHERE vykresy_id = pruvodky_cislo_vykresu "
                                    + "AND pruvodky_id = " + pruvodka_id);
                            while (q.next()) {
                                String vykres = TextFunkce1.osetriCteniTextDB1(q.getString(1));
                                int pocetKusuPolotovar = q.getInt(2);
                                int polotovary_id = q.getInt(3);
                                int materialDelka = q.getInt(4);
                                int pruvodkaNarezanoSklad = q.getInt(5);
                                odectena = q.getBoolean(6);
                                if (narezanoSklad > 0) {
                                    int a = PripojeniDB.dotazIUD("UPDATE spolecne.pruvodky "
                                            + "SET pruvodky_narezano_sklad = " + (pruvodkaNarezanoSklad + narezanoSklad) + " "
                                            + "WHERE pruvodky_id= " + pruvodka_id);
                                }

                                // if ((narezanoSklad - pocetKusuPolotovar) != 0 && materialDelka != 0) {
                                if ((narezanoSklad) != 0 && materialDelka != 0) {
                                    ResultSet id = PripojeniDB.dotazS("SELECT MAX(sklady_polotovary_transakce_id) FROM logistika.sklady_polotovary_transakce");
                                    while (id.next()) {
                                        skladTransakceId = id.getLong(1) + 1;
                                    }
                                    if (druh_id == 1) {
                                        //int pocet_mj = (narezanoSklad - pocetKusuPolotovar) * materialDelka;
                                        int pocet_mj = narezanoSklad * materialDelka;
                                        int transakce_druh_id = 0;
                                        if (pocet_mj < 0) {
                                            transakce_druh_id = 200;
                                        } else if (pocet_mj > 0) {
                                            transakce_druh_id = 100;
                                        }

                                        int a = PripojeniDB.dotazIUD("INSERT INTO logistika.sklady_polotovary_transakce("
                                                + " sklady_polotovary_transakce_id, "
                                                + "sklady_polotovary_transakce_sklad_id, "
                                                + "sklady_polotovary_transakce_umisteni_id, "
                                                + "sklady_polotovary_transakce_druh_id, "
                                                + "sklady_polotovary_transakce_polotovar_id, "
                                                + "sklady_polotovary_transakce_popis, "
                                                + "sklady_polotovary_transakce_pocet_mj, "
                                                + "sklady_polotovary_transakce_log_uzivatel) "
                                                + "VALUES( " + skladTransakceId + ", " + 1 + ", " + 1 + ", " + transakce_druh_id + ", " + polotovary_id + ", "
                                                + TextFunkce1.osetriZapisTextDB1(pruvodka_id + ", " + vykres) + ", " + Math.abs(pocet_mj) + ", 'mikronpanel')");

                                        a = PripojeniDB.dotazIUD("UPDATE spolecne.pruvodky "
                                                + "SET pruvodky_odectena= true "
                                                + "WHERE pruvodky_id= " + pruvodka_id);
                                    } else if (druh_id == 8) {
                                        int pocet_mj = narezanoSklad * materialDelka;
                                        int transakce_druh_id = 0;
                                        transakce_druh_id = 100;
                                        druh_id = 1;

                                        int a = PripojeniDB.dotazIUD("INSERT INTO logistika.sklady_polotovary_transakce("
                                                + " sklady_polotovary_transakce_id, "
                                                + "sklady_polotovary_transakce_sklad_id, "
                                                + "sklady_polotovary_transakce_umisteni_id, "
                                                + "sklady_polotovary_transakce_druh_id, "
                                                + "sklady_polotovary_transakce_polotovar_id, "
                                                + "sklady_polotovary_transakce_popis, "
                                                + "sklady_polotovary_transakce_pocet_mj, "
                                                + "sklady_polotovary_transakce_log_uzivatel) "
                                                + "VALUES( " + skladTransakceId + ", " + 1 + ", " + 1 + ", " + transakce_druh_id + ", " + polotovary_id + ", "
                                                + TextFunkce1.osetriZapisTextDB1(pruvodka_id + ", " + vykres) + ", " + Math.abs(pocet_mj) + ", 'mikronpanel')");

                                        a = PripojeniDB.dotazIUD("UPDATE spolecne.pruvodky "
                                                + "SET pruvodky_odectena= true "
                                                + "WHERE pruvodky_id= " + pruvodka_id);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //System.out.println("narez " + skladMaterialu.narezanoSklad);
                    } else if (druhStroje == 2 || druhStroje == 3 || druhStroje == 4) {
                        boolean showMaterial = true;
                        for (int i = 0; i < vrStrojeDruhy1.size(); i++) {
                            if ((Integer) ((Vector) vrStrojeDruhy1.get(i)).get(0) == 9) {
                                showMaterial = false;
                            }
                            if ((Integer) ((Vector) vrStrojeDruhy1.get(i)).get(0) == druhStroje && i != 0) {
                                showMaterial = false;
                            }
                        }

                        if (SQLFunkceObecne2.selectBooleanPole("SELECT pruvodky_odectena FROM spolecne.pruvodky WHERE pruvodky_id = " + pruvodka_id)) {
                            if (SQLFunkceObecne2.selectINTPole("SELECT pracovni_postup_pruvodka_druh_stroje_id "
                                    + "FROM spolecne.pracovni_postup_pruvodka "
                                    + "WHERE pracovni_postup_pruvodka_pruvodka_id = " + pruvodka_id + " "
                                    + "ORDER BY pracovni_postup_pruvodka_poradi "
                                    + "LIMIT 1") == 9) {
                                showMaterial = false;
                            }
                        }

                        if (showMaterial && (druh_id == 4 || druh_id == 8)) {
                            MaterialSoustruh materialSoustruh = new MaterialSoustruh(this, true, pruvodka_id);
                            int narezanoSklad = materialSoustruh.narezanoSklad;
                            boolean prirez = materialSoustruh.prirez;
                            long skladTransakceId = -1;
                            try {
                                ResultSet q = PripojeniDB.dotazS("SELECT vykresy_cislo, pruvodky_polotovar_pocet_kusu, pruvodky_polotovar_id, pruvodky_material_prumerna_delka, pruvodky_odectena "
                                        + "FROM spolecne.pruvodky "
                                        + "CROSS JOIN spolecne.vykresy "
                                        + "WHERE vykresy_id = pruvodky_cislo_vykresu "
                                        + "AND pruvodky_id = " + pruvodka_id);
                                while (q.next()) {
                                    String vykres = TextFunkce1.osetriCteniTextDB1(q.getString(1));
                                    int pocetKusuPolotovar = q.getInt(2);
                                    int polotovary_id = q.getInt(3);
                                    int materialDelka = q.getInt(4);
                                    odectena = q.getBoolean(5);

                                    /*if (materialDelka != 0 && (((narezanoSklad - pocetKusuPolotovar * materialDelka) != 0 && prirez == false)
                                            || (narezanoSklad != 0 && prirez == true))) {*/
                                    if (materialDelka != 0 && (((narezanoSklad) != 0 && prirez == false && odectena == false)
                                            || (narezanoSklad != 0 && prirez == true))) {
                                        ResultSet id = PripojeniDB.dotazS("SELECT MAX(sklady_polotovary_transakce_id) FROM logistika.sklady_polotovary_transakce");
                                        while (id.next()) {
                                            skladTransakceId = id.getLong(1) + 1;
                                        }

                                        if (druh_id == 4) {
                                            int pocet_mj;
                                            if (prirez) {
                                                pocet_mj = narezanoSklad;
                                            } else {
                                                // pocet_mj = narezanoSklad - pocetKusuPolotovar * materialDelka;
                                                pocet_mj = narezanoSklad;
                                            }
                                            int transakce_druh_id = 0;
                                            if (pocet_mj < 0) {
                                                transakce_druh_id = 200;
                                            } else if (pocet_mj > 0) {
                                                transakce_druh_id = 100;
                                            }

                                            int a = PripojeniDB.dotazIUD("INSERT INTO logistika.sklady_polotovary_transakce("
                                                    + " sklady_polotovary_transakce_id, "
                                                    + "sklady_polotovary_transakce_sklad_id, "
                                                    + "sklady_polotovary_transakce_umisteni_id, "
                                                    + "sklady_polotovary_transakce_druh_id, "
                                                    + "sklady_polotovary_transakce_polotovar_id, "
                                                    + "sklady_polotovary_transakce_popis, "
                                                    + "sklady_polotovary_transakce_pocet_mj, "
                                                    + "sklady_polotovary_transakce_log_uzivatel) "
                                                    + "VALUES( " + skladTransakceId + ", " + 1 + ", " + 1 + ", " + transakce_druh_id + ", " + polotovary_id + ", "
                                                    + TextFunkce1.osetriZapisTextDB1(pruvodka_id + ", " + vykres) + ", " + Math.abs(pocet_mj) + ", 'mikronpanel')");

                                            a = PripojeniDB.dotazIUD("UPDATE spolecne.pruvodky "
                                                    + "SET pruvodky_odectena= true "
                                                    + "WHERE pruvodky_id= " + pruvodka_id);
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }

                    transakce_id = -1;
                    try {
                        ResultSet id = PripojeniDB.dotazS("SELECT MAX(zamestnanci_stroje_transakce_id) FROM spolecne.zamestnanci_stroje_transakce");
                        while (id.next()) {
                            transakce_id = id.getLong(1) + 1;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        chyba++;
                    }

                    try {
                        dotaz = "SELECT MAX(zamestnanci_stroje_transakce_id), "
                                + "zamestnanci_stroje_transakce_druh_id "
                                + "FROM spolecne.zamestnanci_stroje_transakce "
                                + "WHERE zamestnanci_stroje_transakce_zamestnanci_id = " + zamestnanec_id + " AND "
                                + "zamestnanci_stroje_transakce_stroje_id = " + stroje_id + " AND "
                                + "zamestnanci_stroje_transakce_pruvodky_id = ";
                        /*  if (pruvodka_id == -1) {
                            dotaz += ((TridaZaznam1) vrAktivniZaznamy1.get(column)).getPruvodky_id();
                        } else {*/
                        dotaz += pruvodka_id;
                        //}
                        dotaz += " GROUP BY zamestnanci_stroje_transakce_druh_id,zamestnanci_stroje_transakce_id "
                                + "ORDER BY zamestnanci_stroje_transakce_log_timestamp DESC LIMIT 1";
                        ResultSet id = PripojeniDB.dotazS(dotaz);
                        while (id.next()) {
                            druh1_id = id.getInt(2);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        chyba++;
                    }

                    if (druh1_id == 6) {
                        dotaz = "INSERT INTO spolecne.zamestnanci_stroje_transakce( "
                                + "zamestnanci_stroje_transakce_id, zamestnanci_stroje_transakce_zamestnanci_id, "
                                + "zamestnanci_stroje_transakce_stroje_id, zamestnanci_stroje_transakce_druh_id, "
                                + "zamestnanci_stroje_transakce_pruvodky_id) "
                                + "VALUES (" + transakce_id + ", " + zamestnanec_id + ", " + stroje_id + ", "
                                + 5 + ", ";
                        /* if (pruvodka_id == -1) {
                            dotaz += ((TridaZaznam1) vrAktivniZaznamy1.get(column)).getPruvodky_id() + ")";
                        } else {*/
                        dotaz += pruvodka_id + ")";
                        // }

                        try {
                            int a = PripojeniDB.dotazIUD(dotaz);
                            transakce_id++;
                        } catch (Exception e) {
                            e.printStackTrace();
                            chyba++;
                        }
                    }
                    dotaz = "INSERT INTO spolecne.zamestnanci_stroje_transakce( "
                            + "zamestnanci_stroje_transakce_id, zamestnanci_stroje_transakce_zamestnanci_id, "
                            + "zamestnanci_stroje_transakce_stroje_id, zamestnanci_stroje_transakce_druh_id, "
                            + "zamestnanci_stroje_transakce_pruvodky_id) "
                            + "VALUES (" + transakce_id + ", " + zamestnanec_id + ", " + stroje_id + ", "
                            + druh_id + ", ";
                    /* if (pruvodka_id == -1) {
                        dotaz += ((TridaZaznam1) vrAktivniZaznamy1.get(column)).getPruvodky_id() + ")";
                    } else {*/
                    dotaz += pruvodka_id + ")";
                    //}

                    try {
                        int a = PripojeniDB.dotazIUD(dotaz);
                    } catch (Exception e) {
                        e.printStackTrace();
                        chyba++;
                    }
                    if ((stroje_id == 13) || (stroje_id == 17) || (stroje_id == 18)) {
                        transakce_id++;
                        dotaz = "INSERT INTO spolecne.zamestnanci_stroje_transakce( "
                                + "zamestnanci_stroje_transakce_id, zamestnanci_stroje_transakce_zamestnanci_id, "
                                + "zamestnanci_stroje_transakce_stroje_id, zamestnanci_stroje_transakce_druh_id, "
                                + "zamestnanci_stroje_transakce_pruvodky_id) "
                                + "VALUES (" + transakce_id + ", " + zamestnanec_id + ", " + stroje_id + ", "
                                + 4 + ", ";
                        /*  if (pruvodka_id == -1) {
                            dotaz += ((TridaZaznam1) vrAktivniZaznamy1.get(column)).getPruvodky_id() + ")";
                        } else {*/
                        dotaz += pruvodka_id + ")";
                        //}

                        try {
                            int a = PripojeniDB.dotazIUD(dotaz);
                        } catch (Exception e) {
                            e.printStackTrace();
                            chyba++;
                        }
                    }
                }
            }
        }
        if (chyba == 0) {
            vycistitPanel();
            PotvrditButton1.setText("Potvrdit");
        } else {
            PotvrditButton1.setText("Došlo k chybě, opakujte požadavek");
        }
    }

    protected void vycistitPanel() {
        ZamestnanecTextField1.setText("");
        PruvodkaTextField1.setText("");

        vrPopisy1 = new Vector();
        vsPopisy1 = new Vector();

        vrStrojeDruhy1 = new Vector();
        vsStrojeDruhy1 = new Vector();

        vrStroje1 = new Vector();
        vsStroje1 = new Vector();

        vsZamStrojeLog1 = new Vector();
        vrZamStrojeLog1 = new Vector();

        vsAktivniPruvodka1 = new Vector();
        vrAktivniPruvodka1 = new Vector();

        tabulkaModelStrojeDruhy1.oznamZmenu();
        tabulkaModelStroje1.oznamZmenu();
        tabulkaModelTransakce1.oznamZmenu();
        ZamestnanecLabel1.setText("");
        CisloVykresuLabel1.setText("");
        NazevSoucastiLabel1.setText("");
        pruvodka_id = -1;
        zamestnanec_id = -1;
        vrAktivniZaznamy1 = new Vector();
        strojeTransakce = new Vector();
        sloupce = new Vector();
        strojeTransakceNactene = new Vector();
        sloupceNactene = new Vector();
        ZamestnanecTextField1.requestFocus();
        PotvrditButton1.setText("Potvrdit");

    }

    protected class tabulkaModelStrojeDruhy1 extends AbstractTableModel {

        protected final String[] columnNames = {"Druh stroje"};

        public void pridejSadu() {
            fireTableRowsInserted(0, vrStrojeDruhy1.size());
            //updateZaznamyProgram1();
            if (vrStrojeDruhy1.size() > 0) {
                jTablePruvodkyStrojeDruhy1.changeSelection(0, 0, false, false);
            }
        }

        public void oznamZmenu() {
            fireTableDataChanged();
//        if (vrPruvodka1size() > 0)
//        jTablePruvodkyPruvodky.changeSelection(0, 0, false, false);
        }//konec oznamZmenu

        @Override
        public Object getValueAt(int row, int col) {
            //  System.out.println("getValueAt ProgFram");
            try {
                return (((Vector) vrStrojeDruhy1.elementAt(row)).elementAt(col + 1));
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }//konec getValueAt

        @Override
        public void setValueAt(Object value, int row, int col) {
            // System.out.println("setValueAt PruvFram");
            if (col == 1) {
                ((Vector) vrStrojeDruhy1.get(row)).setElementAt(value, col);
                fireTableCellUpdated(row, col);
                // nastavVyberTabulkyPruvodka1();
                return;
            } //konec if
            try {
                vrStrojeDruhy1.get(row);
                // pruvodka = updatePruvodka1DB(pruvodka);
                //akce po update s osetrenim chyb
                if (pruvodka.pruvodky_id > 0) {
                    fireTableCellUpdated(row, col);
                    nastavHodnotuNaVybrane(pruvodka);
                }
                if (pruvodka.pruvodky_id == 0) {
                    //  deletePruvodka1TAB();
                }
            }//konec try
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }//konec setValueAt

        public boolean nastavHodnotuNaVybrane(TridaPruvodka1 bt) {
            //System.out.println("nastavHodnotuNaVybranePruvodka1 " + jTablePruvodkyStrojeDruhy1.getSelectedRow());
            return nastavHodnotuNaPozici(bt, jTablePruvodkyStrojeDruhy1.getSelectedRow());
        }//konec getNastavHodnotuNaVybrane

        public boolean nastavHodnotuNaPozici(TridaPruvodka1 nastavPruv, int pozice) {
            //System.out.println("nastav hodnotu na pozici");
            try {
                v = new Vector();

                v.add(new Long(nastavPruv.pruvodky_id));
                v.add(new String(nastavPruv.pruvodky_nazev));
                v.add(new Integer(nastavPruv.pruvodky_cislo_vykresu));
                v.add(df.format(nastavPruv.pruvodky_termin_dokonceni));
                v.add(nastavPruv.pruvodky_pocet_kusu);
                v.add(nastavPruv.pruvodky_pocet_kusu_sklad);
                v.add(nastavPruv.pruvodky_pocet_kusu_polotovar);
                v.add(nastavPruv.poznamky);

                //  vrPruvodka1.setElementAt(v, pozice);
                oznamUpdateRadkyPozice(pozice);

                return true;
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        }//konec nastavHodnotuNaPozici

        public void oznamUpdateRadkyVybrane() {
            fireTableRowsUpdated(jTablePruvodkyStrojeDruhy1.getSelectedRow(), jTablePruvodkyStrojeDruhy1.getSelectedRow());
        }//konec oznamUpdateRadky

        public void oznamUpdateRadkyPozice(int pozice) {
            fireTableRowsUpdated(pozice, pozice);
        }//konec oznamUpdateRadky

        @Override
        public int getColumnCount() {
            return columnNames.length;
//        return vs.size();
        }//konec getRowCount*/

        @Override
        public int getRowCount() {
            return vrStrojeDruhy1.size();
        }//konec getRowCount

        @Override
        public String getColumnName(int col) {
            try {
                return columnNames[col];
            } catch (Exception ex) {
                return null;
            }
        }//konec getColumnName
    }

    protected class tabulkaModelStroje1 extends AbstractTableModel {

        protected final String[] columnNames = {"Stroj"};

        public void pridejSadu() {
            fireTableRowsInserted(0, vrStroje1.size());
            //updateZaznamyProgram1();
            if (vrStroje1.size() > 0) {
                jTablePruvodkyStroje1.changeSelection(0, 0, false, false);
            }
        }

        public void oznamZmenu() {
            fireTableDataChanged();
//        if (vrPruvodka1size() > 0)
//        jTablePruvodkyPruvodky.changeSelection(0, 0, false, false);
        }//konec oznamZmenu

        @Override
        public Object getValueAt(int row, int col) {
            //  System.out.println("getValueAt ProgFram");
            try {
                return (((Vector) vrStroje1.elementAt(row)).elementAt(col + 1));
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }//konec getValueAt

        @Override
        public void setValueAt(Object value, int row, int col) {
            // System.out.println("setValueAt PruvFram");
            if (col == 1) {
                ((Vector) vrStroje1.get(row)).setElementAt(value, col);
                fireTableCellUpdated(row, col);
                // nastavVyberTabulkyPruvodka1();
                return;
            } //konec if
            try {
                vrStroje1.get(row);
                // pruvodka = updatePruvodka1DB(pruvodka);
                //akce po update s osetrenim chyb
                if (pruvodka.pruvodky_id > 0) {
                    fireTableCellUpdated(row, col);
                    nastavHodnotuNaVybrane(pruvodka);
                }
                if (pruvodka.pruvodky_id == 0) {
                    //  deletePruvodka1TAB();
                }

            }//konec try
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }//konec setValueAt

        public boolean nastavHodnotuNaVybrane(TridaPruvodka1 bt) {
            //System.out.println("nastavHodnotuNaVybranePruvodka1 " + jTablePruvodkyStroje1.getSelectedRow());
            return nastavHodnotuNaPozici(bt, jTablePruvodkyStroje1.getSelectedRow());
        }//konec getNastavHodnotuNaVybrane

        public boolean nastavHodnotuNaPozici(TridaPruvodka1 nastavPruv, int pozice) {
            //System.out.println("nastav hodnotu na pozici");
            try {
                v = new Vector();

                v.add(new Long(nastavPruv.pruvodky_id));
                v.add(new String(nastavPruv.pruvodky_nazev));
                v.add(new Integer(nastavPruv.pruvodky_cislo_vykresu));
                v.add(df.format(nastavPruv.pruvodky_termin_dokonceni));
                v.add(nastavPruv.pruvodky_pocet_kusu);
                v.add(nastavPruv.pruvodky_pocet_kusu_sklad);
                v.add(nastavPruv.pruvodky_pocet_kusu_polotovar);
                v.add(nastavPruv.poznamky);

                //  vrPruvodka1.setElementAt(v, pozice);
                oznamUpdateRadkyPozice(pozice);

                return true;
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        }//konec nastavHodnotuNaPozici

        public void oznamUpdateRadkyVybrane() {
            fireTableRowsUpdated(jTablePruvodkyStroje1.getSelectedRow(), jTablePruvodkyStroje1.getSelectedRow());
        }//konec oznamUpdateRadky

        public void oznamUpdateRadkyPozice(int pozice) {
            fireTableRowsUpdated(pozice, pozice);
        }//konec oznamUpdateRadky

        @Override
        public int getColumnCount() {
            return columnNames.length;
//        return vs.size();
        }//konec getRowCount*/

        @Override
        public int getRowCount() {
            return vrStroje1.size();
        }//konec getRowCount

        @Override
        public String getColumnName(int col) {
            try {
                return columnNames[col];
            } catch (Exception ex) {
                return null;
            }
        }//konec getColumnName
    }

    protected class tabulkaModelTransakce1 extends AbstractTableModel {

        protected String[] columnNames;

        public tabulkaModelTransakce1() {
            try {
                columnNames = new String[sloupceNactene.size() + sloupce.size()];
                for (int i = 0; i < columnNames.length; i++) {
                    if (i < sloupceNactene.size()) {
                        columnNames[i] = (String) sloupceNactene.get(i);
                    } else {
                        columnNames[i] = (String) sloupce.get(i - sloupceNactene.size());
                    }
                }
            } catch (Exception e) {
                columnNames = new String[1];
                columnNames[0] = "Akce";
            }
        }


        /*public void nastavSloupce(String[] sloupce) {
        columnNames = new String[sloupce.length];
        for(int i = 0; i < columnNames.length; i++) {
        columnNames[i] = sloupce[i];
        }
        }     */
        public void pridejSadu() {
            fireTableRowsInserted(0, vrOperaceZaznamy1.size());
            //updateZaznamyProgram1();
            if (vrOperaceZaznamy1.size() > 0) {
                jTablePruvodkyTransakce1.changeSelection(0, 0, false, false);
            }
        }

        public void oznamZmenu() {
            fireTableDataChanged();
//        if (vrPruvodka1size() > 0)
//        jTablePruvodkyPruvodky.changeSelection(0, 0, false, false);
        }//konec oznamZmenu

        @Override
        public Object getValueAt(int row, int col) {
            // System.out.println(vrOperaceZaznamy1.size() + " getValueAt transakce " + row + " x " + col);
            try {
                //System.out.println("TableTrans Col  " + col);
                ///System.out.println("vrOp " + vrOperaceZaznamy1.size() + " x " + (2 * (col - (vrOperaceZaznamyNactene1.size() / 2)) + row));
                if (col < (vrOperaceZaznamyNactene1.size() / 2)) {
                    return (((Vector) vrOperaceZaznamyNactene1.elementAt(2 * col + row)).elementAt(1));
                } else {
                    col = col - (vrOperaceZaznamyNactene1.size() / 2);
                    //System.out.println("TableTrans ColX  " + col);
                    return (((Vector) vrOperaceZaznamy1.elementAt((2 * col + row))).elementAt(1));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }//konec getValueAt

        @Override
        public void setValueAt(Object value, int row, int col) {
            // System.out.println("setValueAt PruvFram");
            if (col == 1) {
                ((Vector) vrOperaceZaznamy1.get(row)).setElementAt(value, col);
                fireTableCellUpdated(row, col);
                // nastavVyberTabulkyPruvodka1();
                return;
            } //konec if
            try {
                vrOperaceZaznamy1.get(row);

            }//konec try
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }//konec setValueAt

        public void oznamUpdateRadkyVybrane() {
            fireTableRowsUpdated(jTablePruvodkyTransakce1.getSelectedRow(), jTablePruvodkyTransakce1.getSelectedRow());
        }//konec oznamUpdateRadky

        public void oznamUpdateRadkyPozice(int pozice) {
            fireTableRowsUpdated(pozice, pozice);
        }//konec oznamUpdateRadky

        @Override
        public int getColumnCount() {
            return columnNames.length;
//        return vs.size();
        }//konec getRowCount*/

        @Override
        public int getRowCount() {
            if ((strojeTransakce.size() > 0) || (strojeTransakceNactene.size() > 0)) {
                return (vrOperaceZaznamy1.size() + vrOperaceZaznamyNactene1.size()) / (strojeTransakce.size() + strojeTransakceNactene.size());
            } else {
                return 0;
            }
        }//konec getRowCount

        @Override
        public String getColumnName(int col) {
            try {
                return columnNames[col];
            } catch (Exception ex) {
                return null;
            }
        }//konec getColumnName
    }

    protected class tabulkaModelVykresyNazvy1 extends AbstractTableModel {

        protected String[] columnNames = {"1", "2", "3", "4", "5", "6"};

        /*public void nastavSloupce(String[] sloupce) {
        columnNames = new String[sloupce.length];
        for(int i = 0; i < columnNames.length; i++) {
        columnNames[i] = sloupce[i];
        }
        }     */
        public void pridejSadu() {
            fireTableRowsInserted(0, jTablePruvodkyTransakce1.getColumnCount());
            //updateZaznamyProgram1();
            if (jTablePruvodkyTransakce1.getColumnCount() > 0) {
                jTableVykresyNazvy1.changeSelection(0, 0, false, false);
            }
        }

        public void oznamZmenu() {
            fireTableDataChanged();
//        if (vrPruvodka1size() > 0)
//        jTablePruvodkyPruvodky.changeSelection(0, 0, false, false);
        }//konec oznamZmenu

        @Override
        public Object getValueAt(int row, int col) {
            // System.out.println(vrOperaceZaznamy1.size() + " getValueAt transakce " + row + " x " + col);
            try {
                TridaZaznam1 aktivZazn = (TridaZaznam1) vrAktivniZaznamy1.get(col);
                if (aktivZazn.getCislo_vykresu().equals("01010101")) {
                    return "Údržba";
                } else {
                    return aktivZazn.getCislo_vykresu();
                }

            } catch (Exception e) {
                if (CisloVykresuLabel1.getText().equals("01010101")) {
                    return "Údržba";
                } else {
                    return CisloVykresuLabel1.getText();
                }
            }
        }//konec getValueAt

        @Override
        public void setValueAt(Object value, int row, int col) {
            // System.out.println("setValueAt PruvFram");
            /*
            if (col == 1) {
            ((Vector) vrOperaceZaznamy1.get(row)).setElementAt(value, col);
            fireTableCellUpdated(row, col);
            // nastavVyberTabulkyPruvodka1();
            return;
            } //konec if
            try {
            vrOperaceZaznamy1.get(row);
            
            }//konec try
            catch (Exception ex) {
            ex.printStackTrace();
            }*/
        }//konec setValueAt

        public void oznamUpdateRadkyVybrane() {
            fireTableRowsUpdated(jTableVykresyNazvy1.getSelectedRow(), jTableVykresyNazvy1.getSelectedRow());
        }//konec oznamUpdateRadky

        public void oznamUpdateRadkyPozice(int pozice) {
            fireTableRowsUpdated(pozice, pozice);
        }//konec oznamUpdateRadky

        @Override
        public int getColumnCount() {
            return jTablePruvodkyTransakce1.getColumnCount();
//        return vs.size();
        }//konec getRowCount*/

        @Override
        public int getRowCount() {
            return 1;
        }//konec getRowCount

        @Override
        public String getColumnName(int col) {
            try {
                return columnNames[col];
            } catch (Exception ex) {
                return null;
            }
        }//konec getColumnName
    }

    class LSLUdalosti implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            // System.out.println("List Selection Event : " + e.toString());
            if (e.getSource() == lsmStrojeDruhy1) {
                if (e.getValueIsAdjusting() == false) {
                    nastavVyberTabulkyStrojeDruhy1();
                }
            } else if (e.getSource() == lsmStroje1) {
                if (e.getValueIsAdjusting() == false) {
                    nastavVyberTabulkyStroje1();
                }
            } else if (e.getSource() == lsmTransakce1) {
                if (e.getValueIsAdjusting() == false) {
                    nastavVyberTabulkyTransakce1();
                }
            }//konec if (getSource ...)

        }//konec valueChanged
    } //konec LSLUdalosti

    class MyTableHeaderRenderer extends JLabel implements TableCellRenderer {

        String stroj;
        String vykres;

        public MyTableHeaderRenderer(String stroj, String vykres) {
            this.stroj = stroj;
            this.vykres = vykres;
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int rowIndex, int vColIndex) {
            setFont(new java.awt.Font("DejaVu Sans", 0, 18));
            setText(stroj);
            setToolTipText(vykres);

            return this;
        }
    }

    class MyTableNoHeaderRenderer extends JLabel implements TableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int rowIndex, int vColIndex) {

            return this;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ZamestnanecTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        PruvodkaTextField1 = new javax.swing.JTextField();
        udrzbaButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        getUdajeButton1 = new javax.swing.JButton();
        vsechnyStrojeButton1 = new javax.swing.JButton();
        SmazatButton1 = new javax.swing.JButton();
        RestartButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        CisloVykresuLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        NazevSoucastiLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTablePruvodkyStroje1 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePruvodkyStrojeDruhy1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        ZamestnanecLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablePruvodkyTransakce1 = new javax.swing.JTable();
        PotvrditButton1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableVykresyNazvy1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Mikron ovládací panel");

        ZamestnanecTextField1.setFont(new java.awt.Font("DejaVu Sans", 0, 15));
        ZamestnanecTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ZamestnanecTextField1KeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 0, 15));
        jLabel1.setText("Zaměstnanec :");

        PruvodkaTextField1.setFont(new java.awt.Font("DejaVu Sans", 0, 15));
        PruvodkaTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PruvodkaTextField1KeyPressed(evt);
            }
        });

        udrzbaButton1.setFont(new java.awt.Font("DejaVu Sans", 0, 20)); // NOI18N
        udrzbaButton1.setText("Údržba stroje");
        udrzbaButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                udrzbaButton1MousePressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 0, 15));
        jLabel2.setText("Průvodka :");

        getUdajeButton1.setFont(new java.awt.Font("DejaVu Sans", 0, 20)); // NOI18N
        getUdajeButton1.setText("Načíst údaje");
        getUdajeButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                getUdajeButton1MousePressed(evt);
            }
        });

        vsechnyStrojeButton1.setFont(new java.awt.Font("DejaVu Sans", 0, 20)); // NOI18N
        vsechnyStrojeButton1.setText("Všechny stroje");
        vsechnyStrojeButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                vsechnyStrojeButton1MousePressed(evt);
            }
        });

        SmazatButton1.setFont(new java.awt.Font("DejaVu Sans", 0, 20)); // NOI18N
        SmazatButton1.setText("Smazat");
        SmazatButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SmazatButton1MousePressed(evt);
            }
        });

        RestartButton1.setFont(new java.awt.Font("DejaVu Sans", 0, 20)); // NOI18N
        RestartButton1.setText("Restart");
        RestartButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                RestartButton1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PruvodkaTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ZamestnanecTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(getUdajeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(udrzbaButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(vsechnyStrojeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SmazatButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RestartButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(603, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(getUdajeButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                        .addComponent(udrzbaButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                        .addComponent(SmazatButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                        .addComponent(RestartButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                        .addComponent(vsechnyStrojeButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(ZamestnanecTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(PruvodkaTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15))
        );

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 1, 20));
        jLabel3.setText("Číslo výkresu :");

        CisloVykresuLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 20));
        CisloVykresuLabel1.setText("-------------");

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 1, 20));
        jLabel4.setText("Název součásti :");

        NazevSoucastiLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 20));
        NazevSoucastiLabel1.setText("-------------");

        jTablePruvodkyStroje1.setFont(new java.awt.Font("DejaVu Sans", 0, 20));
        jTablePruvodkyStroje1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTablePruvodkyStroje1.setRowHeight(60);
        jScrollPane3.setViewportView(jTablePruvodkyStroje1);

        jTablePruvodkyStrojeDruhy1.setFont(new java.awt.Font("DejaVu Sans", 0, 20));
        jTablePruvodkyStrojeDruhy1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTablePruvodkyStrojeDruhy1.setRowHeight(60);
        jScrollPane1.setViewportView(jTablePruvodkyStrojeDruhy1);

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 1, 20));
        jLabel5.setText("Zaměstnanec :");

        ZamestnanecLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 20));
        ZamestnanecLabel1.setText("--------------------------");

        jTablePruvodkyTransakce1.setFont(new java.awt.Font("DejaVu Sans", 0, 20)); // NOI18N
        jTablePruvodkyTransakce1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTablePruvodkyTransakce1.setRowHeight(150);
        jScrollPane2.setViewportView(jTablePruvodkyTransakce1);

        PotvrditButton1.setFont(new java.awt.Font("DejaVu Sans", 0, 20)); // NOI18N
        PotvrditButton1.setText("Potvrdit");
        PotvrditButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PotvrditButton1MousePressed(evt);
            }
        });

        jTableVykresyNazvy1.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        jTableVykresyNazvy1.setFont(new java.awt.Font("Tahoma", 0, 20));
        jTableVykresyNazvy1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableVykresyNazvy1.setRowHeight(40);
        jScrollPane4.setViewportView(jTableVykresyNazvy1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ZamestnanecLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(CisloVykresuLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(NazevSoucastiLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                            .addComponent(PotvrditButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE))))
                .addContainerGap(276, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(ZamestnanecLabel1)
                    .addComponent(jLabel3)
                    .addComponent(CisloVykresuLabel1)
                    .addComponent(jLabel4)
                    .addComponent(NazevSoucastiLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PotvrditButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void getUdajeButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_getUdajeButton1MousePressed
        if ((PruvodkaTextField1.getText().trim().length() > 0) && (ZamestnanecTextField1.getText().trim().length() > 0)) {
            pruvodka_id = Long.valueOf(PruvodkaTextField1.getText().trim()).longValue();
            zamestnanec_id = Integer.valueOf(ZamestnanecTextField1.getText().trim()).intValue();
            if (SQLFunkceObecne2.selectBooleanPole(
                    "SELECT EXISTS (SELECT pruvodka_kooperace_pruvodka_id FROM spolecne.pruvodka_kooperace "
                    + "WHERE pruvodka_kooperace_datum_prijeti IS NULL AND pruvodka_kooperace_pruvodka_id = " + pruvodka_id + ")") == false) {
                nactiPruvodku(true);
                nactiHlavicku(true);
                nactiTransakce(true);
                vsechnyStrojeButton1.setEnabled(true);
                vsechnyStrojeButton1.setVisible(true);
            } else {
                JednoducheDialogy1.warnAno(this, "Průvodka na kooperaci", "Nelze pokračovat s výrobou, průvodka ještě nebyla přijata z kooperace");
            }

        }
        if ((PruvodkaTextField1.getText().trim().length() == 0) && (ZamestnanecTextField1.getText().trim().length() > 0)) {
            zamestnanec_id = Integer.valueOf(ZamestnanecTextField1.getText().trim()).intValue();
            nactiHlavicku(
                    false);
            nactiTransakce(
                    false);

        }
    }//GEN-LAST:event_getUdajeButton1MousePressed

    private void udrzbaButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_udrzbaButton1MousePressed
        vsechnyStrojeButton1.setEnabled(true);
        vsechnyStrojeButton1.setVisible(true);
        udrzbaStroje();
    }//GEN-LAST:event_udrzbaButton1MousePressed
    private void vsechnyStrojeButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vsechnyStrojeButton1MousePressed
        nactiVsechnyStroje();
    }//GEN-LAST:event_vsechnyStrojeButton1MousePressed
    private void SmazatButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SmazatButton1MousePressed
        vycistitPanel();
    }//GEN-LAST:event_SmazatButton1MousePressed
    private void PotvrditButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PotvrditButton1MousePressed
        ulozTransakce();

    }//GEN-LAST:event_PotvrditButton1MousePressed
    private void ZamestnanecTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ZamestnanecTextField1KeyPressed
        // TODO add your handling code here:
        int key = evt.getKeyCode();

        if (key == KeyEvent.VK_ENTER) {
            if (SQLFunkceObecne2.selectBooleanPole(
                    "SELECT EXISTS (SELECT zamestnanci_id FROM spolecne.zamestnanci WHERE zamestnanci_id = " + ZamestnanecTextField1.getText().trim() + ")") == true) {
                PruvodkaTextField1.requestFocus();

            } else {
                ZamestnanecTextField1.setText("");
                ZamestnanecTextField1.requestFocus();

            }
        }
    }//GEN-LAST:event_ZamestnanecTextField1KeyPressed

    private void PruvodkaTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PruvodkaTextField1KeyPressed
        int key = evt.getKeyCode();

        if (key == KeyEvent.VK_ENTER) {
            if ((PruvodkaTextField1.getText().trim().length() > 0) && (ZamestnanecTextField1.getText().trim().length() > 0)) {
                if (SQLFunkceObecne2.selectBooleanPole(
                        "SELECT EXISTS (SELECT pruvodky_id FROM spolecne.pruvodky WHERE pruvodky_id = " + PruvodkaTextField1.getText().trim() + ")") == true) {

                    pruvodka_id = Long.valueOf(PruvodkaTextField1.getText().trim()).longValue();
                    zamestnanec_id = Integer.valueOf(ZamestnanecTextField1.getText().trim()).intValue();
                    if (SQLFunkceObecne2.selectBooleanPole(
                            "SELECT EXISTS (SELECT pruvodka_kooperace_pruvodka_id FROM spolecne.pruvodka_kooperace "
                            + "WHERE pruvodka_kooperace_datum_prijeti IS NULL AND pruvodka_kooperace_pruvodka_id = " + pruvodka_id + ")") == false) {
                        nactiPruvodku(
                                true);
                        nactiHlavicku(
                                true);
                        nactiTransakce(
                                true);
                        vsechnyStrojeButton1.setEnabled(true);
                        vsechnyStrojeButton1.setVisible(true);

                    } else {
                        JednoducheDialogy1.warnAno(this, "Průvodka na kooperaci", "Nelze pokračovat s výrobou, průvodka ještě nebyla přijata z kooperace");
                    }

                } else {
                    PruvodkaTextField1.setText("");
                    PruvodkaTextField1.requestFocus();

                }
            }
            /* if ((PruvodkaTextField1.getText().trim().length() == 0) && (ZamestnanecTextField1.getText().trim().length() > 0)) {
            zamestnanec_id = Integer.valueOf(ZamestnanecTextField1.getText().trim()).intValue();
            nactiHlavicku(false);
            nactiTransakce(false);
            }*/
        }

    }//GEN-LAST:event_PruvodkaTextField1KeyPressed

    private void RestartButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RestartButton1MousePressed
        Main.restart();
    }//GEN-LAST:event_RestartButton1MousePressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CisloVykresuLabel1;
    private javax.swing.JLabel NazevSoucastiLabel1;
    private javax.swing.JButton PotvrditButton1;
    private javax.swing.JTextField PruvodkaTextField1;
    private javax.swing.JButton RestartButton1;
    private javax.swing.JButton SmazatButton1;
    private javax.swing.JLabel ZamestnanecLabel1;
    private javax.swing.JTextField ZamestnanecTextField1;
    private javax.swing.JButton getUdajeButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTablePruvodkyStroje1;
    private javax.swing.JTable jTablePruvodkyStrojeDruhy1;
    private javax.swing.JTable jTablePruvodkyTransakce1;
    private javax.swing.JTable jTableVykresyNazvy1;
    private javax.swing.JButton udrzbaButton1;
    private javax.swing.JButton vsechnyStrojeButton1;
    // End of variables declaration//GEN-END:variables
}
