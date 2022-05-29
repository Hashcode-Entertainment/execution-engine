package cloud.hashcodeentertainment.executionengineservice.utils;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class MessageUtils {

    private static ResourceBundleMessageSource messageSource;

    public MessageUtils(ResourceBundleMessageSource messageSource) {
        MessageUtils.messageSource = messageSource;
    }

    public static String getMessage(final String key, Object... args){
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }
}