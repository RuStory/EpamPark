package tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 28.05.15
 * Time: 12:57
 * To change this template use File | Settings | File Templates.
 */

/**
 * tag which gives information about success task giving
 */
public class SuccessTask extends TagSupport {
    private static final long serialVersionUID = 1L;
    private boolean success;

    public void setValue(boolean success) {
        this.success = success;
    }

    @Override
    public int doStartTag() throws JspException {
        if (success) {
            return EVAL_BODY_INCLUDE;
        }
        return SKIP_BODY;
    }
}
