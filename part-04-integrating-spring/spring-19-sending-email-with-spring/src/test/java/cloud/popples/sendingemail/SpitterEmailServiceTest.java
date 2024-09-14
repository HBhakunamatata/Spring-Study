package cloud.popples.sendingemail;

import cloud.popples.sendingemail.config.EmailConfig;
import cloud.popples.sendingemail.domain.Spitter;
import cloud.popples.sendingemail.domain.Spittle;
import cloud.popples.sendingemail.service.SpitterEmailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = EmailConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpitterEmailServiceTest {

    @Autowired
    private SpitterEmailService emailService;

    private Spitter spitter;

    private Spittle spittle;

    private String to = "2495716508@qq.com";

    @Before
    public void prepareData() {
        spitter = new Spitter();
        spitter.setId(1L);
        spitter.setFullName("Spitter Fullname");

        spittle = new Spittle();
        spittle.setId(2L);
        spittle.setMessage("Spittle Message");
        spittle.setSpitter(spitter);
    }

    @Test
    public void testSendTextEmail() {
        emailService.sendSimpleSpitterEmail(to, spittle);
    }

    @Test
    public void testSendMailWithAttachment() {
        emailService.sendMimeEmailWithAttachments(to, spittle);
    }

    @Test
    public void testSendMailWithTemplate() {
        emailService.sendEmailWithTemplate(to, spittle);
    }

}
