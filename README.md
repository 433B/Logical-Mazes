# Logical Mazes
Logic game where you have to go through the whole maze, collecting prizes along the way

- [x] Spring Boot


## Diagram
<img src="src/main/resources/static/mazes_screen/diagram.png"/>



## Login page
<img src="src/main/resources/static/mazes_screen/main_window.png"/>


## Game page
<img src="src/main/resources/static/mazes_screen/game_window.png"/>


## Game map
<img src="src/main/resources/static/mazes_screen/game_map.png"/>


## Player
<img src="src/main/resources/static/image/lepricom.png"/>

## Coin
<img src="src/main/resources/static/image/gold.png"/>

## Wall
<img src="src/main/resources/static/image/wall.png"/>

## Clue
<img src="src/main/resources/static/image/clue.png"/>

## Finish
<img src="src/main/resources/static/image/pod.png"/>


##  Button for moving 
<img src="src/main/resources/static/mazes_screen/move.png"/>


## Comment 
<img src="src/main/resources/static/mazes_screen/comment.png"/>

### CURL
```
curl --location --request POST 'http://localhost:8080/api/v1/comment' \
--header 'Content-Type: application/json' \
--data-raw '{
        "player": "vladi",
        "game": "mazes",
        "comment": "good",
        "commented_on": "time"
}'
```


## Rate
<img src="src/main/resources/static/mazes_screen/rate.png"/>

### CURL
```
curl --location --request POST 'http://localhost:8080/api/v1/rating' \
--header 'Content-Type: application/json' \
--data-raw '{
        "player": "vladi",
        "game": "mazes",
        "rating": "10",
        "rated_on": "time"
}'
```

## History Score
<img src="src/main/resources/static/mazes_screen/scores.png"/>

### CURL
```
curl --location --request POST 'http://localhost:8080/api/v1/score' \
--header 'Content-Type: application/json' \
--data-raw '{
        "player": "vladi",
        "game": "mazes",
        "point": "1000",
        "player_at": "time"
}'
```
