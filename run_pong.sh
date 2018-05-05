#!/bin/bash

gradle :pong:build
java -jar ./pong/build/libs/pong-0.1.0.jar 
