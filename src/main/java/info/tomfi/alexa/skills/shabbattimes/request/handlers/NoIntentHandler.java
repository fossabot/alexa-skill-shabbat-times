package info.tomfi.alexa.skills.shabbattimes.request.handlers;

import static info.tomfi.alexa.skills.shabbattimes.tools.LocalizationUtils.getBundleFromAttribures;
import static info.tomfi.alexa.skills.shabbattimes.tools.LocalizationUtils.getFromBundle;

import java.util.Optional;
import java.util.ResourceBundle;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;

import info.tomfi.alexa.skills.shabbattimes.annotation.IncludeRequestHandler;
import info.tomfi.alexa.skills.shabbattimes.enums.Attributes;
import info.tomfi.alexa.skills.shabbattimes.enums.BundleKeys;
import info.tomfi.alexa.skills.shabbattimes.enums.CountryInfo;
import info.tomfi.alexa.skills.shabbattimes.enums.Intents;

@IncludeRequestHandler
public final class NoIntentHandler implements IntentRequestHandler
{
    @Override
    public boolean canHandle(final HandlerInput input, final IntentRequest intent)
    {
        return intent.getIntent().getName().equals(Intents.NO.getName());
    }

    @Override
    public Optional<Response> handle(final HandlerInput input, final IntentRequest intent)
    {
        final ResourceBundle bundle = getBundleFromAttribures(input.getAttributesManager().getRequestAttributes());
        String speechOutput = bundle.getString(BundleKeys.DEFAULT_OK.toString());
        try
        {
            if (input.getAttributesManager().getPersistentAttributes().get(Attributes.LAST_INTENT.getName()).equals(Intents.COUNTRY_SELECTED.getName()))
            {
                final String attribValue = (String) input.getAttributesManager().getSessionAttributes().get(Attributes.COUNTRY.getName());
                String speechMiddle = "";
                if (attribValue.equals(CountryInfo.UNITED_STATES.getAbbreviation()))
                {
                    speechMiddle = getFromBundle(bundle, BundleKeys.NOT_FOUND_IN_US);
                }
                else if (attribValue.equals(CountryInfo.ISRAEL.getAbbreviation()))
                {
                    speechMiddle = getFromBundle(bundle, BundleKeys.NOT_FOUND_IN_ISRAEL);
                }
                else if (attribValue.equals(CountryInfo.UNITED_KINGDOM.getAbbreviation()))
                {
                    speechMiddle = getFromBundle(bundle, BundleKeys.NOT_FOUND_IN_UK);
                }
                speechOutput = String.format(getFromBundle(bundle, BundleKeys.NOT_FOUND_FMT), speechMiddle);
            }
        } catch (IllegalStateException exc)
        {
        }
        return input.getResponseBuilder()
            .withSpeech(speechOutput)
            .withShouldEndSession(true)
            .build();
    }
}
