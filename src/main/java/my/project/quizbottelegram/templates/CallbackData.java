package my.project.quizbottelegram.templates;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CallbackData {

    START_COMMAND_JAVA_ID(1),
    MENU(2),
    NEXT(3);


    private final int id;

}
