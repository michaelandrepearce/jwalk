package org.simplecoding.jwalk.exceptions;

/**
 *
 * @author fred
 */
public class MethodAccessingException
    extends
        JWalkException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of <code>MethodAccessingException</code> without detail message.
     */
    public MethodAccessingException() {
    }

    /**
     * Constructs an instance of <code>MethodAccessingException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public MethodAccessingException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>MethodAccessingException</code> with the caused by exception.
     * @param e the exception.
     */
    public MethodAccessingException(Exception e) {
        super(e);
    }
}
