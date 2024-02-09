package com.services.chambitas.utility;

import static com.services.chambitas.constant.EmailConstant.*;
import static javax.mail.Message.RecipientType.CC;
import static javax.mail.Message.RecipientType.TO;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.sun.mail.smtp.SMTPTransport;

@Service
public class EmailService {

	public void resetPassword(String firstName, String password, String email) throws MessagingException {
		Message message = createEmail(firstName, password, email);
		SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
		smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWORD);
		smtpTransport.sendMessage(message, message.getAllRecipients());
		smtpTransport.close();
	}

	private Message createEmail(String firstName, String token, String email) throws MessagingException {
		Message message = new MimeMessage(getEmailSession());
		message.setFrom(new InternetAddress(FROM_EMAIL));
		message.setRecipients(TO, InternetAddress.parse(email, false));
		message.setRecipients(CC, InternetAddress.parse(CC_EMAIL, false));
		message.setSubject(EMAIL_SUBJECT);
		message.setContent("<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n" + "    <meta charset=\"UTF-8\">\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
				+ "    <title>Password</title>\n" + "</head>\n" + "<body>\n" + "    \n"
				+ "        <center class=\"wrapper\">\n"
				+ "            <table class=\"top-panel center\" width=\"602\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"
				+ "                <tbody>\n" + "                <tr>\n"
				+ "                    <td class=\"title\" width=\"300\">Hale Jobs</td>\n"
				+ "                    <td class=\"subject\" width=\"300\"><a class=\"strong\" href=\"#\" target=\"_blank\">www.hale.com.mx</a></td>\n"
				+ "                </tr>\n" + "                <tr>\n"
				+ "                    <td class=\"border\" colspan=\"2\">&nbsp;</td>\n" + "                </tr>\n"
				+ "                </tbody>\n" + "            </table>\n" + "        \n"
				+ "            <div class=\"spacer\">&nbsp;</div>\n" + "        \n"
				+ "            <table class=\"main center\" width=\"602\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"
				+ "                <tbody>\n" + "                <tr>\n" + "                    <td class=\"column\">\n"
				+ "                        <div class=\"column-top\">&nbsp;</div>\n"
				+ "                        <table class=\"content\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"
				+ "                            <tbody>\n" + "                            <tr>\n"
				+ "                                <td class=\"padded\">\n"
				+ "                                  <h1>Restablecer contraseña</h1>\n"
				+ "                                  <p>Hola has solicitado <strong>restablecer tu contraseña</strong> sigue las siguientes indicaciones:</p>\n"
				+ "                                  <p>Presiona el link con la leyenda 'Restablecer', posteriormente a ello coloca tu nueva contraseña: </p>\n"
				+ "                                  <p style=\"text-align:center;\">"
				+ "                                  <a href=\"http://localhost:4200/auth/reset-password/" + token + "/"
				+ email + "\"" + "                                   class=\"btn\">"
				+ "                                   Restablecer </a>" + "                                  </p>\n"
				+ "                                  <p style=\"text-align:center;\">\n"
				+ "                                    <a class=\"strong\">En Hale estamos comprometidos con su bienestar. </a>\n"
				+ "                                  </p>\n"
				+ "                                  <p style=\"text-align:center;\"> Gracias por su atención :). </p>\n"
				+ "                                </td>\n" + "                            </tr>\n"
				+ "                            </tbody>\n" + "                        </table>\n"
				+ "                        <div class=\"column-bottom\">&nbsp;</div>\n" + "                    </td>\n"
				+ "                </tr>\n" + "                </tbody>\n" + "            </table>\n" + "        \n"
				+ "            <div class=\"spacer\">&nbsp;</div>\n" + "        \n"
				+ "            <table class=\"footer center\" width=\"602\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"
				+ "                <tbody>\n" + "                <tr>\n"
				+ "                    <td class=\"border\" colspan=\"2\">&nbsp;</td>\n" + "                </tr>\n"
				+ "                <tr>\n" + "                    <td class=\"signature\" width=\"300\">\n"
				+ "                        <p>\n" + "                            Soporte Hale AS,<br>\n"
				+ "                            Hale Inc.<br>\n"
				+ "                            +0 (000) 00-00-00, John Doe<br>\n" + "                            </p>\n"
				+ "                        <p>\n"
				+ "                            Support: <a class=\"strong\" href=\"mailto:#\" target=\"_blank\">support@Hale.mx</a>\n"
				+ "                        </p>\n" + "                    </td>\n"
				+ "                    <td class=\"subscription\" width=\"300\">\n"
				+ "                        <div class=\"logo-image\">\n"
				+ "                            <a href=\"https://hale.com.mx\" target=\"_blank\"><img src=\"https://hale.com.mx/assets/logo.png\" alt=\"logo-alt\" width=\"120\" height=\"70\"></a>\n"
				+ "                        </div>\n" + "                        <p>\n"
				+ "                            <a class=\"strong block\" href=\"#\" target=\"_blank\">\n"
				+ "                                Iniciar sesión\n" + "                            </a>\n"
				+ "                            <span class=\"hide\">&nbsp;&nbsp;|&nbsp;&nbsp;</span>\n"
				+ "                            <a class=\"strong block\" href=\"#\" target=\"_blank\">\n"
				+ "                                Aviso de privacidad\n" + "                            </a>\n"
				+ "                        </p>\n" + "                    </td>\n" + "                </tr>\n"
				+ "                </tbody>\n" + "            </table>\n" + "        </center>\n" + "\n" + "\n"
				+ "</body>\n" + "</html>", "text/html");

		// message.setText("Hola " + firstName + ", \n \n Restablece tu contraseña en la
		// siguiente url: http://localhost:4200/auth/reset-password/" + token + "/" +
		// email + "\n \n Nova Support");
		message.setSentDate(new Date());
		message.saveChanges();
		return message;
	}

	public void sendNewPasswordEmail(String firstName, String password, String email) throws MessagingException {
		Message message = createMessage(firstName, password, email);
		SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
		smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWORD);
		smtpTransport.sendMessage(message, message.getAllRecipients());
		smtpTransport.close();
	}

	private Message createMessage(String firstName, String token, String email) throws MessagingException {
		Message message = new MimeMessage(getEmailSession());
		message.setFrom(new InternetAddress(FROM_EMAIL));
		message.setRecipients(TO, InternetAddress.parse(email, false));
		message.setRecipients(CC, InternetAddress.parse(CC_EMAIL, false));
		message.setSubject(EMAIL_SUBJECT);
		message.setContent("<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n" + "    <meta charset=\"UTF-8\">\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
				+ "    <title>Password</title>\n" + "</head>\n" + "<body>\n" + "    \n"
				+ "        <center class=\"wrapper\">\n"
				+ "            <table class=\"top-panel center\" width=\"602\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"
				+ "                <tbody>\n" + "                <tr>\n"
				+ "                    <td class=\"title\" width=\"300\">Hale Jobs</td>\n"
				+ "                    <td class=\"subject\" width=\"300\"><a class=\"strong\" href=\"#\" target=\"_blank\">www.hale.com.mx</a></td>\n"
				+ "                </tr>\n" + "                <tr>\n"
				+ "                    <td class=\"border\" colspan=\"2\">&nbsp;</td>\n" + "                </tr>\n"
				+ "                </tbody>\n" + "            </table>\n" + "        \n"
				+ "            <div class=\"spacer\">&nbsp;</div>\n" + "        \n"
				+ "            <table class=\"main center\" width=\"602\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"
				+ "                <tbody>\n" + "                <tr>\n" + "                    <td class=\"column\">\n"
				+ "                        <div class=\"column-top\">&nbsp;</div>\n"
				+ "                        <table class=\"content\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"
				+ "                            <tbody>\n" + "                            <tr>\n"
				+ "                                <td class=\"padded\">\n"
				+ "                                  <h1>Nueva contraseña</h1>\n"
				+ "                                  <p> Hola te hacemos llegar  tu <strong> nueva contraseña </strong> la cual es: <strong>"
				+ token + " </strong> </p>\n"
				+ "                                  <p> Presiona el link con la leyenda 'Iniciar sesión', y comienza a hacer uso de tu cuenta: </p>\n"
				+ "                                  <p style=\"text-align:center;\">"
				+ "                                  <a href=\"http://localhost:4200/auth/login\""
				+ "                                   class=\"btn\">"
				+ "                                   Iniciar sesión </a>" + "                                  </p>\n"
				+ "                                  <p style=\"text-align:center;\">\n"
				+ "                                    <a class=\"strong\">En Hale estamos comprometidos con su bienestar. </a>\n"
				+ "                                  </p>\n"
				+ "                                  <p style=\"text-align:center;\"> Gracias por su atención :). </p>\n"
				+ "                                </td>\n" + "                            </tr>\n"
				+ "                            </tbody>\n" + "                        </table>\n"
				+ "                        <div class=\"column-bottom\">&nbsp;</div>\n" + "                    </td>\n"
				+ "                </tr>\n" + "                </tbody>\n" + "            </table>\n" + "        \n"
				+ "            <div class=\"spacer\">&nbsp;</div>\n" + "        \n"
				+ "            <table class=\"footer center\" width=\"602\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"
				+ "                <tbody>\n" + "                <tr>\n"
				+ "                    <td class=\"border\" colspan=\"2\">&nbsp;</td>\n" + "                </tr>\n"
				+ "                <tr>\n" + "                    <td class=\"signature\" width=\"300\">\n"
				+ "                        <p>\n" + "                            Soporte Hale AS,<br>\n"
				+ "                            Hale Jobs.<br>\n"
				+ "                            +0 (000) 00-00-00, John Doe<br>\n" + "                            </p>\n"
				+ "                        <p>\n"
				+ "                            Support: <a class=\"strong\" href=\"mailto:#\" target=\"_blank\">support@hale.com.mx</a>\n"
				+ "                        </p>\n" + "                    </td>\n"
				+ "                    <td class=\"subscription\" width=\"300\">\n"
				+ "                        <div class=\"logo-image\">\n"
				+ "                            <a href=\"https://hale.com.mx\" target=\"_blank\"><img src=\"https://hale.com.mx/assets/logo.png\" alt=\"logo-alt\" width=\"120\" height=\"70\"></a>\n"
				+ "                        </div>\n" + "                        <p>\n"
				+ "                            <a class=\"strong block\" href=\"#\" target=\"_blank\">\n"
				+ "                                Iniciar sesión\n" + "                            </a>\n"
				+ "                            <span class=\"hide\">&nbsp;&nbsp;|&nbsp;&nbsp;</span>\n"
				+ "                            <a class=\"strong block\" href=\"#\" target=\"_blank\">\n"
				+ "                                Aviso de privacidad\n" + "                            </a>\n"
				+ "                        </p>\n" + "                    </td>\n" + "                </tr>\n"
				+ "                </tbody>\n" + "            </table>\n" + "        </center>\n" + "\n" + "\n"
				+ "</body>\n" + "</html>", "text/html");
		// message.setText("Hola " + firstName + ", \n \n El password para tu cuenta es:
		// " + token + "\n \n Ingresa a nuestro sitio: https://portal.macropay.mx/login"
		// + "\n \n Team SAP");
		message.setSentDate(new Date());
		message.saveChanges();
		return message;
	}

	public void sendNotification(String firstName, String orderId, String email) throws MessagingException {
		Message message = createMessageN(firstName, orderId, email);
		SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
		smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWORD);
		smtpTransport.sendMessage(message, message.getAllRecipients());
		smtpTransport.close();
	}

	private Message createMessageN(String firstName, String orderId, String email) throws MessagingException {
		Message message = new MimeMessage(getEmailSession());
		message.setFrom(new InternetAddress(FROM_EMAIL));
		message.setRecipients(TO, InternetAddress.parse(email, false));
		message.setRecipients(CC, InternetAddress.parse(CC_EMAIL, false));
		message.setSubject("Hale Jobs | " + orderId);
		message.setContent("<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n" + "    <meta charset=\"UTF-8\">\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
				+ "    <title>Password</title>\n" + "</head>\n" + "<body>\n" + "    \n"
				+ "        <center class=\"wrapper\">\n"
				+ "            <table class=\"top-panel center\" width=\"602\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"
				+ "                <tbody>\n" + "                <tr>\n"
				+ "                    <td class=\"title\" width=\"300\">Hale Jobs</td>\n"
				+ "                    <td class=\"subject\" width=\"300\"><a class=\"strong\" href=\"#\" target=\"_blank\">www.hale.com.mx</a></td>\n"
				+ "                </tr>\n" + "                <tr>\n"
				+ "                    <td class=\"border\" colspan=\"2\">&nbsp;</td>\n" + "                </tr>\n"
				+ "                </tbody>\n" + "            </table>\n" + "        \n"
				+ "            <div class=\"spacer\">&nbsp;</div>\n" + "        \n"
				+ "            <table class=\"main center\" width=\"602\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"
				+ "                <tbody>\n" + "                <tr>\n" + "                    <td class=\"column\">\n"
				+ "                        <div class=\"column-top\">&nbsp;</div>\n"
				+ "                        <table class=\"content\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"
				+ "                            <tbody>\n" + "                            <tr>\n"
				+ "                                <td class=\"padded\">\n"
				+ "                                  <h1> Orden creada correctamente </h1>\n"
				+ "                                  <p> Tu orden se ha <strong> generado correctamente </strong> sigue las siguientes indicaciones:</p>\n"
				+ "                                  <p>Presiona el link con la leyenda 'Ver mi orden', para saber el estatus y detalles de tu orden: </p>\n"
				+ "                                  <p style=\"text-align:center;\">"
				+ "                                  <a href=\"http://localhost:4200/order/" + orderId + "\""
				+ "                                   class=\"btn\">"
				+ "                                   Ver mi orden </a>" + "                                  </p>\n"
				+ "                                  <p style=\"text-align:center;\">\n"
				+ "                                    <a class=\"strong\">En Hale estamos comprometidos con su bienestar. </a>\n"
				+ "                                  </p>\n"
				+ "                                  <p style=\"text-align:center;\"> Gracias por su atención :). </p>\n"
				+ "                                </td>\n" + "                            </tr>\n"
				+ "                            </tbody>\n" + "                        </table>\n"
				+ "                        <div class=\"column-bottom\">&nbsp;</div>\n" + "                    </td>\n"
				+ "                </tr>\n" + "                </tbody>\n" + "            </table>\n" + "        \n"
				+ "            <div class=\"spacer\">&nbsp;</div>\n" + "        \n"
				+ "            <table class=\"footer center\" width=\"602\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"
				+ "                <tbody>\n" + "                <tr>\n"
				+ "                    <td class=\"border\" colspan=\"2\">&nbsp;</td>\n" + "                </tr>\n"
				+ "                <tr>\n" + "                    <td class=\"signature\" width=\"300\">\n"
				+ "                        <p>\n" + "                            Soporte Hale AS,<br>\n"
				+ "                            Hale Inc.<br>\n"
				+ "                            +0 (000) 00-00-00, John Doe<br>\n" + "                            </p>\n"
				+ "                        <p>\n"
				+ "                            Support: <a class=\"strong\" href=\"mailto:#\" target=\"_blank\">support@Hale.mx</a>\n"
				+ "                        </p>\n" + "                    </td>\n"
				+ "                    <td class=\"subscription\" width=\"300\">\n"
				+ "                        <div class=\"logo-image\">\n"
				+ "                            <a href=\"https://hale.com.mx\" target=\"_blank\"><img src=\"https://hale.com.mx/assets/logo.png\" alt=\"logo-alt\" width=\"120\" height=\"70\"></a>\n"
				+ "                        </div>\n" + "                        <p>\n"
				+ "                            <a class=\"strong block\" href=\"#\" target=\"_blank\">\n"
				+ "                                Iniciar sesión\n" + "                            </a>\n"
				+ "                            <span class=\"hide\">&nbsp;&nbsp;|&nbsp;&nbsp;</span>\n"
				+ "                            <a class=\"strong block\" href=\"#\" target=\"_blank\">\n"
				+ "                                Aviso de privacidad\n" + "                            </a>\n"
				+ "                        </p>\n" + "                    </td>\n" + "                </tr>\n"
				+ "                </tbody>\n" + "            </table>\n" + "        </center>\n" + "\n" + "\n"
				+ "</body>\n" + "</html>", "text/html");

		message.setSentDate(new Date());
		message.saveChanges();
		return message;
	}

	private Session getEmailSession() {
		Properties properties = System.getProperties();
		properties.put(SMTP_HOST, GMAIL_SMTP_SERVER);
		properties.put(SMTP_AUTH, true);
		properties.put(SMTP_PORT, DEFAULT_PORT);
		properties.put(SMTP_STARTTLS_ENABLE, true);
		properties.put(SMTP_STARTTLS_REQUIRED, true);
		return Session.getInstance(properties, null);
	}

}
