# EE server and java JRE
FROM jboss/wildfly

# copy JDBC Driver jar into server LIB folder
ADD docker/postgresql-42.2.13.jar /opt/jboss/wildfly/modules/org/postgresql/main/
ADD docker/module.xml /opt/jboss/wildfly/modules/org/postgresql/main/

# copy server datasource config
ADD docker/standalone.xml /opt/jboss/wildfly/standalone/configuration

# copy war file to EE server deployment folder
ADD moduleEAR/target/testEEServer.ear /opt/jboss/wildfly/standalone/deployments/
