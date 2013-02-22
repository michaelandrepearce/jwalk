package org.simplecoding.jwalk.components;

import java.lang.reflect.Field;
import java.util.Deque;
import org.simplecoding.jwalk.exceptions.FieldAccessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author fred
 */
public class WalkField
    extends
        WalkComponent {

    /* -------------------------------------------------------------------------------------------------------------- *
     * Private Static Fields
     * -------------------------------------------------------------------------------------------------------------- */
    private static final Logger LOGGER =
        LoggerFactory.getLogger(WalkField.class);

    /* -------------------------------------------------------------------------------------------------------------- *
     * Private Fields
     * -------------------------------------------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------------------------------------------- *
     * Constructor
     * -------------------------------------------------------------------------------------------------------------- */
    public WalkField() {
        this(null);
    }

    public WalkField(String id) {
        super(id);
    }

    /* -------------------------------------------------------------------------------------------------------------- *
     * Lifecycle methods
     * -------------------------------------------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------------------------------------------- *
     * Public methods
     * -------------------------------------------------------------------------------------------------------------- */
    @Override
    public Object evaluate(Object instance, Deque<Object> arguments)
        throws
            FieldAccessingException {

        try {
            Field field = instance.getClass().getDeclaredField(this.getId());
            field.setAccessible(true);

            return field.get(instance);
        }
        catch (Exception e) {
            throw new FieldAccessingException(e);
        }
    }

    /* -------------------------------------------------------------------------------------------------------------- *
     * Private methods
     * -------------------------------------------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------------------------------------------- *
     * Getters & Setters
     * -------------------------------------------------------------------------------------------------------------- */

}