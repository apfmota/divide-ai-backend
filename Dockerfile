FROM openjdk:17-jdk-slim

# Install python3 and pip
RUN apt-get update && apt-get install -y python3 python3-pip

# Set workdir
WORKDIR /app

# Copy Java jar
COPY target/divide-ai-backend-0.0.1-SNAPSHOT.jar /app/

# Copy python files & requirements
COPY scrapper/ /app/scrapper/
COPY scrapper/requirements.txt /app/scrapper/

# Install python dependencies
RUN pip3 install -r scrapper/requirements.txt

# Run your Java app
CMD ["java", "-jar", "divide-ai-backend-0.0.1-SNAPSHOT.jar"]
