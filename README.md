frolquote
=========
[![Build Status](https://travis-ci.org/hutoroff/frolquote.svg?branch=dev)](https://travis-ci.org/hutoroff/frolquote)
[![Coverage Status](https://coveralls.io/repos/github/hutoroff/frolquote/badge.svg?branch=travis_coverage)](https://coveralls.io/github/hutoroff/frolquote?branch=travis_coverage)

This is telegram bot that is triggered by key words and replies with quotes from docent of mathematical analysis at Moscow Technical University of Communications and Informatics.

# Build
To build project execute command:

    mvn clean assembly:single
    
For correct work of bot you need add to file `./src/main/resources/bot.secured.properties` following content:

    username=%Bot name registered by Telegram bot @BotFather%
    token=%Token provided by @BotFather on registration%