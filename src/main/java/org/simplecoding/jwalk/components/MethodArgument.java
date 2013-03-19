package org.simplecoding.jwalk.components;

/**
 *
 * @author fred
 */
public class MethodArgument {

    /* -------------------------------------------------------------------------------------------------------------- *
     * Private Static Fields
     * -------------------------------------------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------------------------------------------- *
     * Private Fields
     * -------------------------------------------------------------------------------------------------------------- */
    private String      name;
    private Class<?>    definition;
    private Object      object;

    /* -------------------------------------------------------------------------------------------------------------- *
     * Constructor
     * -------------------------------------------------------------------------------------------------------------- */
    public MethodArgument() {
        this(null, null);
    }

    public MethodArgument(String name, Class<?> definition) {
        this.name       = name;
        this.definition = definition;
        this.object     = null;
    }

    /* -------------------------------------------------------------------------------------------------------------- *
     * Lifecycle methods
     * -------------------------------------------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------------------------------------------- *
     * Public methods
     * -------------------------------------------------------------------------------------------------------------- */
    @Override
    public String toString() {
        return
            new StringBuilder(this.name)
                .append("(")
                .append(this.definition)
                .append(")")
                .append(" = ")
                .append(this.object)
                .toString();
    }

    /* -------------------------------------------------------------------------------------------------------------- *
     * Private methods
     * -------------------------------------------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------------------------------------------- *
     * Getters & Setters
     * -------------------------------------------------------------------------------------------------------------- */
    public String getName() {
        return name;
    }

    public MethodArgument setName(String name) {
        this.name = name;

        return this;
    }

    public Class<?> getDefinition() {
        return definition;
    }

    public MethodArgument setDefinition(Class<?> definition) {
        this.definition = definition;

        return this;
    }

    public Object getObject() {
        return object;
    }

    public MethodArgument setObject(Object object) {
        this.object = object;

        return this;
    }


}
