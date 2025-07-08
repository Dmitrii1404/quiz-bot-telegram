package my.project.quizbottelegram.model;

import lombok.Data;

import java.util.List;


@Data
public class Poll {

    private String codeSnippet;

    private String description;

    private String task;

    private List<String> answers;

    private Integer correctAnswer;

}