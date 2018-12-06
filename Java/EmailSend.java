import java.nio.charset.Charset;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSend {

	private String host;
	private String user;
	private String pass;
	private String to;
	private String from;
	private String subject;
	private String orderID;
	private String messageText;
	private String address, postalCode, OCR;

	private boolean sessionDebug;

	public EmailSend(String userName, String userLastName, String userEmail, String userAddress, String userPostalCode,
			String holder, String userCredit, String userItems) {
		try {
			this.host = "smtp.gmail.com";
			this.user = "mkhardwareproject@gmail.com";
			this.pass = "";
			this.to = userEmail;
			this.from = "mkhardwareproject@gmail.com";
			this.subject = "Order Confirmation - MK Hardware";
			this.address = userAddress;
			this.postalCode = userPostalCode;
			this.orderID = generateOrderID();
			this.messageText = "Order ID: " + orderID + "\n" + "Name: " + userName + " " + userLastName + "\nAddress: "
					+ userAddress + ", " + userPostalCode + "\nAccount Holder:" + holder
					+ "\nCredit Card Starting with [XXXX]: " + userCredit + "\nItems:\n" + userItems;
			sessionDebug = false;
			Properties props = System.getProperties();

			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.required", "true");
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

			Session mailSession = Session.getDefaultInstance(props, null);
			mailSession.setDebug(sessionDebug);
			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setText(messageText);

			Transport transport = mailSession.getTransport("smtp");
			transport.connect(host, user, pass);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			System.out.println("Order Confirmation has been sent");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public EmailSend(String userName, String userLastName, String userEmail, String userAddress, String userPostalCode,
			String userItems) {
		try {
			this.host = "smtp.gmail.com";
			this.user = "mkhardwareproject@gmail.com";
			this.pass = "FinalProj123";
			this.to = userEmail;
			this.from = "mkhardwareproject@gmail.com";
			this.subject = "Order Confirmation - MK Hardware";
			this.address = userAddress;
			this.postalCode = userPostalCode;
			this.orderID = generateOrderID();
			this.OCR = generateOCRNumber();
			this.messageText = "Order ID: " + orderID + "\n" + "Name: " + userName + " " + userLastName + "\nAddress: "
					+ userAddress + ", " + userPostalCode + "\nOCR: " + OCR + "\nItems:\n" + userItems;
			sessionDebug = false;
			Properties props = System.getProperties();

			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.required", "true");
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

			Session mailSession = Session.getDefaultInstance(props, null);
			mailSession.setDebug(sessionDebug);
			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setText(messageText);

			Transport transport = mailSession.getTransport("smtp");
			transport.connect(host, user, pass);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			System.out.println("Order Confirmation has been sent");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public String generateOrderID() {
		String characters = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder randomString = new StringBuilder();
		Random random = new Random();
		while (randomString.length() < 10) {
			int i = (int) (random.nextFloat() * characters.length());
			randomString.append(characters.charAt(i));
		}
		String orderId = randomString.toString();
		this.setOrderID(orderId);

		return orderId;
	}

	public String generateOCRNumber() {
		String characters = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder randomString = new StringBuilder();
		Random random = new Random();
		while (randomString.length() < 20) {
			int i = (int) (random.nextFloat() * characters.length());
			randomString.append(characters.charAt(i));
		}
		String ocrNr = randomString.toString();

		return ocrNr;
	}

	public String getOrderID() {
		return this.orderID;
	}

	public String setOrderID(String orderId) {
		return this.orderID = orderId;
	}
}
