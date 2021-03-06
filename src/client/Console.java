package client;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.AdjustmentEvent;
import java.text.SimpleDateFormat;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import io.Logger;

@SuppressWarnings("serial")
public class Console extends JList<Object> {

    private JScrollPane scroller;
    private JTextArea area;
    private static DefaultListModel<Object> model = new DefaultListModel<Object>();
    public static final Rectangle BOTTOM_OF_WINDOW = new Rectangle(0, Integer.MAX_VALUE, 0, 0);
    private static Formatter formatter = new Formatter() {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");

        @Override
        public String format(final LogRecord record) {
            String name = record.getSourceClassName();
            if (name == null || name.equals("null"))
                name = "System:";
            final int max = 16;
            final String append = "...";

            return String.format("%s  %-" + max + "s %s","[" + dateFormat.format(record.getMillis()) + "]",name.length() > max ? name.substring(0, max - append.length()) : name, record.getMessage());
        }
    };

    public Console() {
        super();
        setModel(model);
        setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        setFont(new Font("Arial", Font.PLAIN, 12));
        setAutoscrolls(true);
        setCellRenderer(new Renderer());
        scroller = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroller.setBorder(null);
        scroller.setPreferredSize(new Dimension(1000, 350));
    }

    public void log(final LogRecord record) {
        JScrollBar bar = scroller.getVerticalScrollBar();
        boolean end = bar.getMaximum() == bar.getValue() + bar.getVisibleAmount();
        model.addElement(new ConsoleEntry(record));
        ensureIndexIsVisible(model.size() - 1);
       // window.revalidate(); //Update the scrollbar size
        JScrollBar vertical = scroller.getVerticalScrollBar();
        //Logger.log("Max "+vertical.getMaximum()+" Cur: "+vertical.getValue());
        vertical.setValue(vertical.getMaximum());
        //
      ///  if (end) {
        	//System.out.println("End");
            //scrollRectToVisible(BOTTOM_OF_WINDOW);
           // scroller.scrollRectToVisible(BOTTOM_OF_WINDOW);
       // }

  /*      SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JScrollBar bar = scroller.getVerticalScrollBar();
                boolean end = bar.getMaximum() == bar.getValue()
                        + bar.getVisibleAmount();
                model.addElement(new ConsoleEntry(record));
                ensureIndexIsVisible(model.size() - 1);
                
                if (end) {
                    scrollRectToVisible(BOTTOM_OF_WINDOW);
                    scroller.scrollRectToVisible(BOTTOM_OF_WINDOW);
                }

            }
        });*/
    }

    public JScrollPane createScrollPane() {
        return scroller;
    }

    private static class ConsoleEntry {

        private LogRecord record;
        private String formatted;

        public ConsoleEntry(LogRecord record) {
            this.record = record;
            formatted = Console.formatter.format(record);
        }

        public String toString() {
            return Console.formatter.format(record);
        }
    }

    private static class Renderer implements ListCellRenderer<Object> {

        private Border EMPTY_BORDER = new EmptyBorder(1, 1, 1, 1);;
        private Border SELECTED_BORDER = BorderFactory.createLineBorder(Color.blue, 1);;

        @Override
        public Component getListCellRendererComponent(JList<?> list,
                Object value, int index, boolean isSelected,
                boolean cellHasFocus) {
            if (!(value instanceof ConsoleEntry)) {
                return new JLabel();
            }
            ConsoleEntry ce = (ConsoleEntry) value;
            JTextArea jta = new JTextArea(ce.formatted);
            DefaultCaret caret = (DefaultCaret)jta.getCaret();
            caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
            jta.setComponentOrientation(list.getComponentOrientation());
            jta.setFont(list.getFont());
            jta.setBorder(!cellHasFocus && !isSelected ? EMPTY_BORDER : SELECTED_BORDER);
            if (ce.record.getLevel() == Level.SEVERE || ce.record.getLevel() == Level.WARNING) {
                jta.setForeground(Color.red.darker());
            }
            return jta;
        }
    }
}