package my.project.quizbottelegram.constructor.poll.factory;

import my.project.quizbottelegram.model.Poll;

@FunctionalInterface
public interface PollFactory {

    Poll createPoll();

}
