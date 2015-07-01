package com.github.kn.appocalypse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EmailScanner {
    private static final Logger LOG = LoggerFactory.getLogger(EmailScanner.class);


    public static final Lock LOCK = new ReentrantLock();
    public static final Condition FINISH_CONDITION = LOCK.newCondition();

    public static void main(String[] args) throws Exception {
        LOG.info("Application is starting");
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/application-context.xml");
        context.registerShutdownHook();
        context.start();
        LOG.info("Application startup complete");

        Runtime.getRuntime().addShutdownHook(new ShutdownNotificationHook());
        LOCK.lock();
        try {
            FINISH_CONDITION.awaitUninterruptibly();
        } finally {
            LOCK.unlock();
        }

        context.stop();
        while (context.isRunning());

        LOG.info("Application shutdown complete");
    }

    public static class ShutdownNotificationHook extends Thread {
        @Override
        public void run() {
            LOCK.lock();
            try {
                FINISH_CONDITION.signal();
            } finally {
                LOCK.unlock();
            }
        }
    }
}
