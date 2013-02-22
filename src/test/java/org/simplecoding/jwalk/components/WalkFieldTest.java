package org.simplecoding.jwalk.components;

import java.util.LinkedList;
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
public class WalkFieldTest {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(WalkFieldTest.class);

    private Integer number;
    private String  message;

    private WalkField numberField;
    private WalkField messageField;

    private SimpleStructure bean;

    public WalkFieldTest() {
        this.number     = 16;
        this.message    = "Ca marche";

        this.numberField    = new WalkField("number");
        this.messageField   = new WalkField("message");

        this.bean =
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
     * Test of evaluate method, of class WalkField.
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
            this.numberField.evaluate(this.bean));
    }

    /**
     * Test of evaluate method, of class WalkField.
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
            this.messageField.evaluate(this.bean));
    }
}
