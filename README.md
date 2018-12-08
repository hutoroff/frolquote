frolquote
=========
[![Build Status](https://travis-ci.org/hutoroff/frolquote.svg?branch=dev)](https://travis-ci.org/hutoroff/frolquote)
[![Coverage Status](https://coveralls.io/repos/github/hutoroff/frolquote/badge.svg?branch=travis_coverage)](https://coveralls.io/github/hutoroff/frolquote?branch=travis_coverage)

This is telegram bot that is triggered by key words and replies with quotes from docent of mathematical analysis at Moscow Technical University of Communications and Informatics.

# Build
To build project execute command:

    mvn clean assembly:single
    
# Run
To run bot you should use 2 arguments:
* `token` - provides token received from [@BotFather](https://telegram.me/botfather) after bot registration;
* `username` -  username selected for bot on registration.

Example:

    java -jar frolquote-jar-with-dependencies.jar --token $BOT_TOKEN --username $BOT_USERNAME