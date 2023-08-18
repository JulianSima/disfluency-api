package com.disfluency.disfluencyapi.service.users.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private static final String INVITATION_URL = "https://disfluency.com/sign-up-confirmation/{id}";

    private final JavaMailSender sender;

    public void sendPatientInvitationEmail(String patientId, String patientAccount) {
        var mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setTo(patientAccount);
            String inviteUrl = buildInvitationUrl(patientId);
            String body = "<!DOCTYPE html>\n" +
                    "<html lang=\"es\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Invitación a Disfluency App</title>\n" +
                    "</head>\n" +
                    "<body style=\"font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4;\">\n" +
                    "\n" +
                    "    <div style=\"background-color: #fc8538; color: #fff; text-align: center; padding: 20px;\">\n" +
                    "        <h1>¡Bienvenido a Disfluency App!</h1>\n" +
                    "        <p>Tu terapeuta te invita a unirte a nuestra aplicación para llevar a cabo tu terapia de manera efectiva.</p>\n" +
                    "    </div>\n" +
                    "\n" +
                    "    <div style=\"padding: 20px;\">\n" +
                    "        <p>Estás a un paso de comenzar tu viaje hacia una mejor comunicación. Para comenzar, sigue estos pasos:</p>\n" +
                    "        <ol>\n" +
                    "            <li>Descarga la aplicación Disfluency desde la tienda de aplicaciones de tu dispositivo.</li>\n" +
                    "            <li>Haz clic en el siguiente enlace para actualizar tu contraseña y finalizar el proceso de registro:</li>\n" +
                    "        </ol>\n" +
                    "        <p style=\"text-align: center;\">\n" +
                    "            <a href=\"{INVITE_URL}\" style=\"display: inline-block; padding: 10px 20px; background-color: #4a90e2; color: #fff; text-decoration: none; border-radius: 5px;\">Completar Registro</a>\n" +
                    "        </p>\n" +
                    "        <p>Una vez hayas actualizado tu contraseña, estarás listo para aprovechar al máximo nuestra aplicación y trabajar junto con tu terapeuta para lograr tus objetivos de comunicación.</p>\n" +
                    "    </div>\n" +
                    "\n" +
                    "    <div style=\"background-color: #4a90e2; color: #fff; text-align: center; padding: 20px;\">\n" +
                    "        <p>Si tienes alguna pregunta o necesitas asistencia, no dudes en contactarnos. ¡Esperamos verte pronto en Disfluency App!</p>\n" +
                    "    </div>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>\n";
            body = body.replace("{INVITE_URL}", inviteUrl);
            helper.setText(body, true);
            helper.setSubject("Comienza tu viaje hacia una mejor comunicación.");
            sender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String buildInvitationUrl(String id){
        return INVITATION_URL.replace("{id}", id);
    }
}
