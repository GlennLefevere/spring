package be.vdab.mail;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.EntityLinks;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import be.vdab.entities.Filiaal;

public class MailSenderImpl implements MailSender {
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	private final JavaMailSender sender;
	private final String webmaster;
	private final EntityLinks entityLinks; // wordt later gebruikt

	@Autowired
	public MailSenderImpl(JavaMailSender sender, @Value("${webmaster}") String webmaster) {
		this.sender = sender;
		this.webmaster = webmaster;
		this.entityLinks = entityLinks;
	}

	@Override
	public void nieuwFiliaalMail(Filiaal filiaal) {
		try {
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
			helper.setTo(webmaster);
			helper.setSubject("Nieuw filiaal");
			helper.setText(String.format("Filiaal <strong>%s</strong> is toegevoegd", filiaal.getNaam()), true);
			sender.send(message);
		} catch (MessagingException ex) {
			logger.log(Level.SEVERE, "kan mail nieuw filiaal niet versturen", ex);
		}
	}
}
