FROM flink:latest
#ARG JAR_FILE
#COPY ${JAR_FILE}  job.jar
#ADD ./deploy-job.sh deploy-job.sh
#RUN chmod +x ./deploy-job.sh
#ENV JOB_MANAGER_HOST 172.17.0.1
#ENV JOB_MANAGER_PORT 30081
#ENTRYPOINT ["sh", "-c", "./deploy-job.sh ./job.jar"]
ENTRYPOINT ["/docker-entrypoint.sh"]
#CMD sh ./deploy-job.sh
