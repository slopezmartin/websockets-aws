

Conectarse a la instancia de Ubuntu en AWS

ssh -i "slopezmartin-demo-ssh.pem" ubuntu@ec2-54-194-60-242.eu-west-1.compute.amazonaws.com

Levantar la aplicación

cd websockets
java -jar build/libs/websockets-0.0.1-SNAPSHOT.jar