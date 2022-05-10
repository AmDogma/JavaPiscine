package preprocessor;

import java.util.Locale;

public class PreProcessorToLowerImpl implements PreProcessor {
    public String process(String text) {
        return text.toLowerCase(Locale.ROOT);
    }
}
