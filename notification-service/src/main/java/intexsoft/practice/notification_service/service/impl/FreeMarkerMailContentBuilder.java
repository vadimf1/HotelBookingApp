package intexsoft.practice.notification_service.service.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import intexsoft.practice.notification_service.service.MailContentBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class FreeMarkerMailContentBuilder implements MailContentBuilder {

    private final Configuration freemarkerConfig;

    @Override
    public String build(String templateName, Map<String, Object> model) {
        try (StringWriter out = new StringWriter()) {
            Template template = freemarkerConfig.getTemplate(templateName);
            template.process(model, out);
            return out.toString();
        } catch (IOException | TemplateException e) {
            throw new RuntimeException("Ошибка при генерации email-шаблона", e);
        }
    }
}
