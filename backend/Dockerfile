# syntax=docker/dockerfile:1.7

# ---- Build stage ----------------------------------------------------------
FROM eclipse-temurin:25-jdk AS build

WORKDIR /workspace

# Copy Maven wrapper first so dependency resolution can be cached
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw && ./mvnw -B -q dependency:go-offline

# Now copy sources and build the Spring Boot jar
COPY src ./src
COPY ruleset.xml ./
RUN ./mvnw -B -DskipTests package \
    && mkdir -p /workspace/app \
    && cp target/*.jar /workspace/app/app.jar

# ---- Runtime stage --------------------------------------------------------
FROM eclipse-temurin:25-jre

WORKDIR /app

# Run as non-root
RUN groupadd --system spring && useradd --system --gid spring --home /app spring \
    && mkdir -p /app/database \
    && chown -R spring:spring /app

COPY --from=build --chown=spring:spring /workspace/app/app.jar /app/app.jar

USER spring

ENV PROFILE_ACTIVE=prod \
    JAVA_OPTS=""

EXPOSE 8080

# SQLite file persists across container restarts when /app/database is mounted as a volume
VOLUME ["/app/database"]

ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS --enable-native-access=ALL-UNNAMED -jar /app/app.jar"]
