package util;

import javax.swing.text.html.HTMLEditorKit;

public class ParserGetter extends HTMLEditorKit {

    // purely to make this method public
    @Override
    public HTMLEditorKit.Parser getParser() {
        return super.getParser();
    }
}
