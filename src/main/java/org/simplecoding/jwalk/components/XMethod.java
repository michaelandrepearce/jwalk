package org.simplecoding.jwalk.components;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.simplecoding.jwalk.exceptions.MethodAccessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author fred
 */
public class XMethod
    extends
        XComponent {

    /* -------------------------------------------------------------------------------------------------------------- *
     * Private Static Fields
     * -------------------------------------------------------------------------------------------------------------- */
    private static final Logger LOGGER =
        LoggerFactory.getLogger(XMethod.class);

    /* -------------------------------------------------------------------------------------------------------------- *
     * Private Fields
     * -------------------------------------------------------------------------------------------------------------- */
    private List<Class<?>> classes;

    /* -------------------------------------------------------------------------------------------------------------- *
     * Constructor
     * -------------------------------------------------------------------------------------------------------------- */
    public XMethod() {
        this(null);
    }

    public XMethod(String id) {
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
    public Object evaluate(Object instance, Object... args)
        throws
            MethodAccessingException {

        try {
            Method method =
                instance.getClass()
                    .getDeclaredMethod(
                        this.getId(),
                        this.classes.toArray(new Class<?>[] {}));

            method.setAccessible(true);

            return
                method
                    .invoke(
                        instance,
                        this.values.toArray(new Object[] {}));
        }
        catch (Exception e) {
            throw new MethodAccessingException(e);
        }
    }

    public XMethod add(Class<?> clazz) {
        LOGGER.debug(
            new StringBuilder("add argument : ")
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
