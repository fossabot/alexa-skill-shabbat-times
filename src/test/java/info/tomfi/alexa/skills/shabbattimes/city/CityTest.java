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
package info.tomfi.alexa.skills.shabbattimes.city;

import static info.tomfi.alexa.skills.shabbattimes.assertions.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.GsonBuilder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.Cleanup;
import lombok.val;

public final class CityTest
{
    @Test
    @DisplayName("test the creation of the city pojo from a json file")
    public void cityJsonToObject_testJsonFile_validateValues() throws IOException, URISyntaxException
    {
        @Cleanup val breader = Files.newBufferedReader(
            Paths.get(Thread.currentThread().getContextClassLoader().getResource("cities/TST_City1.json").toURI())
        );

        val city = new GsonBuilder().create().fromJson(breader, City.class);

        assertThat(city)
            .cityNameIs("testCity1")
            .geoNameIs("TST-testCity1")
            .geoIdIs(1234567)
            .countryAbbreviationIs("TST");

        assertThat(city.iterator()).toIterable().containsExactlyInAnyOrder("testCity1", "city1", "firstcity");
    }
}
