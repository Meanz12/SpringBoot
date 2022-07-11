package com.rubypaper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
public class LoggingRunner implements ApplicationRunner{
	
	private Logger logger = LoggerFactory.getLogger(LoggingRunner.class);
	
	@Override
	public void run(ApplicationArguments args) throws Exception{
		logger.trace("Trace 로그메시지");
		logger.debug("DEBUG 로그메시지");
		logger.info("INFO 로그메시지");
		logger.warn("WARN 로그메시지");
		logger.error("ERROR 로그메시지");
		
	}
}
