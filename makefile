# Copyright (c) 2012 Martin Ueding <dev@martin-ueding.de>

javafiles := $(wildcard *.java)
classfiles := $(javafiles:.java=.class)

brick.jar: manifest.txt $(classfiles) *.properties
	jar -cfm $@ $^

%.class: %.java
	javac $^

.PHONY: clean
clean:
	$(RM) *.class *.jar
	$(RM) *.o *.out
	$(RM) *.pyc *.pyo
	$(RM) *.orig
