FROM markhobson/maven-chrome

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

ARG GH_USERNAME=rasangikaw
ARG GH_TOKEN=test123

WORKDIR /usr/src/app

COPY . .

RUN mvn clean -s settings.xml -DskipTests=true install
