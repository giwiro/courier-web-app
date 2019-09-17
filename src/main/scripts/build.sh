#!/usr/bin/env bash
set -e

GREEN='\033[0;32m';
NC='\033[0m';

export PORT=6969;
export ENV=development;
export DATABASE_URI="jdbc:sqlite:courier.db";

sbt clean compile assembly