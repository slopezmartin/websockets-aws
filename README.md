

Conectarse a la instancia de Ubuntu en AWS

ssh -i "slopezmartin-demo-ssh.pem" ubuntu@ec2-54-194-60-242.eu-west-1.compute.amazonaws.com


Subir código mediante SCP

Subir el proyecto a la instancia
Desde tu máquina local, usa scp para transferir el proyecto:  
scp -i "path/to/your-key.pem" -r /path/to/your/project ubuntu@<public-ip>:/home/ubuntu/
Reemplaza /path/to/your/project con la ruta de tu proyecto local.

Levantar la aplicación

cd websockets
java -jar build/libs/websockets-0.0.1-SNAPSHOT.jar

Instalar git en la instancia

Conectarse a la instancia

#Actualizar repositorios
sudo apt get

#Install git
sudo apt install git -y


#Crear clave ssh para conexión a GitHub

ssh-keygen -t ed25519 -C "tu_correo@example.com"