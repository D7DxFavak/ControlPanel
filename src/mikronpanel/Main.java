/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mikronpanel;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

/**
 *
 * @author 7Data Gotzy
 */
public class Main {

    protected static PripojeniDB pripojeniDB;
    protected static GraphicsEnvironment ge;
    protected static GraphicsDevice gd;
    protected static GraphicsConfiguration gc;
    protected static PanelFrame panel;
    private static Thread t;
    private static boolean refresh;
    private static final int refreshPolozek = 5000;
    private static int resetCount;
    private static boolean isBackup;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        pripojeniDB = new PripojeniDB();
        String[] argsa = {"", "", ""};
        
        int rc = pripojeniDB.navazSpojeniDB(argsa[0], argsa[1], argsa[2]);

        /*  if (rc == 1) {
        JednoducheDialogy1.errAno(new JPanel(), "Selhání připojení",
        "Selhalo připojení k databázi. Pravděpodobně bylo zadáno chybné jméno nebo heslo\n" +
        "nebo byl detekován pokus o současné navázání více než jednoho spojení k databázi");
        return;
        }*/

        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        gd = ge.getDefaultScreenDevice();
        gc = gd.getDefaultConfiguration();
        panel = new PanelFrame();
        resetCount = 0;
        refresh = true;
        isBackup = false;
        kontrolaSpojeni();
        //login();
        //show(new MikronISView(this));
    }

    private static void kontrolaSpojeni() {
        t = new Thread(new Runnable() {

            public void run() {
                while (refresh == true) {
                    try {
                        Thread.sleep(refreshPolozek);
                        if (!PripojeniDB.jePlatne()) {
                            panel.setTitle("Mikron ovládací panel - odpojeno od DB");
                            //System.out.println("bad");
                            resetCount++;
                            if (resetCount < 5) {
                                restart();
                            } else {
                                isBackup  = true;
                                restart();
                            }
                        } else {
                            if (isBackup == false) {
                                resetCount = 0;
                                //System.out.println("ok");
                                panel.setTitle("Mikron ovládací panel - připojeno k DB");
                            } else {
                                mainServerTest();
                                //System.out.println("Main server test");
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }//konec while

            }//konec run
        });
        t.start();

    }
    
    public static void mainServerTest() { 
        String[] argsa = {"", "", ""};
        argsa[0] = "jdbc:postgresql://192.168.1.100:54325/mikron1?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
        //argsa[0] = "jdbc:postgresql://server1.mikron-plzen.cz:54325/mikron1?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
        argsa[1] = "favak";
        argsa[2] = "favak";
        
        PripojeniDB pripojeniTest = new PripojeniDB();
        int rc = pripojeniTest.navazSpojeniDB(argsa[0], argsa[1], argsa[2]);
        if(pripojeniTest.jePlatne()) {
            pripojeniTest.uzavriSpojeniDB();
            isBackup = false;
            restart();
        }
    }

    public static void restart() {

        pripojeniDB.uzavriSpojeniDB();
        panel.removeAll();
        panel.dispose();

        String[] argsa = {"", "", ""};
        if(!isBackup) {
            argsa[0] = "jdbc:postgresql://192.168.1.100:54325/mikron1?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
        } else {
            argsa[0] = "jdbc:postgresql://192.168.1.100:54325/mikron1?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
        }
        //argsa[0] = "jdbc:postgresql://192.168.1.100:54325/mikron1?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
        argsa[1] = "favak";
        argsa[2] = "favak";
        int rc = pripojeniDB.navazSpojeniDB(argsa[0], argsa[1], argsa[2]);
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        gd = ge.getDefaultScreenDevice();
        gc = gd.getDefaultConfiguration();
        panel = new PanelFrame();
    }
}
