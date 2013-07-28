[![Build Status](https://travis-ci.org/sbhark/SMServerMonitoring.png)](https://travis-ci.org/sbhark/SMServerMonitoring)

SMServerMonitoring
==================

Simple Server Monitoring Application written in Scala and uses several Java libraries. 

This application is still a work in progress.

About 
==================
This application is not a replacement of existing production quality monitoring softwares such as Nagios or Zabbix. 
It is a possible complementary solution but again not a replacement. 

Note: I plan to use this on my own production environment.  

Architecture 
==================
The architecture of the application is quite simple. All the servers are individual actors. There is one master actor which
collects all data that is sent by the node actors being monitored. As the application embraces the Akka Actor system all 
messages received are processed asynchronously allowing for high performance and efficiency. In addition, there is low CPU and 
memory usage on the system so this application will have minimal affect on any server. 

For data storage MongoDB is used and the application connects to the DB by using the Casbah Scala driver. 

The following data are collected on a specified interval, default interval is every 1 minute. The interval can be specified 
during setup and if it needs to be modified in the future, edit the database entry for polling interval. 

1) System load 

2) RAM Usage

3) SWAP Usage

4) Disk Usage

5) Uptime

6) System logins 

7) Network statistics
  - Total Recieved Packets 
  - Total Outbound Packets
  - Total Received Traffic 
  - Total Outbound Traffic  

There are currently several external APIS that are being used to sending out alerts in case a monitoring server is down 
or unstable. 

1) Twitter - Twitter Direct Message

2) Twilio - SMS, Voice Alerts

3) Mandrill - Sending out Email Alerts

4) Custom Mail Server - Sending out email alerts via custom mail server

Sending alerts by email is on by default the master-server. By default tt will try and send via the local SMTP server installed on the 
system be it postfix or sendmail or some other server. 

All data that is sent by servers being monitored can be viewable by a web interface that simply visualizes the data. 

Database
==================
I chose MongoDB because well I just wanted to learn about NoSQL databases and indeed I have learned more about it. I may in the future 
support MySQL or some other relational database but that requires defining a DB schema and what not and I rather focus on the functionality of 
my application than worry about the schema :) 

Web Interface
==================
All data that exists in the database can be viewable by a web interface. The interface just queries the database for data 
of all servers and just presents it. As data can be collected over a long period of time, the data visible is per day to reduce 
connectivity strains. 

Security 
==================
Although this is a simple application there are still people out there who like to exploit and destroy. So the application 
has some security in place. 

1) A master server must have a unique hash of all servers being monitored. This blocks out any unauthorized servers from 
trying to send data to the master server.

2) All data sent over is encrypted to discourage snooping of data. 

3) The actor based system cannot be terminated remotely. This will prevent any accidental shut-downs and possible malicious 
attempts.

4) All data that the master server receives is verified to make sure it is of the right structure, if not then the data is ignored. If there are more than 5 attempts to send incorrect or corrupt data the offending message producer will be blocked and a CRITICAL alert sent out. 

Application Configuration / Installation 
===================
*Work in progress 

*I developed this on a Debian 7.0 (Wheezy) machine and I only guarantee that the guide below works on Debian based platorms.

1) Make sure you have Scala installed on your system. 

  - To install on Debian Run apt-get install scala
  
2) 

3) Download the smmonitoring-master.jar for the master server to receive monitoring data in the JAR directory in this repo. 

4) Download the smmonitoring-client.jar for any servers to be monitored in the JAR directory in this repository. 



RoadMap
==================
Assuming that I have time to continuously work on this application this is what I plan to do: 

1) Additional third party API integrations for sending out alerts. 

2) Plugin architecture so that third party code can be easily integrated. 

3) A RESTFUL HTTP API so you can create your own interfaces or views. 

4) I think this monitoring system can be easily expanded so that each monitoring server can also talk with other servers. Or the 
master server can send other commands to be executed which means that well its up to your imagination :)

Questions / Feedback 
==================
Have any questions about the application or want to submit feedback (bugs, requests) feel free to send me an email! :)
