package io.github.ossdbh.genaiveclbl4j.exception;

public class GenAITextLabelGeneratorException extends RuntimeException {
    public GenAITextLabelGeneratorException(String message) {
        super(message);
    }

    public GenAITextLabelGeneratorException(Throwable t) {
        super(t);
    }

    public GenAITextLabelGeneratorException(String message, Throwable t) {
        super(message, t);
    }
}