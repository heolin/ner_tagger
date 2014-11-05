all:
	mvn clean compile assembly:single

clean:
	mvn clean

run:
	mvn install
	mvn exec:java
