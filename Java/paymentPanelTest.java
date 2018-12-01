import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class paymentPanelTest {

	@Test
	void testCreditFieldisEmpty() {
		PaymentPanel panel = new PaymentPanel("hello");
		assertEquals(panel.getAllFieldsCreditCard(), true);

	}

	@Test
	void testBankTransferisEmpty() {
		PaymentPanel panel = new PaymentPanel("hello");
		assertEquals(panel.bankTransferisEmpty(), true);
	}

	@Test
	void testGetAllFieldsCreditCard() {
		PaymentPanel panel = new PaymentPanel("hello");
		assertEquals(panel.getAllFieldsCreditCard(), "");
	}

	@Test
	void testGetAllFieldsBankTransfer() {
		PaymentPanel panel = new PaymentPanel("hello");
		assertEquals(panel.getAllFieldsBankTransfer(), "");
	}

	@Test
	void PaymentMethod() {
		PaymentPanel panel = new PaymentPanel("hello");

		assertEquals(panel.getPaymentMethod(), "None");
		panel.setPaymentMethod("Success");
		assertEquals(panel.getPaymentMethod(), "Success");
	}
}
