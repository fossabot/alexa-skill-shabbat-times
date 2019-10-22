package info.tomfi.alexa.skills.shabbattimes.exception;

import com.amazon.ask.exception.AskSdkException;

/**
 * Extension of com.amazon.ask.exception.AskSdkException. Used when the requested city wasn't found.
 *
 * @author Tomer Figenblat {@literal <tomer.figenblat@gmail.com>}
 */
public final class NoCityFoundException extends AskSdkException
{
    private static final long serialVersionUID = 22L;

    public NoCityFoundException(final String message)
    {
        super(message);
    }

    public NoCityFoundException(final String message, final Throwable cause)
    {
        super(message, cause);
    }
}
