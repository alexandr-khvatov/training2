```shell
apt-get update
```

### Install JAVA JDK-17

```shell
apt-get install -y openjdk-17-jdk
java -version
echo $JAVA_HOME
sudo update-java-alternatives -l
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64/bin/java
echo $JAVA_HOME
```

### Install tomcat

```
useradd tomcat -U -s /bin/false -d /opt/tomcat -m

wget https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.73/bin/apache-tomcat-9.0.73.tar.gz -P /tmp
VERSION=9.0.73
sudo tar -xf /tmp/apache-tomcat-${VERSION}.tar.gz -C /opt/tomcat/
sudo ln -s /opt/tomcat/apache-tomcat-${VERSION} /opt/tomcat/latest
chown -R tomcat:tomcat /opt/tomcat
sudo sh -c 'chmod +x /opt/tomcat/latest/bin/*.sh'
```

```
vi /etc/systemd/system/tomcat.service
```

```text
[Unit]
Description=Tomcat 9.0.73 Servlet Container
After=network.target

[Service]
Type=forking
User=tomcat
Group=tomcat
Environment="JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64"
Environment="JAVA_OPTS=-Djava.security.egd=file:///dev/urandom -Djava.awt.headless=true"

Environment="CATALINA_BASE=/opt/tomcat/latest"
Environment="CATALINA_HOME=/opt/tomcat/latest"
Environment="CATALINA_PID=/opt/tomcat/latest/temp/tomcat.pid"
Environment="CATALINA_OPTS=-Xms512M -Xmx1024M -server -XX:+UseParallelGC"

ExecStart=/opt/tomcat/latest/bin/startup.sh
ExecStop=/opt/tomcat/latest/bin/shutdown.sh

[Install]
WantedBy=multi-user.target
```

```
systemctl daemon-reload
systemctl enable --now tomcat
systemctl status tomcat
```
### config tomcat permission
```
sudo vi /opt/tomcat/latest/conf/tomcat-users.xml
```

```text
<user username="jenkins" password="<password>" roles="manager-script"/>
```

```
sudo vi /opt/tomcat/latest/webapps/manager/META-INF/context.xml
```

```text
<Context antiResourceLocking="false" privileged="true" >
<!--
  <Valve className="org.apache.catalina.valves.RemoteAddrValve"
         allow="127.d+.d+.d+|::1|0:0:0:0:0:0:0:1" />
-->
</Context>
```

```
sudo vi /opt/tomcat/latest/webapps/host-manager/META-INF/context.xml
```

```text
<Context antiResourceLocking="false" privileged="true" >
  <Valve className="org.apache.catalina.valves.RemoteAddrValve"
         allow="127.d+.d+.d+|::1|0:0:0:0:0:0:0:1|192.168.17.1" />
</Context>
```

```
vi /etc/netplan/00-installer-config.yaml
```
### ip interface config
```text
enp0s8:
    dhcp4: no
    addresses: [192.168.17.2/24]
```
```
sudo netplan generate
sudo netplan apply
```