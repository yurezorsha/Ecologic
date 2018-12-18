package Models;

import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;


public class MyDocumentFilter extends DocumentFilter {
	final int maxCharacters = 20;
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
    	String text = fb.getDocument().getText(0,
                fb.getDocument().getLength());
        text += string;
        if ((fb.getDocument().getLength() + string.length()) <= maxCharacters
                && text.matches("^[0-9]+[.]?[0-9]{0,10}$")) {
            super.insertString(fb, offset, string, attr);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }
    
    @Override
    public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attrs) throws BadLocationException {
    	String text = fb.getDocument().getText(0,
                fb.getDocument().getLength());
        text += string;
        if ((fb.getDocument().getLength() + string.length()) <= maxCharacters
                && text.matches("^[0-9]+[.]?[0-9]{0,10}$")) {
            super.replace(fb, offset,length, string, attrs);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }
}
