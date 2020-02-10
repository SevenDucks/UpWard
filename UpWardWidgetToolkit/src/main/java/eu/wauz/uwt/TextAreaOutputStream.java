package eu.wauz.uwt;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.BorderFactory;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

/**
 * An output stream, that is bound to a text area, used for displaying  runtime logs or similar.
 * 
 * @author Wauzmons
 */
public class TextAreaOutputStream extends OutputStream {

    /**
     * The text area, to which the output will be redirected.
     */
    private final JTextArea textArea;
    
    /**
     * The scroll pane, containing the text area.
     */
    private final JScrollPane scrollPane;

    /**
     * Creates a new output stream which writes to a text area control.
     *
     * @param useAsDefault If the new stream should be the default one, used for sysout etc.
     */
    public TextAreaOutputStream(final boolean useAsDefault) {
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        textArea.setForeground(Color.WHITE);
        textArea.setBackground(Color.BLACK);
        
        int vertical = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS;
        int horizontal = JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS;
        scrollPane = new JScrollPane(textArea, vertical, horizontal);

        if(useAsDefault) {
            final PrintStream out = new PrintStream(this);
            System.setOut(out);
            System.setErr(out);
        }
    }

    /**
     * Writes the specified byte as a character to the text area.
     * The text area can hold a maximum of 5K lines.
     *
     * @param b The byte to be written as character to the text area.
     */
    @Override
    public void write(final int b) throws IOException {
    	
        textArea.append(String.valueOf((char) b));
        if(textArea.getLineCount() > 5000) {
        	try {
    	        int endIndex = textArea.getLineEndOffset(0);
    			textArea.replaceRange("", 0, endIndex);
            }
            catch (BadLocationException e) {
            	e.printStackTrace();
            }
        }
        
        JScrollBar vertical = scrollPane.getVerticalScrollBar();
        if(true) {
        	vertical.setValue(vertical.getMaximum());
        }
    }

    /**
     * @return The text area, to which the output will be redirected.
     */
    public JTextArea getTextArea() {
        return textArea;
    }

    /**
     * @return The scroll pane, containing the text area.
     */
    public JScrollPane getScrollPane() {
        return scrollPane;
    }

}
