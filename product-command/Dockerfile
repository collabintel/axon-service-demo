FROM java:8
VOLUME /tmp
ADD / /home/workspace
WORKDIR "/home/workspace"
RUN bash -c './gradlew build'
RUN bash -c 'mv build/libs/product-command*.jar app.jar'
RUN bash -c 'touch app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]