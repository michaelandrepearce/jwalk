package org.simplecoding.jwalk.components;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import org.simplecoding.jwalk.exceptions.MethodAccessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author fred
 */
public class WalkMethod
    extends
        WalkComponent {

    /* -------------------------------------------------------------------------------------------------------------- *
     * Private Static Fields
     * -------------------------------------------------------------------------------------------------------------- */
    private static final Logger LOGGER =
        LoggerFactory.getLogger(WalkMethod.class);

    /* -------------------------------------------------------------------------------------------------------------- *
     * Private Fields
     * -------------------------------------------------------------------------------------------------------------- */
    private List<Class<?>> classes;

    /* -------------------------------------------------------------------------------------------------------------- *
     * Constructor
     * -------------------------------------------------------------------------------------------------------------- */
    public WalkMethod() {
        this(null);
    }

    public WalkMethod(String id) {
        super(id);

        this.classes = new ArrayList<Class<?>>(4);
    }

    /* -------------------------------------------------------------------------------------------------------------- *
     * Lifecycle methods
     * -------------------------------------------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------------------------------------------- *
     * Public methods
     * -------------------------------------------------------------------------------------------------------------- */
    @Override
    public Object evaluate(Object instance, Deque<Object> args)
        throws
            MethodAccessingException {

        try {
            Method method =
                instance.getClass()
                    .getDeclaredMethod(
                        this.getId(),
                        this.classes.toArray(new Class<?>[] {}));

            method.setAccessible(true);

            Object[] arguments = new Object[this.classes.size()];
            for(int i = 0; i < arguments.length; i++) {
                arguments[i] = args.pop();
            }

            return
                method
                    .invoke(
                        instance,
                        arguments);
        }
        catch (Exception e) {
            throw new MethodAccessingException(e);
        }
    }

    public WalkMethod add(Class<?> clazz) {
        LOGGER.debug(
            new StringBuilder("add argument class : ")
                .append(clazz.getName())
                .toString());

        this.classes.add(clazz);

        return this;
    }

    /* -------------------------------------------------------------------------------------------------------------- *
     * Private methods
     * -------------------------------------------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------------------------------------------- *
     * Getters & Setters
     * -------------------------------------------------------------------------------------------------------------- */

}
