FROM java:8
MAINTAINER Vasily Khutorov <hutoroff@gmail.com>
WORKDIR /
ADD target/frolquote-jar-with-dependencies.jar frolquote.jar
CMD java -jar frolquote.jar --token $FROL_TOKEN --username $FROL_USERNAME
