package org.simplecoding.jwalk.components;

import java.util.Arrays;
import java.util.LinkedList;
import org.simplecoding.jwalk.components.XMethod;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.simplecoding.jwalk.structures.SimpleStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.*;

/**
 *
 * @author fred
 */
public class XMethodTest {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(XMethodTest.class);

    private Integer number;
    private String  message;

    private SimpleStructure simple;

    public XMethodTest() {
        this.number     = 16;
        this.message    = "Ca marche";

        this.simple =
            new SimpleStructure()
                .setNumber(this.number)
                .setMessage(this.message);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of evaluate method, of class XField.
     */
    @Test
    public void testEvaluateNumber()
        throws
            Exception {

        LOGGER.debug("--------------------------------------------------------------------------------");
        LOGGER.debug(" testEvaluateNumber");
        LOGGER.debug("--------------------------------------------------------------------------------");

        assertEquals(
            this.number,
            new XMethod("getNumber")
                .evaluate(this.simple));
    }

    /**
     * Test of evaluate method, of class XField.
     */
    @Test
    public void testEvaluateMessage()
        throws
            Exception {

        LOGGER.debug("--------------------------------------------------------------------------------");
        LOGGER.debug(" testEvaluateMessage");
        LOGGER.debug("--------------------------------------------------------------------------------");

        assertEquals(
            this.message,
            new XMethod("getMessage")
                .evaluate(this.simple));
    }

    /**
     * Test of evaluate method, of class XField.
     */
    @Test
    public void testEvaluateWithArguments()
        throws
            Exception {

        LOGGER.debug("--------------------------------------------------------------------------------");
        LOGGER.debug(" testEvaluateWithArguments");
        LOGGER.debug("--------------------------------------------------------------------------------");

        assertEquals(
            'a',
            new XMethod("charAt")
                .add(Integer.TYPE)
                .evaluate(
                    this.message,
                    new LinkedList<Object>(
                        Arrays.asList(
                            new Object[] { 4 }))));
    }
}
