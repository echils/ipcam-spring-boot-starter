package com.github.ipcam.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RetryUtils
 *
 * @author echils
 */
public abstract class RetryTemplate {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(RetryTemplate.class);

    /**
     * The number of retries to execute
     */
    private int retryCount = 5;

    /**
     * The time interval between retries of execution
     */
    private int retryInterval = 1000;


    public RetryTemplate() {
    }

    public RetryTemplate(int retryCount, int retryInterval) {
        this.retryCount = retryCount;
        this.retryInterval = retryInterval;
    }

    /**
     * Method that needs to be retried
     */
    public abstract void execute();


    /**
     * Start the retry process
     *
     * @return Whether the retry succeeded
     */
    public boolean retry() {
        boolean success = false;
        for (int i = 0; i < retryCount; i++) {
            logger.info("the No.{} retry start...", i + 1);
            try {
                execute();
                success = true;
                break;
            } catch (Exception e) {
                try {
                    Thread.sleep(retryInterval);
                } catch (InterruptedException ignored) {}
            }
        }
        return success;
    }

}
