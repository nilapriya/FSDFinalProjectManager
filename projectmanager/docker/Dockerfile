FROM openjdk:8
	ADD /projectmanager-0.0.1-SNAPSHOT.jar projectmanager.jar
	EXPOSE 8089
	RUN bash -c 'touch /projectmanager.jar'
	ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://localhost:27017/test","-jar","projectmanager.jar"]