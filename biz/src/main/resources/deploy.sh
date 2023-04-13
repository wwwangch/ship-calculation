cp $WORKSPACE/biz/src/main/resources/Dockerfile $WORKSPACE/biz/build/libs
cd $WORKSPACE/biz/build/libs
docker build -t 192.168.100.96:80/library/phm:0.0.1 .
docker login -u admin -p Harbor12345 192.168.100.96:80
docker push 192.168.100.96:80/library/phm:0.0.1