#!/bin/bash

if [ $# -eq 0 ]; then
    echo "Usage: ./run_client [ping | pong] id"
    exit -1
fi

if [ $# -eq 1 ]; then
    echo "Usage: ./run_client [ping | pong] id"
    exit -1
fi

gradle :client:build
java -jar ./client/build/libs/client-0.1.0.jar $1 $2
