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
	private String address, postalCode;
	private boolean sessionDebug;

	public EmailSend(String userName,String userLastName, String userEmail,String userAddress, String userPostalCode,String holder, String userCredit,String userItems)
	{
		try {
            host ="smtp.gmail.com" ;
            user = "mkhardwareproject@gmail.com";
            pass = "";
            to = userEmail;
            from = "mkhardwareproject@gmail.com";
            subject = "Order Confirmation - MK Hardware";
            address = userAddress;
            postalCode = userPostalCode;
            orderID = generateOrderID();
            messageText = "Order ID: "+ orderID + "\n" + "Name: " + userName + " " + userLastName +"\nAddress: " + userAddress + ", " + userPostalCode + "\nAccount Holder:" + holder + "\nCredit Card Starting with [XXXX]: " + userCredit + "\nItems:\n" + userItems;
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
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("Order Confirmation has been sent");
           }catch(Exception ex){
        	   System.out.println(ex);
        	   }
		}
	
	public String generateOrderID() {
	    byte[] array = new byte[10];
	    new Random().nextBytes(array);
	    String generatedString = new String(array, Charset.forName("UTF-8"));
	 return generatedString;
	}
	
	public String getOrderID(){
		return this.orderID;
	}
}


