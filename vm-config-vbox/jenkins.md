```shell
apt-get update
```

### Install JAVA JDK-17

```
apt-get install -y openjdk-17-jdk
java -version
echo $JAVA_HOME
update-alternatives --list java
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64/bin/java
echo $JAVA_HOME
```

### Install jenkins

```
curl -fsSL https://pkg.jenkins.io/debian-stable/jenkins.io.key | sudo tee \
  /usr/share/keyrings/jenkins-keyring.asc > /dev/null

echo deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] \
  https://pkg.jenkins.io/debian-stable binary/ | sudo tee \
  /etc/apt/sources.list.d/jenkins.list > /dev/null

apt-get update
apt-get install -y jenkins
systemctl status jenkins
```

```
apt-get install -y git
git --version
```

```
vi /etc/netplan/00-installer-config.yaml
```

```text
   enp0s8:
        dhcp4: no
        addresses: [192.168.17.1/24]**
```

```
sudo netplan generate
sudo netplan apply
```

### Install maven 3.9.0

```
wget https://dlcdn.apache.org/maven/maven-3/3.9.0/binaries/apache-maven-3.9.0-bin.tar.gz -P /tmp
sudo tar xf /tmp/apache-maven-*.tar.gz -C /opt
sudo ln -s /opt/apache-maven-3.9.0 /opt/maven
sudo vi /etc/profile.d/maven.sh
```

```text
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export M2_HOME=/opt/maven
export MAVEN_HOME=/opt/maven
export PATH=${M2_HOME}/bin:${PATH}
```
```
sudo chmod +x /etc/profile.d/maven.sh
source /etc/profile.d/maven.sh
mvn -version
```
### ip interface config
```text
enp0s8:
    dhcp4: no
    addresses: [192.168.17.1/24]
```
```
sudo netplan generate
sudo netplan apply
```
### Tunnel ngrok start
```
ngrok config add-authtoken ТОКЕН
ngrok http 8080
```