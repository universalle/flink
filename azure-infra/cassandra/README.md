##Requirements

Linux (ubuntu 18.04)
Open port 8080 at firewall

##Steps
```bash
git clone https://mstfs.softserveinc.com/tfs/CNH_Industrial/CNH_PoC/_git/CNH_PoC
cd CNH_Poc/azure-infra/cassandra/
sh script.sh
```


Script installing Cassandra,GeoMesa-Cassandra,Geoserver

##Setting up Cassandra for remote access

**Replace node-ip with your real IP address of the server**
**Make these changes in the cassandra.yaml config file:**

```bash
sudo vi /etc/cassandra/cassandra.yaml
```

seed_provider:
    # Addresses of hosts that are deemed contact points.
    # Cassandra nodes use this list of hosts to find each other and learn
    # the topology of the ring.  You must change this if you are running
    # multiple nodes!
    - class_name: org.apache.cassandra.locator.SimpleSeedProvider
      parameters:
          # seeds is actually a comma-delimited list of addresses.
          # Ex: "<ip1>,<ip2>,<ip3>"
          - seeds: "node-ip"


listen_address: node-ip

start_rpc: true

rpc_address: node-ip

```bash
sudo systemctl restart cassandra
```

Open in browser

http://IP-address:8080/geoserver

