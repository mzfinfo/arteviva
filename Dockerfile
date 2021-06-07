# na linha abaixo será baixado um sistema operacional com a jdk 11 instalada. 
FROM adoptopenjdk/openjdk11:alpine
# na linha abaixo será criado um grupo e um usuário no sistema operacional, para não usar o usuário root.
RUN addgroup -S spring && adduser -S spring -G spring
# na linha abaixo será realizada a atribuição do usuário spring.
USER spring:spring
# na linha abaixo é criado um parâmentro que receberá o nome da aplicação spring boot que foi gerada pelo maven.
ARG JAR_FILE=target/*.jar
# na linha abaixo será copiado, no sistema operacional, a aplicação contida no parâmentro registrado acima.
COPY ${JAR_FILE} app.jar
# na linha abaixo será definido o comando que será executado no container, para subir a aplicação.
# o parâmetro -Xmx512m é para limitar o conteúdo de memória da máquina virtual no provedor da cloud.
# o parâmetro PORT é para não gerar conflito na cloud com a porta a ser utilizada pelo servidor web.
ENTRYPOINT ["java","-Xmx512m","-Dserver.port=${PORT}", "-jar", "/app.jar"]