package hardlearner.springStudy.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class DummyMailSender implements MailSender {
    private static final Logger log =  LoggerFactory.getLogger(DummyMailSender.class);

    @Override
    public void send(SimpleMailMessage simpleMailMessage) throws MailException {
        log.debug("메일이 발송되었습니다 : 사용자 레벨 업그레이드");
    }

    @Override
    public void send(SimpleMailMessage... simpleMailMessages) throws MailException {

    }
}
