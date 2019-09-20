/*
 * TridaPruvodka1.java
 *
 * Created on 8/12/2009, 19:17
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mikronpanel;

import java.sql.Date;
import java.util.Vector;

/**
 *
 * @author dave
 */
public class TridaPruvodka1 {

   // protected boolean vyber = false;
    protected long pruvodky_id;
    protected String  pruvodky_nazev;
    protected int pruvodky_cislo_vykresu;
    protected Date pruvodky_termin_dokonceni;
    protected int pruvodky_pocet_kusu_polotovar;
    protected int pruvodky_pocet_kusu;
    protected int pruvodky_pocet_kusu_sklad;
    protected int  pruvodky_polotovar_id;   
    protected String poznamky;  

    private Vector v;
    private java.text.DateFormat df = java.text.DateFormat.getDateInstance();


    public TridaPruvodka1() {
    this.pruvodky_id = 0;
    this.pruvodky_nazev = "";
    this.pruvodky_cislo_vykresu = 0;
    this.pruvodky_termin_dokonceni= null;
    this.pruvodky_pocet_kusu_polotovar = 0;
    this.pruvodky_pocet_kusu = 0;
    this.pruvodky_pocet_kusu_sklad = 0;
    this.pruvodky_polotovar_id = 0;
    this.poznamky = "";
   
    }

    public TridaPruvodka1(TridaPruvodka1 t) {
    this.pruvodky_id = t.pruvodky_id;
    this.pruvodky_nazev = t.pruvodky_nazev;
    this.pruvodky_cislo_vykresu = t.pruvodky_cislo_vykresu;
    this.pruvodky_termin_dokonceni = t.pruvodky_termin_dokonceni;
    this.pruvodky_pocet_kusu = t.pruvodky_pocet_kusu;
      this.pruvodky_pocet_kusu_polotovar = t.pruvodky_pocet_kusu_polotovar;
    this.pruvodky_pocet_kusu_sklad = t.pruvodky_pocet_kusu_sklad;
    this.pruvodky_polotovar_id = t.pruvodky_polotovar_id;  
    this.poznamky = t.poznamky;  
    }
    
    public TridaPruvodka1(long pruvodky_id, int pruvodky_cislo_vykresu, Date pruvodky_termin_dokonceni,int pruvodky_pocet_kusu, int pruvodky_pocet_kusu_polotovar,String pruvodky_nazev,
            int pruvodky_pocet_kusu_sklad, int pruvodky_polotovar_id, String poznamky) {
    this.pruvodky_id = pruvodky_id;
    this.pruvodky_nazev = pruvodky_nazev;
    this.pruvodky_cislo_vykresu = pruvodky_cislo_vykresu;
    this.pruvodky_termin_dokonceni = pruvodky_termin_dokonceni;
    this.pruvodky_pocet_kusu_polotovar = pruvodky_pocet_kusu_polotovar;
    this.pruvodky_pocet_kusu = pruvodky_pocet_kusu;
    this.pruvodky_pocet_kusu_sklad = pruvodky_pocet_kusu_sklad;
    this.pruvodky_polotovar_id = pruvodky_polotovar_id;    
    this.poznamky = poznamky;
    
    }    

   /* protected boolean selectData(long id) {
    v = SQLFunkceObecne2.selectVektor("SELECT * FROM spolecne.pruvodky WHERE pruvodky_id = " + id);   
    if (v.isEmpty() == true)
        return false;
    
    this.pruvodky_id = ((Long)v.get(0)).longValue();    
   // this.pruvodky_nazev = TextFunkce1.osetriCteniTextDB1(v.get(1) == null ? "" : v.get(1).toString());
    this.pruvodky_nazev = (String) v.get(1);
    this.pruvodky_cislo_vykresu = ((Integer)v.get(2)).intValue();
      this.pruvodky_termin_dokonceni = (java.sql.Date)v.get(3);    
    this.pruvodky_pocet_kusu = ((Integer)v.get(4)).intValue();
    //this.poznamky = TextFunkce1.osetriCteniTextDB1(v.get(5) == null ? "" : v.get(5).toString());
    this.poznamky = (String) v.get(5);
    this.pruvodky_pocet_kusu_sklad = ((Integer)v.get(6)).intValue();
    this.pruvodky_pocet_kusu_polotovar = ((Integer)v.get(7)).intValue();   
    this.pruvodky_polotovar_id = ((Integer)v.get(8)).intValue();
    System.out.println("pruvodky nazev " + this.pruvodky_nazev);
    return true;
    }*/

    
   /* protected boolean selectData() {
      return selectData(this.pruvodky_id);
    }*/
    
    
  /*  protected boolean updateData() {
    int vu = SQLFunkceObecne2.update("UPDATE spolecne.pruvodky SET " +
    "pruvodky_id = " + TextFunkce1.osetriZapisLongKeyDB1(this.pruvodky_id) + ", " +
    "pruvodky_nazev = " + TextFunkce1.osetriZapisTextDB1(this.pruvodky_nazev) + ", " +
    "pruvodky_cislo_vykresus = " + TextFunkce1.osetriZapisIntKeyDB1(this.pruvodky_cislo_vykresu) + ", " +
    "pruvodky_termin_dokonceni = " + TextFunkce1.osetriDatum(this.pruvodky_termin_dokonceni.toString()) + ", " +
    "pruvodky_pocet_kusu_polotovar = " + TextFunkce1.osetriZapisIntKeyDB1(this.pruvodky_pocet_kusu_polotovar) + ", " +
    "pruvodky_pocet_kusu = " + TextFunkce1.osetriZapisIntKeyDB1(this.pruvodky_pocet_kusu) + ", " +
    "pruvodky_pocet_kusu_sklad = " + TextFunkce1.osetriZapisIntKeyDB1(this.pruvodky_pocet_kusu_sklad) + ", " +
    "pruvodky_polotovar_id = " + TextFunkce1.osetriZapisIntKeyDB1(this.pruvodky_polotovar_id) + ", " +
    "poznamky = " + TextFunkce1.osetriZapisTextDB1(this.poznamky) + ", " +   
    "WHERE pruvodky_id = " + this.pruvodky_id
    );
    
    if (vu > 0) {
     String chyba = SQLFunkceObecne2.getCHybovouHlasku(vu);
     JednoducheDialogy1.errAno(MikronISApp.ramecAplikace, "Chyba při aktualizaci dat bankovní transakce", chyba);
     return false;
    }
    return true;
    }*/

    
}// konec tridy
