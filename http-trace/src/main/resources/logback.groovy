appender("CONSOLE", ConsoleAppender) {
	filter(ThresholdFilter) {
		level = INFO
	}
	
	encoder(PatternLayoutEncoder) {
		pattern = "[%d{HH:mm:ss.SSS}][%thread][%-5level][%logger{36}] - %msg%n"
	}
}

root(WARN, ["CONSOLE"])