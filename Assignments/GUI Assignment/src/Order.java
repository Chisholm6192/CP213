package cp213;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Stores a HashMap of MenuItem objects and the quantity of each MenuItem
 * ordered. Each MenuItem may appear only once in the HashMap.
 *
 * @author Ryan Chisholm - 169027577 - chis7577@mylaurier.ca
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2023-12-6
 */
public class Order implements Printable {

    /**
     * The current tax rate on menu items.
     */
    public static final BigDecimal TAX_RATE = new BigDecimal(0.13);

    // Attributes

    HashMap<MenuItem, Integer> Order = new HashMap<MenuItem, Integer>();

    /**
     * Increments the quantity of a particular MenuItem in an Order with a new
     * quantity. If the MenuItem is not in the order, it is added.
     *
     * @param item     The MenuItem to purchase - the HashMap key.
     * @param quantity The number of the MenuItem to purchase - the HashMap value.
     */
    public void add(final MenuItem item, final int quantity) {

	Order.put(item, quantity);

    }

    /**
     * Calculates the total value of all MenuItems and their quantities in the
     * HashMap.
     *
     * @return the total price for the MenuItems ordered.
     */
    public BigDecimal getSubTotal() {
	BigDecimal subtotal = new BigDecimal(0);

	for (MenuItem item : Order.keySet()) {
	    BigDecimal quantity = new BigDecimal(Order.get(item));
	    BigDecimal ItemPrice = item.getPrice().multiply(quantity);
	    subtotal = subtotal.add(ItemPrice);
	}

	return subtotal;
    }

    /**
     * Calculates and returns the total taxes to apply to the subtotal of all
     * MenuItems in the order. Tax rate is TAX_RATE.
     *
     * @return total taxes on all MenuItems
     */
    public BigDecimal getTaxes() {
	BigDecimal tax = new BigDecimal(0);
	BigDecimal subtotal = getSubTotal();

	tax = TAX_RATE.multiply(subtotal);

	return tax;
    }

    /**
     * Calculates and returns the total price of all MenuItems order, including tax.
     *
     * @return total price
     */
    public BigDecimal getTotal() {
	BigDecimal tax = getTaxes();
	BigDecimal subtotal = getSubTotal();

	BigDecimal total = subtotal.add(tax);

	return total;
    }

    /*
     * Implements the Printable interface print method. Prints lines to a Graphics2D
     * object using the drawString method. Prints the current contents of the Order.
     */
    @Override
    public int print(final Graphics graphics, final PageFormat pageFormat, final int pageIndex)
	    throws PrinterException {
	int result = PAGE_EXISTS;

	if (pageIndex == 0) {
	    final Graphics2D g2d = (Graphics2D) graphics;
	    g2d.setFont(new Font("MONOSPACED", Font.PLAIN, 12));
	    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
	    // Now we perform our rendering
	    final String[] lines = this.toString().split("\n");
	    int y = 100;
	    final int inc = 12;

	    for (final String line : lines) {
		g2d.drawString(line, 100, y);
		y += inc;
	    }
	} else {
	    result = NO_SUCH_PAGE;
	}
	return result;
    }

    /**
     * Returns a String version of a receipt for all the MenuItems in the order.
     */
    @Override
    public String toString() {
	String receipt = "";
	String format = "%-14s %s @ $ %2.2f = $ %5.2f"; // formatting for items

	for (MenuItem item : Order.keySet()) {
	    // traverse hashmap and access all keys
	    String output = "";

	    // values of MenuItem
	    String name = item.getName();
	    BigDecimal price = item.getPrice();
	    int quantint = Order.get(item);
	    BigDecimal quantity = new BigDecimal(quantint);
	    BigDecimal cost = price.multiply(quantity);

	    // constructing string of items
	    output = String.format(format, name, quantint, price, cost);

	    receipt += output + '\n';
	}

	// get values of subtotal, tax and total for order
	BigDecimal subtotal = getSubTotal();
	BigDecimal tax = getTaxes();
	BigDecimal total = getTotal();

	// formatting and adding values to output
	receipt += '\n' + String.format("%-27s $ %5.2f", "Subtotal: ", subtotal) + '\n';
	receipt += String.format("%-27s $ %5.2f", "Taxes: ", tax) + '\n';
	receipt += String.format("%-27s $ %5.2f", "Total: ", total);

	return receipt;
    }

    /**
     * Replaces the quantity of a particular MenuItem in an Order with a new
     * quantity. If the MenuItem is not in the order, it is added. If quantity is 0
     * or negative, the MenuItem is removed from the Order.
     *
     * @param item     The MenuItem to update
     * @param quantity The quantity to apply to item
     */
    public void update(final MenuItem item, final int quantity) {

	if (quantity > 0) {
	    add(item, quantity);
	}

	else {
	    Order.remove(item);
	}

    }
}