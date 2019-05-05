FROM payara/server-full:5.184

COPY mymedia-rest/target/mymedia-rest.war $DEPLOY_DIR

COPY mymedia-rest/target/mymedia-rest/WEB-INF/lib/postgresql-9.1-901-1.jdbc4.jar ./appserver/glassfish/lib/