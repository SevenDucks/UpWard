package eu.wauz.upward.util;

import java.awt.Font;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * An output stream, that is bound to a text area, used for displaying  runtime logs or similar.
 * 
 * @author Wauzmons
 */
public class TextAreaOutputStream extends OutputStream {

    /**
     * The text area control, to which the output will be redirected to.
     */
    private final JTextArea textAreaControl;

    /**
     * Creates a new output stream which writes to a text area control.
     *
     * @param useAsDefault If the new stream should be the default one, used for sysout etc.
     */
    public TextAreaOutputStream(final boolean useAsDefault) {
        textAreaControl = new JTextArea();
        textAreaControl.setEditable(false);
        textAreaControl.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        textAreaControl.setBorder(BorderFactory.createCompoundBorder(textAreaControl.getBorder(), BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        if(useAsDefault) {
            final PrintStream out = new PrintStream(this);
            System.setOut(out);
            System.setErr(out);
        }
    }

    /**
     * Writes the specified byte as a character to the text area.
     *
     * @param b The byte to be written as character to the text area.
     */
    @Override
    public void write(final int b) throws IOException {
        textAreaControl.append(String.valueOf((char) b));
    }

    /**
     * @return The text area control, to which the output will be redirected to.
     */
    public JTextArea getTextControl() {
        return textAreaControl;
    }

    /**
     * @return A newly created scroll pane, containing the text area control.
     */
    public JScrollPane getScrollPane() {
        return new JScrollPane(textAreaControl, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    }

}
