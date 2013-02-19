package org.simplecoding.jwalk;

import org.simplecoding.jwalk.components.ComponentFactory;

/**
 *
 * @author fred
 */
public class Walker {

    /* -------------------------------------------------------------------------------------------------------------- *
     * Private Static Fields
     * -------------------------------------------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------------------------------------------- *
     * Private Fields
     * -------------------------------------------------------------------------------------------------------------- */
    private Object instance;

    /* -------------------------------------------------------------------------------------------------------------- *
     * Constructor
     * -------------------------------------------------------------------------------------------------------------- */
    public Walker(Object instance) {
        this.instance = instance;
    }

    /* -------------------------------------------------------------------------------------------------------------- *
     * Lifecycle methods
     * -------------------------------------------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------------------------------------------- *
     * Public methods
     * -------------------------------------------------------------------------------------------------------------- */
    @SuppressWarnings("unchecked")
    public <T> T evaluate(String expression, Class<T> cast)
        throws
            OFlexException {

        return
            (T)
                ComponentFactory.getInstance()
                    .createXSequence(expression)
                        .evaluate(instance);
    }

    /* -------------------------------------------------------------------------------------------------------------- *
     * Private methods
     * -------------------------------------------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------------------------------------------- *
     * Getters & Setters
     * -------------------------------------------------------------------------------------------------------------- */

}