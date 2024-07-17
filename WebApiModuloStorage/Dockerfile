# Use the official GlassFish image from the Docker Hub
FROM glassfish:5.1.0

# Copy the WAR file to the autodeploy directory of GlassFish
COPY target/WebApiModuloStorage.war $GLASSFISH_HOME/glassfish/domains/domain1/autodeploy/WebApiModuloStorage.war

# Expose port 8080
EXPOSE 8080

# Start GlassFish server
CMD ["asadmin", "start-domain", "-v"]
