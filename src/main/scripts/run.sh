#!/usr/bin/env bash
set -e

GREEN='\033[0;32m';
NC='\033[0m';

export PORT=6969;
export ENV=development;
export DATABASE_URI="jdbc:sqlite:courier.db";

java -jar "/opt/courier-web-app/target/scala-2.12/Courier Web App-assembly-0.1.0-SNAPSHOT.jar";