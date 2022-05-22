# ActiveMQ - Broker de message

1. Préparer deux serveurs Debian sur un même réseau.  
2. Sur chacune d'entres-elles installer Active MQ en suivant le tutoriel [vidéo ici](https://www.youtube.com/watch?v=DiOEbxZuZLQ)  
3. ``` apt update ```   
4. ```apt install openjdk-11-jdk ```  
5. ``` wget https://downloads.apache.org/activemq/5.17.1/apache-activemq-5.17.1-bin.tar.gz ```  
6. ``` tar zxvf apache-activemq-5.17.1-bin.tar.gz ```  
7. ``` ln -s apache-activemq-5.17.1 active ```  
8. ``` ls -al ```  
9. ``` cd active/ ```  
10. ``` ./bin/activemq --help ```   
11. ``` ./bin/activemq start ```  
12. ``` ps -aef | grep -i activemq.jar ```  
13. ``` ./bin/activemq stop ```  
14. ``` nano bin/env ```  
15. Décommanté les lignes suivantes :  
ACTIVEMQ_SUNJMX_START="$ACTIVEMQ_SUNJMX_START -Dcom.sun.management.jmxremote.port=11099 "  
ACTIVEMQ_SUNJMX_START="$ACTIVEMQ_SUNJMX_START -Dcom.sun.management.jmxremote.password.file=${ACTIVEMQ_CONF}/jmx.password"     
ACTIVEMQ_SUNJMX_START="$ACTIVEMQ_SUNJMX_START -Dcom.sun.management.jmxremote.access.file=${ACTIVEMQ_CONF}/jmx.access"     
ACTIVEMQ_SUNJMX_START="$ACTIVEMQ_SUNJMX_START -Dcom.sun.management.jmxremote.ssl=false"  
16. ``` chmod 400 conf/jmx.password ```
17. ``` ./bin/activemq start ```  
18. ``` ./bin/activemq bstat ```  
19. ``` ./bin/activemq producer ```  
20. ``` ./bin/activemq bstat ```  
21. ``` ./bin/activemq consumer ```  
22. ``` ./bin/activemq bstat ```  
23. ``` nano /root/apache-activemq-5.17.1/conf/jetty.xml ```  
24. Remplacer  <property name="host" value="**127.0.0.1**"/> par  <property name="host" value="**0.0.0.0**"/>
25. ``` /root/apache-activemq-5.17.1/conf/users.properties ```
26. Vous y trouverez le user et le mot de passe pour se connecter à ActiveMQ.
27. ``` Ajouter un nouveau service sur le serveur ```
28. ``` nano /etc/systemd/system/activemq.service ```
``` [Unit]  
Description=Apache ActiveMQ Message Broker  
After=network-online.target  
[Service]  
Type=forking  
User=root  
Group=root  
WorkingDirectory=/root/apache-activemq-5.17.1/bin  
ExecStart=/root/apache-activemq-5.17.1/bin/activemq start  
ExecStop=/root/apache-activemq-5.17.1/bin/activemq stop  
Restart=on-abort  
[Install]  
WantedBy=multi-user.target  ```
29. ``` systemctl daemon-reload ```  
30. ``` systemctl start activemq.service ```  
