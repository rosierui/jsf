package org.moonwave.servlet;

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.moonwave.util.AppProperties;
import org.moonwave.util.MailProperties;


/**
 * Initializes web application resources.
 * 
 */
public class MailInitServlet extends HttpServlet {

    private static Log log = LogFactory.getLog(MailInitServlet.class);

    @Override
    public void init() {

        try {
            Properties properties = new Properties();
            try {
                // stackoverflow.com/questions/9963373/read-properties-file-in-static-code-of-a-jsf-web-application
                // InputStream input = EmailController.class.getResourceAsStream("/deploy.properties");
                InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("mail.properties");
                properties.load(input);

                MailProperties mailProperties = MailProperties.getInstance();
                mailProperties.setFrom(properties.getProperty("from"));
                mailProperties.setTo(properties.getProperty("to"));
                mailProperties.setReplyTo(properties.getProperty("replyTo"));
                mailProperties.setCc(properties.getProperty("cc"));
                mailProperties.setBcc(properties.getProperty("bcc"));
                mailProperties.setSubjectPrefix(properties.getProperty("subjectPrefix"));
                mailProperties.setSubjectSuffix(properties.getProperty("subjectSuffix"));
                mailProperties.setBodyPrefix(properties.getProperty("bodyPrefix"));
                mailProperties.setBoySuffix(properties.getProperty("boySuffix"));
                mailProperties.setPostscript(properties.getProperty("postscript"));
            } catch (Exception e) {
                System.out.println(e);
            }

            log.info("InitServlet initialization succeeded");
        } catch (Throwable e) {
            log.fatal("InitServlet initialization error: " + e);
        }

        try {
            Properties properties = AppProperties.getInstance().getProperties();
            try {
                InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
                properties.load(input);

            } catch (Exception e) {
                System.out.println(e);
            }

            log.info("InitServlet initialization succeeded");
        } catch (Throwable e) {
            log.fatal("InitServlet initialization error: " + e);
        }
    }

    @Override
    public void destroy() {
    }
}
