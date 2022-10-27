FROM openjdk:17-alpine AS jre-build
WORKDIR /app
COPY comex.jar /app/app.jar
COPY jdeps.info /app/jdeps.info
RUN jlink \
    --verbose \
    --compress 2 \
    --strip-java-debug-attributes \
    --no-header-files \
    --no-man-pages \
    --output jre \
    --add-modules $(cat jdeps.info)

FROM alpine:3.16.2
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
WORKDIR /home/spring
COPY --from=jre-build /app/jre jre
COPY --from=jre-build /app/app.jar app.jar
ENTRYPOINT jre/bin/java -jar app.jar