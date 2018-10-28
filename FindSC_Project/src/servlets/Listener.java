package servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import helpers.SendEmail;

@WebListener
public class Listener implements ServletContextListener {
	
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Thread t = new EmailThread();
        t.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

class EmailThread extends Thread {
	
	public void run() {
		while (true) {
            SendEmail.process();
            try {
                Thread.sleep(30 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	}
}