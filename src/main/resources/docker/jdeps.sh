#!/bin/bash
# docker run --name jdeps-analize --volume $(pwd):/home --rm maven:3.8.6-eclipse-temurin-17 /home/src/main/resources/docker/jdeps.sh

# .........................................................................................................................................
# Declarations

declare -i release='17'
declare -a directories=('/home/target' '/root/.m2')

# .........................................................................................................................................
# Executions

cd /home/src/main/resources/docker && rm -rf jars.info jdeps.info && mvn --file /home/pom.xml clean package -DskipTests

for directory in "${directories[@]}"; do
  for file in $(find $directory -name '*.jar' -type f); do
    echo 'Analyzing:' $file && jdeps --multi-release $release --ignore-missing-deps --print-module-deps $file >> jars.info
  done
done

cat jars.info | tr ',' ' ' | xargs -n1 | sort -u | xargs | tr ' ' ',' > jdeps.info
rm -rf hsperfdata* jansi* jars.info && mvn --file /home/pom.xml clean

# .........................................................................................................................................
# End Script