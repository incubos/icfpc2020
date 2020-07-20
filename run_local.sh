#!/bin/sh

./build.sh

if [[ ! -d games ]]; then
  mkdir games
fi

echo "Starting player 1" | tee games/1.log | tee games/full_1.log;
echo "Starting player 2" | tee games/2.log | tee games/full_2.log;


./run.sh https://icfpc2020-api.testkontur.ru/aliens/send?apiKey=3132acdb670045d3b93482f7e0b65359 1 local create |\
while read line; do
  if [[ ! -z $(echo "$line" | grep OTHER_PLAYER_KEY_SHOULD_BE) ]]; then
    OTHER_PLAYER_KEY=$(echo "$line" | sed -E 's/.*OTHER_PLAYER_KEY_SHOULD_BE //');
    echo "Player 2 id $OTHER_PLAYER_KEY"
    ./run.sh https://icfpc2020-api.testkontur.ru/aliens/send?apiKey=3132acdb670045d3b93482f7e0b65359 1 local $OTHER_PLAYER_KEY |\
    tee -a games/full_2.log | grep -E '(command for shipId)|(response demList)|(Start demList)|(Join demList)' >> games/2.log; # Log only important info
  fi;
  echo "$line" >> games/full_1.log;
  # Long only important info
  if [[ ! -z "$(echo "$line" | grep -E '(command for shipId)|(response demList)|(Start demList)|(Join demList)')" ]]; then
      echo "$line" >> games/1.log;
  fi;
done;

echo "Done";