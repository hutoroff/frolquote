FROM java:8
MAINTAINER Vasily Khutorov <hutoroff@gmail.com>
WORKDIR /
ADD target/frolquote-0.0.1-jar-with-dependencies.jar frolquote.jar
CMD java -jar frolquote.jar
