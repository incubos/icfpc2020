#!/bin/sh

./build.sh

echo "Starting player1"

if [[ ! -d games ]]; then
  mkdir games
fi

./run.sh https://icfpc2020-api.testkontur.ru/aliens/send?apiKey=3132acdb670045d3b93482f7e0b65359 1 local create | tee games/1.log |\
while read line; do
  if [[ ! -z $(echo "$line" | grep OTHER_PLAYER_KEY_SHOULD_BE) ]]; then
    OTHER_PLAYER_KEY=$(echo "$line" | sed -E 's/.*OTHER_PLAYER_KEY_SHOULD_BE //');
    echo "Starting player 2 with id $OTHER_PLAYER_KEY"
    ./run.sh https://icfpc2020-api.testkontur.ru/aliens/send?apiKey=3132acdb670045d3b93482f7e0b65359 1 local $OTHER_PLAYER_KEY > games/2.log;
  fi;
done;