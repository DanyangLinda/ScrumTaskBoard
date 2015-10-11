compile : taskboard/Startup.java 
	javac -cp ./ ./taskboard/Startup.java
run : taskboard/Startup.class 
	java -cp ./  taskboard/Startup
clean : 
	rm `find ./ -name '*.class'`


