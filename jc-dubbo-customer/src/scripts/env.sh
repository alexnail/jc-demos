#!/bin/bash

#Config your java home  
#JAVA_HOME=/opt/jdk/

if [ -z "$JAVA_HOME" ]; then
  export JAVA=`which java`
else
  export JAVA="$JAVA_HOME/bin/java"
fi

SERVER_NAME="dubbo_customer"
STARTUP_CLASS="com.jc.customer.Customer"

export JMX_PORT=8037
export CLASSPATH=$BASE_DIR/conf:$(ls $BASE_DIR/lib/*.jar | tr '\n' :)

JVM_APGS="-Xmx512m -Xms256m -server -cp $CLASSPATH " 
 
if [ -z "$JVM_APGS" ]; then
	export YXYT_API_JVM_APGS="$JVM_APGS"
fi