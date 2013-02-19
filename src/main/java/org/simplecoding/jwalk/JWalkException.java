package org.simplecoding.jwalk;

/**
 *
 * @author fred
 */
public class JWalkException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of <code>JWalkException</code> without detail message.
     */
    public JWalkException() {
    }

    /**
     * Constructs an instance of <code>JWalkException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public JWalkException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>JWalkException</code> with the caused by exception.
     * @param e the exception.
     */
    public JWalkException(Exception e) {
        super(e);
    }
}
