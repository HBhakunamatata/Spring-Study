package cloud.popples.sendingemail.service;

import cloud.popples.sendingemail.domain.Spittle;

public interface SpitterEmailService {
    void sendSimpleSpitterEmail(String to, Spittle spittle);

    void sendMimeEmailWithAttachments(String to, Spittle spittle);

    void sendEmailWithTemplate(String to, Spittle spittle);
}
