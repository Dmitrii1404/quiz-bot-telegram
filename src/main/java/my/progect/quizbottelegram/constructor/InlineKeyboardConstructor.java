package my.progect.quizbottelegram.constructor;

import lombok.AllArgsConstructor;
import my.progect.quizbottelegram.templates.CallbackData;
import my.progect.quizbottelegram.templates.TextTemplate;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Service
@AllArgsConstructor
public class InlineKeyboardConstructor {

    private final InlineKeyboardFactory inlineKeyboardFactory;
    private final InlineButtonFactory inlineButtonFactory;

    public InlineKeyboardMarkup getStartInlineKeyboard() {

        InlineKeyboardButton javaButton = inlineButtonFactory.createButton(
                TextTemplate.START_BUTTON_JAVA,
                String.valueOf(CallbackData.START_COMMAND_JAVA_ID.getId())
        );

        return inlineKeyboardFactory.createKeyboard(1, javaButton);
    }

    public InlineKeyboardMarkup getTaskInlineKeyboard() {
        InlineKeyboardButton menu = inlineButtonFactory.createButton(
                TextTemplate.MENU_BUTTON,
                    String.valueOf(CallbackData.MENU.getId())
        );

        InlineKeyboardButton next = inlineButtonFactory.createButton(
                TextTemplate.NEXT_BUTTON,
                String.valueOf(CallbackData.NEXT.getId())
        );

        return inlineKeyboardFactory.createKeyboard(2, menu, next);
    }

}
