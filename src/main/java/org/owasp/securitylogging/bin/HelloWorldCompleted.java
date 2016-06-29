package org.owasp.securitylogging.bin;

import org.owasp.security.logging.SecurityMarkers;
import org.owasp.security.logging.util.IntervalLoggerController;
import org.owasp.security.logging.util.SecurityLoggingFactory;
import org.owasp.security.logging.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldCompleted {

	public static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);
	
	public HelloWorldCompleted(String[] args) {

		logger.info("It's alive!");
                
                //log with markers
                logger.info(SecurityMarkers.SECURITY_SUCCESS, "User '{}' logged in", "augustd");
                logger.error(SecurityMarkers.SECURITY_FAILURE, "User '{}' attempted to access invalid account '{}'", "snidely", 5555785);
		
		// log command line arguments
		SecurityUtil.logCommandLineArguments(args);
				
		// log shell environment variables
		SecurityUtil.logShellEnvironmentVariables();
				
		// log java system properties
		SecurityUtil.logJavaSystemProperties();

		// Intercept system streams.
		SecurityUtil.bindSystemStreamsToSLF4J();
                System.out.println("This is a system.out");
                System.err.println("This is a system.err");

		// start the interval logger
		IntervalLoggerController wd = SecurityLoggingFactory.getControllerInstance();
		wd.start();

	}
	
	public static void main(String[] args) {
	
		try {
			
			new HelloWorldCompleted(args);
			
		}catch( Throwable t ) {
			t.printStackTrace();
			System.exit(10);
		}
	}

}
