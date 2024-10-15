apt-cache search openjdk | grep openjdk-17 -y
sudo apt install openjdk-17-jre -y
sudo apt install openjdk-17-jdk -y
java --version

sudo apt install maven -y
mvn -version

sudo apt update
sudo apt install apt-transport-https ca-certificates curl software-properties-common -y
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu focal stable" -y
apt-cache policy docker-ce

sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose -y
sudo chmod +x /usr/local/bin/docker-compose -y
docker-compose --version

sudo apt install docker-ce -y
sudo systemctl status docker

