package printer;

import renderer.Renderer;

public class PrinterWithPrefixImpl implements Printer {

    private final Renderer renderer;
    private String prefix = "";

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    public void print(String text) {
        renderer.render(prefix + text);
    }

    public void setPrefix(String text) {
        this.prefix = text;
    }
}
