FROM markhobson/maven-chrome

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

WORKDIR /usr/src/app

COPY . .

RUN mvn clean -s settings.xml -DskipTests=true install
