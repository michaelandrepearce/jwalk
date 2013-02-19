package org.simplecoding.oflex.structures;

import java.lang.reflect.Field;

/**
 *
 * @author fred
 */
public class SimpleStructure {

    /* -------------------------------------------------------------------------------------------------------------- *
     * Private Static Fields
     * -------------------------------------------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------------------------------------------- *
     * Private Fields
     * -------------------------------------------------------------------------------------------------------------- */
    private Integer number;
    private String  message;

    /* -------------------------------------------------------------------------------------------------------------- *
     * Constructor
     * -------------------------------------------------------------------------------------------------------------- */
    public SimpleStructure() {
        this.number     = null;
        this.message    = null;
    }

    /* -------------------------------------------------------------------------------------------------------------- *
     * Lifecycle methods
     * -------------------------------------------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------------------------------------------- *
     * Public methods
     * -------------------------------------------------------------------------------------------------------------- */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for(Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            result
                .append(field.getName())
                .append("[");

            try {
                result.append(field.get(this));
            }
            catch (Exception e) {
                result.append("ERROR");
            }

            result.append("], ");
        }

        return
            result
                .append("@")
                .toString();
    }

    @Override
    public boolean equals(Object element) {
        try {
            return (this.hashCode() == element.hashCode());
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    /* -------------------------------------------------------------------------------------------------------------- *
     * Private methods
     * -------------------------------------------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------------------------------------------- *
     * Getters & Setters
     * -------------------------------------------------------------------------------------------------------------- */
    public Integer getNumber() {
        return number;
    }

    public SimpleStructure setNumber(Integer number) {
        this.number = number;

        return this;
    }

    public String getMessage() {
        return message;
    }

    public SimpleStructure setMessage(String message) {
        this.message = message;

        return this;
    }
}
