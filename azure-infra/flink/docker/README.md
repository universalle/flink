#Start Flink

docker-compose up -d

#Scale Taskmanager 

docker-compose up -d --scale taskmanager=32