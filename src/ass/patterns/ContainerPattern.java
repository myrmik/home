package ass.patterns;

import ass.AssDefiner;

import java.util.regex.Pattern;

public class ContainerPattern extends DefaultPattern {

    public ContainerPattern(AssDefiner definer) {
        super(definer);
        setPattern(Pattern.compile("^\\[(.*)\\]$"));
    }

    @Override
    protected void setDefinerData(String data) {
        setName("Container");
        setData(matcher.group(1).trim());
    }
}
