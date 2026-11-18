
# Etapa 1: Build da aplicação
FROM maven:3.9-eclipse-temurin-27 AS build

WORKDIR /app

# Copia o arquivo de dependências
COPY pom.xml .

# Baixa as dependências
RUN mvn dependency:go-offline

# Copia o código-fonte
COPY src ./src

# Compila e gera o JAR
RUN mvn clean package -DskipTests


# Etapa 2: Imagem de execução
FROM eclipse-temurin:27-jre

WORKDIR /app

# Copia o JAR gerado na etapa de build
COPY --from=build /app/target/*.jar app.jar

# Porta padrão do Spring Boot
EXPOSE 8080

# Executa a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
