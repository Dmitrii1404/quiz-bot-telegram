package my.progect.quizbottelegram.constructor.poll.factory;

import my.progect.quizbottelegram.model.Poll;

@FunctionalInterface
public interface PollFactory {

    Poll createPoll();

}
