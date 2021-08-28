package pl.testing.extensions;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import java.util.logging.Logger;

public class IAExceptionIgnore implements TestExecutionExceptionHandler {
    private static final Logger LOGGER = Logger.getLogger(IAExceptionIgnore.class.getName());

    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {
        if(throwable instanceof IllegalStateException){
            LOGGER.info(("Just ignored IllegalStateException"));
        }
    }
}
