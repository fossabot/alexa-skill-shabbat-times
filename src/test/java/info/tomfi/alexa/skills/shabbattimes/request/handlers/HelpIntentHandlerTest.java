/**
 * Copyright 2019 Tomer Figenblat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package info.tomfi.alexa.skills.shabbattimes.request.handlers;

import static info.tomfi.alexa.skills.shabbattimes.assertions.Assertions.assertThat;
import static info.tomfi.alexa.skills.shabbattimes.enums.Attributes.L10N_BUNDLE;
import static info.tomfi.alexa.skills.shabbattimes.enums.Intents.HELP;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.RequestEnvelope;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import lombok.val;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public final class HelpIntentHandlerTest
{
    private static IntentRequest fakeRequest;
    private static HandlerInput fakeInput;

    private static HelpIntentHandler handlerInTest;

    @BeforeAll
    public static void initialize()
    {
        val fakeIntent = Intent.builder().withName(HELP.getName()).build();
        fakeRequest = IntentRequest.builder().withIntent(fakeIntent).build();
        val fakeEnvelope = RequestEnvelope.builder().withRequest(fakeRequest).build();
        fakeInput = HandlerInput.builder().withRequestEnvelope(fakeEnvelope).build();

        val bundle = ResourceBundle.getBundle("locales/Responses", Locale.US);
        val attributes = new HashMap<String, Object>();
        attributes.put(L10N_BUNDLE.getName(), bundle);
        fakeInput.getAttributesManager().setRequestAttributes(attributes);

        handlerInTest = new HelpIntentHandler();
    }

    @Test
    @DisplayName("test canHandle method implmentation")
    public void canHandle_fakeArgs_returnTrue()
    {
        assertThat(handlerInTest.canHandle(fakeInput, fakeRequest)).isTrue();
    }

    @Test
    @DisplayName("test handle method implementation")
    public void handle_fakeArgs_validateResponse()
    {
        assertThat(handlerInTest.handle(fakeInput, fakeRequest).isPresent()).isTrue();
    }
}
