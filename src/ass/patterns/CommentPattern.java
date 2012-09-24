package ass.patterns;

import ass.AssDefiner;
import java.util.regex.Pattern;

public class CommentPattern extends DefaultPattern {

    public CommentPattern(AssDefiner definer) {
        super(definer);
        setPattern(Pattern.compile("^;(.*)"));
    }

    @Override
    protected void setDefinerData(String data) {
        setName("CommentSemicolon");
        setData(matcher.group(1).trim());
    }
}