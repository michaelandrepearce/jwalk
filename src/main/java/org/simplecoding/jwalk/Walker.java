package org.simplecoding.jwalk;

import java.util.Arrays;
import java.util.LinkedList;
import org.simplecoding.jwalk.components.ComponentFactory;
import org.simplecoding.jwalk.components.XSequence;
import org.simplecoding.jwalk.exceptions.JWalkException;

/**
 *
 * @author fred
 */
public class Walker {

    /* -------------------------------------------------------------------------------------------------------------- *
     * Private Static Fields
     * -------------------------------------------------------------------------------------------------------------- */
    private static final class SingletonHolder {
        private static final Walker INSTANCE = new Walker();
    }

    /* -------------------------------------------------------------------------------------------------------------- *
     * Private Fields
     * -------------------------------------------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------------------------------------------- *
     * Constructor
     * -------------------------------------------------------------------------------------------------------------- */
    private Walker() {}

    public static Walker getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /* -------------------------------------------------------------------------------------------------------------- *
     * Lifecycle methods
     * -------------------------------------------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------------------------------------------- *
     * Public methods
     * -------------------------------------------------------------------------------------------------------------- */
    public Object evaluate(Object instance, String expression)
        throws
            JWalkException {

        try {
            return
                this.evaluate(
                    instance,
                    ComponentFactory.getInstance()
                        .createXSequence(expression));
        }
        catch(JWalkException e) {
            throw e;
        }
        catch(Exception e) {
            throw new JWalkException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T evaluate(Object instance, String expression, Class<T> cast)
        throws
            JWalkException {

        return (T) this.evaluate(instance, expression);
    }

    public Object evaluate(Object instance, XSequence sequence, Object... arguments)
        throws
            JWalkException {

        return
            sequence.evaluate(
                instance,
                new LinkedList<Object>(
                    Arrays.asList(arguments)));
    }

    @SuppressWarnings("unchecked")
    public <T> T evaluate(Object instance, XSequence sequence, Class<T> cast, Object... arguments)
        throws
            JWalkException {

        return (T) this.evaluate(instance, sequence, arguments);
    }

    /* -------------------------------------------------------------------------------------------------------------- *
     * Private methods
     * -------------------------------------------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------------------------------------------- *
     * Getters & Setters
     * -------------------------------------------------------------------------------------------------------------- */

}
