echo '::::::::::::::: Eliminando contenderos e imagenes :::::'
docker-compose -p disfluency down --rmi local
echo '::::::::::::::: Contruyendo Imagenes ::::::::::::::::::'
docker-compose build --progress=plain
echo '::::::::::::::: Imagenes Creadas Correctamente ::::::::'
echo ':::::::::::::::::::::::::::::::::::::::::::::::::::::::'
echo '::::::::::::::: Creando Contenedores ::::::::::::::::::'
docker-compose -p disfluency up --force-recreate -d
echo '::::::::::::::: Contenedores Creados Correctamente ::::'
echo ':::::::::::::::::::::::::::::::::::::::::::::::::::::::'
echo '::::::::::::::: Disfluency Levantado Correctamente ::::'

