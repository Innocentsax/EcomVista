package dev.Innocent.notification.email;

import dev.Innocent.notification.kafka.order.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dev.Innocent.notification.enums.EmailTemplates.ORDER_CONFIRMATION;
import static dev.Innocent.notification.enums.EmailTemplates.PAYMENT_CONFIRMATION;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_RELATED;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sentPaymentSuccessEmail(String destinationEmail, String customerName,
            BigDecimal amount, String orderReference) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                mimeMessage, MULTIPART_MODE_RELATED, UTF_8.name());
        mimeMessageHelper.setFrom("Innocentcharlesudo@gmail.com");
        final String template = PAYMENT_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("amount", amount);
        variables.put("orderReference", orderReference);

        Context context = new Context();
        context.setVariables(variables);
        mimeMessageHelper.setSubject(PAYMENT_CONFIRMATION.getSubject());

        try {
            String htmlContent = templateEngine.process(template, context);
            mimeMessageHelper.setText(htmlContent, true);
            mimeMessageHelper.setTo(destinationEmail);
            javaMailSender.send(mimeMessage);
            log.info(String.format("INFO - Email successfully sent to %s with template %s ", destinationEmail, template));
        } catch (MessagingException e) {
            log.error(String.format("ERROR - Failed to send email to %s with template %s ", destinationEmail, template));
            throw new MessagingException("Failed to send email");
        }
    }

    @Async
    public void sentOrderConfirmationEmail(String destinationEmail, String customerName,
                                           BigDecimal amount, String orderReference, List<Product> products) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                mimeMessage, MULTIPART_MODE_RELATED, UTF_8.name());
        mimeMessageHelper.setFrom("Innocentcharlesudo@gmail.com");
        final String template = ORDER_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("totalAmount", amount);
        variables.put("orderReference", orderReference);
        variables.put("products", products);

        Context context = new Context();
        context.setVariables(variables);
        mimeMessageHelper.setSubject(ORDER_CONFIRMATION.getSubject());

        try {
            String htmlContent = templateEngine.process(template, context);
            mimeMessageHelper.setText(htmlContent, true);
            mimeMessageHelper.setTo(destinationEmail);
            javaMailSender.send(mimeMessage);
            log.info(String.format("INFO - Email successfully sent to %s with template %s ", destinationEmail, template));
        } catch (MessagingException e) {
            log.error(String.format("Failed to send email to %s with template %s ", destinationEmail, template));
            throw new MessagingException("Failed to send email");
        }
    }
}
