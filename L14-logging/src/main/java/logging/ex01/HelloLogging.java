package logging.ex01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Vladislav
 * created on 06.12.21.
 */
public class HelloLogging {
    private static final Logger logger = LoggerFactory.getLogger(HelloLogging.class);

    public static void main(String[] args) {
        new HelloLogging().log();
    }

    private void log() {
        String value = "test";

/*     Outdated version
This can be seen on legacy projects. Since logging takes time and resources, it is constantly enclosed in
an if condition to include only when debagging
        if (logger.isDebugEnabled()) {
            logger.error("Hello logging:" + value);
        }
*/
        //Modern version
        //Exclude string concatenation as in the old version. Concatenation also affects performance
        logger.error("Hello logging:{}", value);

        try {
            throw new RuntimeException("exception for log");
        } catch (Exception e) {
            logger.error("exception log:", e);
        }
    }
}
