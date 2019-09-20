package mikronpanel;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @version 1.0 11/09/98
 */
public class TableVyberRenderer1 extends JButton implements TableCellRenderer {

    private boolean[][] index;

  /*  public TableVyberRenderer1() {
        setOpaque(true);
    }*/

    public TableVyberRenderer1(boolean[][] index) {
        setOpaque(true);
        this.index = index;
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

    //   System.out.println("TVREND " + row + " x " + column + " x " + index.length + " x " + index[0].length );
     //  System.out.println("TVRE1 " + this.index[row][column]);
        if (this.index[row][column] == true) {
           setForeground(Color.red);
            setBackground(Color.BLUE);
        } else {
           setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));
        }

        if ((isSelected == true) && ( (this.index[row][column] == true))) {
            setForeground(Color.red);
            setBackground(Color.BLUE);
          // setForeground(Color.BLACK);
           // setBackground(javax.swing.UIManager.getDefaults().getColor("InternalFrame.activeTitleGradient"));
        }


      /*  if (isSelected) {
            // button.setForeground(table.getSelectionForeground());
            //button.setBackground(table.getSelectionBackground());
            setForeground(Color.red);
            setBackground(Color.BLUE);
        } else {
            setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));
        }*/
        setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        setText((value == null) ? "" : value.toString());
        return this;
    }
}
/*public class TableVyberRenderer1 extends JToggleButton implements TableCellRenderer {

public TableVyberRenderer1() {
setOpaque(true);
}

public Component getTableCellRendererComponent(JTable table, Object value,
boolean isSelected, boolean hasFocus, int row, int column) {
if (isSelected) {
setForeground(table.getSelectionForeground());
setBackground(table.getSelectionBackground());
} else{
setForeground(table.getForeground());
setBackground(UIManager.getColor("Button.background"));
}
setText( (value ==null) ? "" : value.toString() );
return this;
}
}*/
