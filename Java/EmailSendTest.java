import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmailSendTest {

	@Test
	void testGenerateOrderID() {
		EmailSend obj = new EmailSend("", "", "", "", "", "", "", "");
		String orderId = obj.generateOrderID();
		int length = orderId.length();
		assertEquals(length, 10);
	}

	@Test
	void testGenerateOCRNumber() {
		EmailSend obj = new EmailSend("", "", "", "", "", "", "", "");

		String ocr = obj.generateOCRNumber();
		int length = ocr.length();
		assertEquals(length, 20);
	}

	@Test
	void testGetOrderID() {
		EmailSend obj = new EmailSend("", "", "", "", "", "", "", "");
		String orderId = obj.generateOrderID();
		int length = orderId.length();
		assertEquals(length, 10);

	}

}
