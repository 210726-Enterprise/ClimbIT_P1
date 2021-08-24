# ⛰️ ClimbIT - a simple api

__This api leverages [MrORM](https://github.com/210726-Enterprise/MrORM_P1/tree/master)__

__Docker image:__

```docker pull ryanbusby/climb```


```/api/mountains``` - a collection of mountains throughout the world

```/api/climbers``` - a collection of mountain climbers


__request body to POST to /api/climbers:__

```json
{
  "first": "Katie",
  "last": "Martinez"
}
```

__gives response:__

```
{
  "id": 1234,
  "first": "Katie",
  "last": "Martinez"
}
```

__deletion is a url parameter__

```
api/climbers?climberId=1234
```

__gives response:__
```json
{
"message": "climber 1234 successfully removed"
}
```
