frolquote
=========
[![Build Status](https://travis-ci.org/hutoroff/frolquote.svg?branch=dev)](https://travis-ci.org/hutoroff/frolquote)
[![Coverage Status](https://coveralls.io/repos/github/hutoroff/frolquote/badge.svg?branch=dev)](https://coveralls.io/github/hutoroff/frolquote?branch=dev)

This is telegram bot that is triggered by key words and replies with quotes from docent of mathematical analysis at Moscow Technical University of Communications and Informatics.

# Build
To build project execute command:

    mvn clean compile assembly:single
    
# Run
To run bot you should use 2 arguments:
* `token` - provides token received from [@BotFather](https://telegram.me/botfather) after bot registration;
* `username` -  username selected for bot on registration.

Example:

    java -jar frolquote-jar-with-dependencies.jar --token $BOT_TOKEN --username $BOT_USERNAME
    
# Docker
To run latest image of this project (you can check it here: [Docker Hub](https://hub.docker.com/r/hutoroff/frolquote/)) execute command:

    docker run --name frolquote hutoroff/frolquote -i -t --restart always -e FROL_TOKEN=$BOT_TOKEN -e FROL_USERNAME=$BOT_NAME
    
_Do not forget to change `$BOT_TOKEN` and `$BOT_NAME` on your values or register this environment variables_