FROM eclipse-temurin:17-jre
# The alpine variants had problems linking to libstdc++.

RUN mkdir /opt/app
COPY target/service.jar /opt/app

ENV MODEL_BASE_PATH /opt/app/config/

# Expose port 8080
EXPOSE 8080

CMD ["java", "-jar", "/opt/app/service.jar"]