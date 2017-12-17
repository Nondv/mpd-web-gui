FROM clojure:lein-2.8.1-alpine

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY project.clj /usr/src/app/
RUN lein deps
COPY . /usr/src/app
EXPOSE 80
ENV PORT=80
RUN lein uberjar
CMD ["java", "-jar", "target/mpd-web-gui.jar"]
