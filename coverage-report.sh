#!/bin/bash -x

jar=target/camel-route-coverage-0.0.1-SNAPSHOT.jar

path=$1;

java -jar $jar $path
