package info.tomfi.alexa.skills.shabbattimes.tools;

import static info.tomfi.alexa.skills.shabbattimes.enums.Attributes.L10N_BUNDLE;

import java.util.Map;
import java.util.ResourceBundle;

import info.tomfi.alexa.skills.shabbattimes.enums.BundleKeys;

public final class LocalizationUtils
{
    private LocalizationUtils()
    {
    }

    public static ResourceBundle getBundleFromAttribures(final Map<String, Object> attributes)
    {
        return (ResourceBundle) attributes.get(L10N_BUNDLE.getName());
    }

    public static String getFromBundle(final ResourceBundle bundle, final BundleKeys key)
    {
        return bundle.getString(key.toString());
    }
}
