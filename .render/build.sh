#!/usr/bin/env bash
# Install Python dependencies
pip install -r scrapper/requirements.txt

# Build the Java app
./mvnw clean install
