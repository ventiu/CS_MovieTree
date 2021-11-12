run: compile
	java MovieSearchApp

compile: dataWrangler backEnd frontEnd MovieSearchApp.class

test: compile MovieSearchTests.java
	java -jar junit5.jar --class-path . --scan-classpath

MovieSearchTests.java:
	javac -cp .:junit5.jar MovieSearchTests.java -Xlint

dataWrangler: MovieData.class MovieLoader.class
	
MovieData.class: MovieData.java
	javac MovieData.java

MovieLoader.class: MovieLoader.java
	javac MovieLoader.java

backEnd: SearchBackEnd.class

SearchBackEnd.class: SearchBackEnd.java
	javac SearchBackEnd.java

frontEnd: SearchFrontEnd.class

SearchFrontEnd.class: SearchFrontEnd.java
	javac SearchFrontEnd.java

MovieSearchApp.class: MovieSearchApp.java
	javac MovieSearchApp.java	
