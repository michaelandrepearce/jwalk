package org.simplecoding.jwalk;

/**
 *
 * @author fred
 */
public class OFlexException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of <code>OFlexException</code> without detail message.
     */
    public OFlexException() {
    }

    /**
     * Constructs an instance of <code>OFlexException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public OFlexException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>OFlexException</code> with the caused by exception.
     * @param e the exception.
     */
    public OFlexException(Exception e) {
        super(e);
    }
}
