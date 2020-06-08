# EE server and java JRE
FROM jboss/wildfly

# copy war file to EE server deployment folder
ADD moduleEAR/target/testEEServer.ear /opt/jboss/wildfly/standalone/deployments/
