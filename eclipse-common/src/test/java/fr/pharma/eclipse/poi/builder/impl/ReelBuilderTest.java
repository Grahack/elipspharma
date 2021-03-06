package fr.pharma.eclipse.poi.builder.impl;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.poi.formatter.ItemLineFormatter;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du builder ReelBuilder.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ReelBuilderTest extends AbstractEclipseJUnitTest {

    /**
     * Builder.
     */
    private ReelBuilder builder;

    /**
     * Formatter.
     */
    private ItemLineFormatter formatter;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.formatter = Mockito.mock(ItemLineFormatter.class);
        this.builder = new ReelBuilder();
        this.builder.setLineFormatter(this.formatter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.formatter = null;
        this.builder = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.formatter);
        Assert.assertNotNull(this.builder);
    }

}
