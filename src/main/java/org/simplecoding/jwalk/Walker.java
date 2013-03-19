package org.simplecoding.jwalk;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import org.simplecoding.jwalk.components.WalkSequence;
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
                    WalkFactory.getInstance()
                        .createSequence(expression),
                    new HashMap<String, Object>(4));
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

    public Object evaluate(Object instance, WalkSequence sequence, Map<String, Object> map)
        throws
            JWalkException {

        return
            sequence.evaluate(
                instance,
                map);
    }

    @SuppressWarnings("unchecked")
    public <T> T evaluate(Object instance, WalkSequence sequence, Class<T> cast, Map<String, Object> map)
        throws
            JWalkException {

        return (T) this.evaluate(instance, sequence, map);
    }

    /* -------------------------------------------------------------------------------------------------------------- *
     * Private methods
     * -------------------------------------------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------------------------------------------- *
     * Getters & Setters
     * -------------------------------------------------------------------------------------------------------------- */

}
