package org.simplecoding.jwalk.components;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.simplecoding.jwalk.exceptions.MethodAccessingException;
import org.simplecoding.jwalk.exceptions.MissingMethodArgumentException;
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
    private List<MethodArgument> arguments;

    /* -------------------------------------------------------------------------------------------------------------- *
     * Constructor
     * -------------------------------------------------------------------------------------------------------------- */
    public WalkMethod() {
        this(null);
    }

    public WalkMethod(String id) {
        super(id);

        this.arguments = new ArrayList<MethodArgument>(4);
    }

    /* -------------------------------------------------------------------------------------------------------------- *
     * Lifecycle methods
     * -------------------------------------------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------------------------------------------- *
     * Public methods
     * -------------------------------------------------------------------------------------------------------------- */
    @Override
    public Object evaluate(Object instance, Map<String, Object> map)
        throws
            MethodAccessingException {

        try {

            List<MethodArgument>    args    = this.getArguments(map);
            List<Object>            objects = new ArrayList<Object>(4);
            List<Class<?>>          classes = new ArrayList<Class<?>>(4);

            for(MethodArgument arg : args) {
                objects.add(arg.getObject());
                classes.add(arg.getDefinition());
            }

            Method method =
                instance.getClass()
                    .getDeclaredMethod(
                        this.getId(),
                        classes.toArray(new Class<?>[] {}));

            method.setAccessible(true);


            return
                method
                    .invoke(
                        instance,
                        objects.toArray());
        }
        catch (Exception e) {
            throw new MethodAccessingException(e);
        }
    }

    public List<MethodArgument> getArguments(Map<String, Object> map)
        throws
            MissingMethodArgumentException {

        LOGGER.debug("getArgumentDefintions(Map) :: begins");

        List<MethodArgument> definitions = new ArrayList<MethodArgument>(4);
        for(MethodArgument argument : this.arguments) {
            try {
                Object object = map.get(argument.getName());
                definitions.add(
                    new MethodArgument()
                        .setName(argument.getName())
                        .setObject(object)
                        .setDefinition(
                            (argument.getDefinition() == null)
                                ? object.getClass()
                                : argument.getDefinition()));
            }
            catch (NullPointerException e) {
                throw
                    new MissingMethodArgumentException(
                        new StringBuilder("Argument : ")
                            .append(argument)
                            .append(" not found in the map")
                            .toString());
            }
        }

        LOGGER.debug("getArgumentDefintions(Map) :: ends");

        return
            definitions;
    }

    public WalkMethod add(MethodArgument argument) {
        LOGGER.debug(
            new StringBuilder("add argument name : ")
                .append(argument.getName())
                .toString());

        this.arguments.add(argument);

        return this;
    }

    /* -------------------------------------------------------------------------------------------------------------- *
     * Private methods
     * -------------------------------------------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------------------------------------------- *
     * Getters & Setters
     * -------------------------------------------------------------------------------------------------------------- */

}
