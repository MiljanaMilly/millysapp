#set the base image for ubuntu - bionic
FROM ubuntu:18.04

#set the author of the image
MAINTAINER milly

#environment variables inside of the container

RUN apt-get update && apt-get install -y gnupg2

#add the authentication key for the repository,
RUN apt-key adv --recv-keys --keyserver hkp://keyserver.ubuntu.com:80 0xcbcb082a1bb943db


#Use the Repository Configuration tool to determine how to set up the MariaDB repository for your release of Ubuntu,
#the version of MariaDB that you want to install, and the mirror that you want to use.
RUN echo 'deb [arch=amd64,arm64,ppc64el] http://mariadb.mirror.nucleus.be/repo/10.4/ubuntu bionic main' | tee /etc/apt/sources.list.d/mariadb.lis

#Once the key is imported and the repository added you can install MariaDB 10.4 from the MariaDB repository with:
RUN apt update
RUN apt install -y -q mariadb-server mariadb-client

RUN chown mysql:mysql /etc/mysql/mariadb.conf.d/50-server.cnf && chmod a=r /etc/mysql/mariadb.conf.d/50-server.cnf
RUN sed -i "s/.*bind-address.*/#bind-address = 127.0.0.1/" /etc/mysql/mariadb.conf.d/50-server.cnf && \
 /etc/init.d/mysql start && mysql -e "GRANT ALL  ON *. * TO 'admin'@'%' IDENTIFIED BY '1' WITH GRANT OPTION; FLUSH PRIVILEGES;CREATE DATABASE IF NOT EXISTS skunksdb;"


EXPOSE 3306/tcp
CMD ["/usr/bin/mysqld_safe"]