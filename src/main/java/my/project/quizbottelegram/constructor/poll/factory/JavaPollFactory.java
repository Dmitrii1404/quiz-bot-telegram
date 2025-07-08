package my.project.quizbottelegram.constructor.poll.factory;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.project.quizbottelegram.model.Poll;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@Service
public class JavaPollFactory implements PollFactory {

    private final List<Poll> polls;

    public JavaPollFactory() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        polls = objectMapper.readValue(new File("./src/main/resources/questions.json"), new TypeReference<List<Poll>>() {
        });
    }


    @Override
    public Poll createPoll() {
        return polls.get(new Random().nextInt(polls.size()));
    }
}
