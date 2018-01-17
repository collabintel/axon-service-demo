FROM java:8
VOLUME /tmp
ADD / /home/workspace
WORKDIR "/home/workspace"
RUN bash -c 'ls -al'
RUN bash -c './gradlew build'
RUN bash -c 'ls -al'
RUN bash -c 'mv build/libs/product-command*.jar app.jar'
RUN bash -c 'ls -al'
RUN bash -c 'touch app.jar'
RUN bash -c 'ls -al'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/home/app.jar"]