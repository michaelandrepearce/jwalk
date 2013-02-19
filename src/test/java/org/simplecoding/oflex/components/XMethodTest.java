package org.simplecoding.oflex.components;

import org.simplecoding.oflex.components.XMethod;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.simplecoding.oflex.structures.SimpleStructure;
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

    private XMethod numberMethod;
    private XMethod messageMethod;

    private SimpleStructure simple;

    public XMethodTest() {
        this.number     = 16;
        this.message    = "Ca marche";

        this.numberMethod    = new XMethod("getNumber");
        this.messageMethod   = new XMethod("getMessage");

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
            this.numberMethod.evaluate(this.simple));
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
            this.messageMethod.evaluate(this.simple));
    }
}
