FROM alpine/jdk11
MAINTAINER Alexey Alexanov <alexeyalexanov63@gmail.com>
VOLUME /tmp
RUN apk add --update procps
EXPOSE 9090 9010
ENV JAVA_OPTS="-Xmx256m -Xlog:gc*:file=/tmp/log.gc -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/heapdump.bin -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.local.only=false -Dcom.sun.management.jmxremote.port=9010 -Dcom.sun.management.jmxremote.rmi.port=9010 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Djava.rmi.server.hostname=localhost"
ARG JAR_FILE=/troubleshooting_guide-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} pdm-app.jar
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /pdm-app.jar