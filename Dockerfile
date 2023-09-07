FROM maven

WORKDIR /app

COPY . .

EXPOSE 8080

RUN mvn clean package

ENTRYPOINT ["java", "-jar", "target/ToDoAPI.war"]
