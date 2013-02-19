package org.simplecoding.jwalk;

/**
 *
 * @author fred
 */
public class FieldAccessingException
    extends
        JWalkException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of <code>FieldAccessingException</code> without detail message.
     */
    public FieldAccessingException() {
    }

    /**
     * Constructs an instance of <code>FieldAccessingException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public FieldAccessingException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>FieldAccessingException</code> with the caused by exception.
     * @param e the exception.
     */
    public FieldAccessingException(Exception e) {
        super(e);
    }
}
