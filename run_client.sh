#!/bin/bash

gradle :client:build
java -jar ./client/build/libs/client-0.1.0.jar 
