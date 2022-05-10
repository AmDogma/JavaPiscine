package preprocessor;

import java.util.Locale;

public class PreProcessorToUpperImpl implements PreProcessor {
    public String process(String text) {
        return text.toUpperCase(Locale.ROOT);
    }
}
