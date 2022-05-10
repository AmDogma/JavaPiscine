package renderer;

import preprocessor.PreProcessor;

public class RendererStandardImpl implements Renderer {
    PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    public void render(String text) {
        System.out.println(preProcessor.process(text));
    }
}
