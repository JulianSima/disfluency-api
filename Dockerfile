FROM gradle:latest

RUN mkdir -p /home/gradle/project

COPY ./ /home/gradle/project

WORKDIR /home/gradle/project

EXPOSE 8082

CMD [ "gradle", "bootRun" ]