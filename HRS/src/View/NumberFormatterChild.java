package View;

import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.text.NumberFormatter;

public class NumberFormatterChild extends NumberFormatter {
    NumberFormatterChild(NumberFormat format) {
        super(format);
    }

    @Override
    public Object stringToValue(String text) throws ParseException {
        if (text.length() == 0) {
            return null;
        }
        // TODO Auto-generated method stub
        return super.stringToValue(text);
    }
}
