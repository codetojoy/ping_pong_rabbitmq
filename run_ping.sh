#!/bin/bash

gradle :ping:build
java -jar ./ping/build/libs/ping-0.1.0.jar 
