keytool -delete -alias server -keystore %JAVA_HOME%/jre/lib/security/cacerts -storepass changeit
keytool -delete -alias client -keystore %JAVA_HOME%/jre/lib/security/cacerts -storepass changeit

keytool -genkey -alias server -keyalg RSA -keystore server.jks -dname "CN=Saman Alishiri, OU=spring-cloud, O=tutorial, L=Tehran, S=Tehran, C=IR" -ext "SAN:c=DNS:DESKTOP-1LP65UA,IP:127.0.0.1" -storepass 123456 -keypass 123456
keytool -genkey -alias client -keyalg RSA -keystore client.jks -dname "CN=client-app, OU=spring-cloud, O=tutorial, L=Tehran, S=Tehran, C=IR" -ext "SAN:c=DNS:DESKTOP-1LP65UA,IP:127.0.0.1" -storepass 123456 -keypass 123456

keytool -export -alias server -file server.crt -keystore server.jks -storepass 123456
keytool -export -alias client -file client.crt -keystore client.jks -storepass 123456

keytool -import -alias client -file client.crt -keystore server.jks -storepass 123456
keytool -import -alias server -file server.crt -keystore client.jks -storepass 123456

keytool -importkeystore -srckeystore client.jks -destkeystore client.p12 -srcstoretype JKS -deststoretype PKCS12 -srcstorepass 123456 -deststorepass 123456 -srcalias client -destalias client -srckeypass 123456 -destkeypass 123456 -noprompt

keytool -import -trustcacerts -alias client -file client.crt -keystore %JAVA_HOME%/jre/lib/security/cacerts -storepass changeit
keytool -import -trustcacerts -alias server -file server.crt -keystore %JAVA_HOME%/jre/lib/security/cacerts -storepass changeit

