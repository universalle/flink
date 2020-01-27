#!/bin/bash

#Cassandra

echo "deb http://www.apache.org/dist/cassandra/debian 39x main" | sudo tee -a /etc/apt/sources.list.d/cassandra.sources.list
sudo apt install curl
curl https://www.apache.org/dist/cassandra/KEYS | sudo apt-key add -
sudo apt update
sudo apt install cassandra -y
#sudo systemctl enable cassandra
sudo /lib/systemd/systemd-sysv-install enable cassandra
sudo systemctl start cassandra

cqlsh -e "CREATE KEYSPACE cnh WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor' : 3}; exit"

echo "export CASSANDRA_HOME=/usr/sbin/cassandra" >> ~/.profile
. ~/.profile
echo $CASSANDRA_HOME

#GeoMesa

wget "https://github.com/locationtech/geomesa/releases/download/geomesa_2.11-2.4.0/geomesa-cassandra_2.11-2.4.0-bin.tar.gz"
tar xvf geomesa-cassandra_2.11-2.4.0-bin.tar.gz
cd geomesa-cassandra_2.11-2.4.0

wget -P lib https://repo1.maven.org/maven2/org/apache/cassandra/cassandra-all/3.11.4/cassandra-all-3.11.4.jar
wget -P lib https://repo1.maven.org/maven2/com/datastax/cassandra/cassandra-driver-core/3.7.2/cassandra-driver-core-3.7.2.jar
wget -P lib https://repo1.maven.org/maven2/com/datastax/cassandra/cassandra-driver-mapping/3.7.2/cassandra-driver-mapping-3.7.2.jar
wget -P lib https://repo1.maven.org/maven2/io/netty/netty-all/4.1.17.Final/netty-all-4.1.17.Final.jar
wget -P lib https://repo1.maven.org/maven2/io/dropwizard/metrics/metrics-core/3.2.6/metrics-core-3.2.6.jar
wget -P lib https://repo1.maven.org/maven2/ch/qos/logback/logback-core/1.1.3/logback-core-1.1.3.jar
wget -P lib https://repo1.maven.org/maven2/ch/qos/logback/logback-classic/1.1.3/logback-classic-1.1.3.jar
wget -P lib https://repo1.maven.org/maven2/com/google/guava/guava/18.0/guava-18.0.jar
yes | bin/install-jai.sh
rm lib/t-utils-1.3.1.jar
wget -P lib https://repo1.maven.org/maven2/org/jaitools/jt-utils/1.3.1/jt-utils-1.3.1.jar
wget -P lib https://repo1.maven.org/maven2/jline/jline/2.12.1/jline-2.12.1.jar
#bin/geomesa-cassandra

#Geoserver

sudo apt install unzip
cd /usr/share/
sudo mkdir geoserver
sudo chown -R cnh /usr/share/geoserver
cd geoserver
wget https://netix.dl.sourceforge.net/project/geoserver/GeoServer/2.16.1/geoserver-2.16.1-bin.zip
unzip geoserver-2.16.1-bin.zip
echo "export GEOSERVER_HOME=/usr/share/geoserver/geoserver-2.16.1" >> ~/.profile
. ~/.profile


#GeoMesa Plugin for Geoserver

cd /home/cnh/geomesa-cassandra_2.11-2.4.0/dist/gs-plugins
tar xvf geomesa-cassandra-gs-plugin_2.11-2.4.0-install.tar.gz
cp * /usr/share/geoserver/geoserver-2.16.1/webapps/geoserver/WEB-INF/lib/
cd /usr/share/geoserver/geoserver-2.16.1/webapps/geoserver/WEB-INF/lib/
rm geomesa-cassandra-gs-plugin_2.11-2.4.0-install.tar.gz
rm geomesa-process-wps_2.11-2.4.0.jar

wget https://repo1.maven.org/maven2/org/apache/cassandra/cassandra-all/3.11.4/cassandra-all-3.11.4.jar
wget https://repo1.maven.org/maven2/com/datastax/cassandra/cassandra-driver-core/3.7.2/cassandra-driver-core-3.7.2.jar
wget https://repo1.maven.org/maven2/com/datastax/cassandra/cassandra-driver-mapping/3.7.2/cassandra-driver-mapping-3.7.2.jar
wget https://repo1.maven.org/maven2/io/netty/netty-all/4.1.17.Final/netty-all-4.1.17.Final.jar
wget https://repo1.maven.org/maven2/io/dropwizard/metrics/metrics-core/3.2.6/metrics-core-3.2.6.jar
wget https://repo1.maven.org/maven2/ch/qos/logback/logback-core/1.1.3/logback-core-1.1.3.jar
wget https://repo1.maven.org/maven2/ch/qos/logback/logback-classic/1.1.3/logback-classic-1.1.3.jar

# Start GeoServer

cd /usr/share/geoserver/geoserver-2.16.1/bin
screen -dmS geo ./startup.sh
