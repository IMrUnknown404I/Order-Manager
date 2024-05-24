package mngr.renders_and_editors;

import mngr.TableMethods;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * The Render class for Active orders' cells
 * @author MrUnknown404
 */
public class MainTableRenderer extends DefaultTableCellRenderer {
    private final Border IS_FOCUSED = new BevelBorder(1);
    private final Border OUT_OF_FOCUS = new EmptyBorder(1, 1, 1, 1);

    @Override
    @SuppressWarnings("empty-statement")
    public Component getTableCellRendererComponent(JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {
        
        setHorizontalAlignment(CENTER);
        
        // setting up colors according data difference
        if (isSelected) {
            setBackground(Color.cyan);
        } else {
            if (column == 0) {
                int daysDif = TableMethods.getDaysDiff((String) value);
                if (daysDif < 0 && daysDif > -6) {
                    setBackground(new Color(208, 208, 208)); //серый   192, 192, 192  224, 224, 224
                } else if (daysDif <= -6) {
                    setBackground(Color.orange); //банан   255, 255, 153 new Color(250, 231, 182)
                } else if (daysDif <= 3) {
                    setBackground(new Color(255, 204, 204)); //красный   255, 153, 153
                } else if (daysDif <= 7) {
                    setBackground(new Color(255, 255, 204)); //жёлтый   255, 255, 153
                } else {
                    setBackground(Color.WHITE);
                }
            }
        }
        
        // setting up a border when the cell is selected
        if (hasFocus && isSelected) {
            setBorder(IS_FOCUSED);
        } else
            setBorder(OUT_OF_FOCUS);
        
        // setting the out and in of description column cells
        if(column==3){
            setText((value == null) ? "" : getFormattedText(value.toString()));
        } else
            setText((value == null) ? "" : value.toString());
        
        return this;
    }
    
    /**
     * Getter for formatted text
     * @param text text to format
     * @return formatted text
     */
    private String getFormattedText(String text){
        String formattedText = text;
        return formattedText.replaceAll("\n", " \n");
    }
}
