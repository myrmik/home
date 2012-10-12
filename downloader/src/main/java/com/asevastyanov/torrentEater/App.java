package com.asevastyanov.torrentEater;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        App util = new App();
        util.init();

    }

    private void init() throws IOException {
        ApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
    }
}
