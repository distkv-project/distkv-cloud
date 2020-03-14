#!/usr/bin/env bash

set -e
set -x

if [ ! -f "$file" ]; then
  wget https://distkv-1252912764.cos.ap-shanghai.myqcloud.com/distkv-server-jars/nightly/distkv-server.jar
fi

cp ../target/distkv-cloud-0.1.0-SNAPSHOT-jar-with-dependencies.jar ./distkv_cloud.jar
cp ../src/main/java/com/distkv/tool/dashboard/index.html ./index.html

nohup java -cp ./distkv-server.jar com.distkv.server.storeserver.StoreServer >> /dev/null 2>&1 &

sleep 2
nohup java -cp ./distkv_cloud.jar com.distkv.tool.dashboard.DashboardServer >> /dev/null 2>&1 &
