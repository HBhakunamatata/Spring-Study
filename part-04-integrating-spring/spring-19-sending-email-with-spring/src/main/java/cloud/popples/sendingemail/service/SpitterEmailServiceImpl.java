package cloud.popples.sendingemail.service;

import cloud.popples.sendingemail.domain.Spittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class SpitterEmailServiceImpl implements SpitterEmailService {

    @Autowired
    private SpringTemplateEngine templateEngine;

    private final JavaMailSender mailSender;

    private static final String FIREFOX_LOGO_IMAGE = "mail-template/images/logo.png";

    @Autowired
    public SpitterEmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendSimpleSpitterEmail(String to, Spittle spittle) {
        String spitterName = spittle.getSpitter().getFullName();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("2495716508@qq.com");
        message.setTo(to);
        message.setSubject("New Spittle from " + spitterName);
        message.setText(spitterName + " says: " + spittle.getMessage());

        mailSender.send(message);
    }


    @Override
    public void sendMimeEmailWithAttachments(String to, Spittle spittle) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("2495716508@qq.com");
            helper.setTo(to);
            helper.setSubject("New Spittle from " + spittle.getMessage());
            helper.setText(spittle.getMessage());

            ClassPathResource coupleImage = new ClassPathResource("/collateral/coupon.jpg");
            helper.addAttachment("coupon.jpg", coupleImage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        mailSender.send(message);
    }

    @Override
    public void sendEmailWithTemplate(String to, Spittle spittle) {
        Context context = new Context();
        context.setVariable("spitterName", spittle.getSpitter().getFullName());
        context.setVariable("spittleText", spittle.getMessage());
        context.setVariable("firefoxLogo", FIREFOX_LOGO_IMAGE);

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("2495716508@qq.com");
            helper.setTo(to);
            helper.setSubject("New Spittle from " + spittle.getMessage());
            String messageText = templateEngine.process("emailTemplate.html", context);
            helper.setText(messageText);

            ClassPathResource resource = new ClassPathResource(FIREFOX_LOGO_IMAGE);
            helper.addInline("firefoxLogo", resource);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        mailSender.send(message);
    }

}
