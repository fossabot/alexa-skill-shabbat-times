package info.tomfi.alexa.skills.shabbattimes.exception.handlers;

import java.util.Optional;

import com.amazon.ask.dispatcher.exception.ExceptionHandler;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

import info.tomfi.alexa.skills.shabbattimes.exception.NoCitySlotException;

public final class NoCitySlotHandler implements ExceptionHandler
{
    @Override
    public boolean canHandle(final HandlerInput input, final Throwable throwable) {
        return throwable instanceof NoCitySlotException;
    }

    @Override
    public Optional<Response> handle(final HandlerInput input, final Throwable throwable) {
        return input.getResponseBuilder()
            .withSpeech("I'm sorry. I can't seem to find your requested city. Please repeat the city name. For a list of all the possible city names, just ask me for help.")
            .withReprompt("Please tell me the requested city name. For a list of all the possible city names, just ask me for help.")
            .withShouldEndSession(false)
            .build();
    }
}