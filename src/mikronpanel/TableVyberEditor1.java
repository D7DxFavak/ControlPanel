package mikronpanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @version 1.0 11/09/98
 */
public class TableVyberEditor1 extends DefaultCellEditor {
  protected JButton button;
  private String    label;
  private boolean   isPushed;

  public TableVyberEditor1(JCheckBox checkBox) {
    super(checkBox);
    button = new JButton();
    button.setOpaque(true);
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
      }
    });
  }

  public Component getTableCellEditorComponent(JTable table, Object value,
                   boolean isSelected, int row, int column) {
    /*if (isSelected) {
     // button.setForeground(table.getSelectionForeground());
      //button.setBackground(table.getSelectionBackground());
         button.setForeground(Color.red);
      button.setBackground(Color.BLUE);
    } else{
      button.setForeground(table.getForeground());
      button.setBackground(table.getBackground());
    }*/
    label = (value ==null) ? "" : value.toString();
    button.setText( label );
    isPushed = true;
    return button;
  }

  public Object getCellEditorValue() {
    if (isPushed)  {
      //
      //
      JOptionPane.showMessageDialog(button ,label + ": Ouch!");
      // System.out.println(label + ": Ouch!");
    }
    isPushed = false;
    return new String( label ) ;
  }

  public boolean stopCellEditing() {
    isPushed = false;
    return super.stopCellEditing();
  }

  protected void fireEditingStopped() {
    super.fireEditingStopped();
  }
}


/*

public class TableVyberEditor1 extends DefaultCellEditor {
  protected JToggleButton button;
  private String    label;
  private boolean   isPushed;

  public TableVyberEditor1(JCheckBox checkbox) {
    super(checkbox);
    button = new JToggleButton();
    button.setOpaque(true);
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
      }
    });
  }

  public Component getTableCellEditorComponent(JTable table, Object value,
                   boolean isSelected, int row, int column) {
    if (isSelected) {
      button.setForeground(table.getSelectionForeground());
      button.setBackground(table.getSelectionBackground());
    } else{
      button.setForeground(table.getForeground());
      button.setBackground(table.getBackground());
    }
    label = (value ==null) ? "" : value.toString();
    button.setText( label );
    isPushed = true;
    return button;
  }

  public Object getCellEditorValue() {
    if (isPushed)  {
      //
      //
      JOptionPane.showMessageDialog(button ,label + ": Ouch!");
      // System.out.println(label + ": Ouch!");
    }
    isPushed = false;
    return new String( label ) ;
  }

  public boolean stopCellEditing() {
    isPushed = false;
    return super.stopCellEditing();
  }

  protected void fireEditingStopped() {
    super.fireEditingStopped();
  }
}*/