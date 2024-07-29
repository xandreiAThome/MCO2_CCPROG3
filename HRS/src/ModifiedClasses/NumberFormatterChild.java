package ModifiedClasses;

import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.text.NumberFormatter;

/**
 * This class is a child class of NumberFormatter and provides additional
 * functionality for formatting and parsing numbers.
 */
public class NumberFormatterChild extends NumberFormatter {
    /**
     * Constructs a NumberFormatterChild object with the specified number format.
     * 
     * @param format the number format to be used for formatting and parsing numbers
     */
    public NumberFormatterChild(NumberFormat format) {
        super(format);
    }

    /**
     * Converts the given string representation of a number into its corresponding
     * object representation.
     * 
     * @param text the string representation of the number
     * @return the object representation of the number
     * @throws ParseException if the string cannot be parsed into a number
     */
    @Override
    public Object stringToValue(String text) throws ParseException {
        if (text.length() == 0) {
            return null;
        }
        // TODO Auto-generated method stub
        return super.stringToValue(text);
    }
}
