package org.simplecoding.jwalk.exceptions;

/**
 *
 * @author fred
 */
public class MissingMethodArgumentException
    extends
        MethodAccessingException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of <code>MethodAccessingException</code> without detail message.
     */
    public MissingMethodArgumentException() {
    }

    /**
     * Constructs an instance of <code>MethodAccessingException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public MissingMethodArgumentException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>MethodAccessingException</code> with the caused by exception.
     * @param e the exception.
     */
    public MissingMethodArgumentException(Exception e) {
        super(e);
    }
}
