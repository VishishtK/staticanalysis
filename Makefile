build:
	mvn clean
	mkdir -p  target/generated-sources/antlr4/com/cns/grammar/
	cp src/main/antlr4/com/cns/grammar/GoParserBase.java target/generated-sources/antlr4/com/cns/grammar/GoParserBase.java
	mvn package