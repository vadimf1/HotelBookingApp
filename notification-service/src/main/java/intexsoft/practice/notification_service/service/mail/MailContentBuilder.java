package intexsoft.practice.notification_service.service.mail;

import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Map;

public interface MailContentBuilder {
    String build(String templateName, Map<String, Object> model) throws IOException, TemplateException;
}
