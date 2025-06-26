package intexsoft.practice.notification_service.localization;

import java.util.List;

public final class NotificationLoginMessageKeys {
    public static final String SUBJECT = "subject";
    public static final String LOGIN_TITLE = "login.title";
    public static final String LOGIN_BODY = "login.body";
    public static final String IP_LABEL = "ip.label";
    public static final String COUNTRY_LABEL = "country.label";
    public static final String CITY_LABEL = "city.label";
    public static final String AGENT_LABEL = "agent.label";
    public static final String TIME_LABEL = "time.label";
    public static final String UNKNOWN = "unknown";

    public static final List<String> ALL_KEYS = List.of(
            SUBJECT,
            LOGIN_TITLE,
            LOGIN_BODY,
            IP_LABEL,
            COUNTRY_LABEL,
            CITY_LABEL,
            AGENT_LABEL,
            TIME_LABEL,
            UNKNOWN
    );

    private NotificationLoginMessageKeys() {
    }
}
