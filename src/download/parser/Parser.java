package download.parser;

public abstract class Parser<T extends ParseResult> {
    public abstract T parse(String html) throws Exception;

}
