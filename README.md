# cacheService

run ./gradlew bootRun to start the cache service at localhost:8080

curl  -X POST http://localhost:8080/api/v1/cache/foo?capacity=2 create a cache with capacity default is 100

curl  -d "{ name=item1}" -X POST http://localhost:8080/api/v1/cache/foo/key/1 create a item with key at that hashset

curl -X GET http://localhost:8080/api/v1/cache/foo/key/1 get a item with key at that hashset

curl -X DELETE http://localhost:8080/api/v1/cache/foo/key/1 delete a item with key at that hashset
