package com.suryansh.mail;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContentBuilderService {
    private final TemplateEngine templateEngine;

    public MailContentBuilderService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String buildAuthVerificationMail(String verificationLink, String username) {
        Context context = new Context();
        context.setVariable("verificationLink", verificationLink);
        context.setVariable("username", username);
        return templateEngine.process("AuthMailTemplate", context);
    }

    public String buildForgotPasswordEmail(String link) {
        Context context = new Context();
        context.setVariable("link", link);
        return templateEngine.process("password-reset-email", context);
    }
}
