Helpful commands:

Build command
docker build -t your_dockerhub_username/<image-name> .

Run command
docker run --name <container-name> -p 80:8080 -d your_dockerhub_username/<image-name>

List running containers
sudo docker ps

Jump into the container
docker exec -it <name-of-container> sh

to run the yaml file
docker-compose up --build