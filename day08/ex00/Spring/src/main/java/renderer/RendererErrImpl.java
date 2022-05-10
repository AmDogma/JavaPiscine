package renderer;

import preprocessor.PreProcessor;

public class RendererErrImpl implements Renderer {
    PreProcessor preProcessor;

    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    public void render(String text) {
        System.err.println(preProcessor.process(text));
    }
}
