# CORONA DATA REST API WORKING IN AWS

Runs on port 5200

## Containerization

### Troubleshooting

- Problem - Caused by: com.spotify.docker.client.shaded.javax.ws.rs.ProcessingException: java.io.IOException: No such file or directory
- Solution - Check if docker is up and running!
- Problem - Error creating the Docker image on MacOS - java.io.IOException: Cannot run program “docker-credential-osxkeychain”: error=2, No such file or directory
- Solution - https://medium.com/@dakshika/error-creating-the-docker-image-on-macos-wso2-enterprise-integrator-tooling-dfb5b537b44e

### Please change the host and port as mentioned

### GETTING COMPLETE LIST OF CORONA

http://localhost:5200/api/spring-rest/infinite-zero/corona/all


### GETTING LIST OF SORTED CORONA DATA

http://localhost:5200/api/spring-rest/infinite-zero/corona/all/sort/totalconfirm/100
http://localhost:5200/api/spring-rest/infinite-zero/corona/all/sort/totalnewconfirm/100
http://localhost:5200/api/spring-rest/infinite-zero/corona/all/sort/totalrecover/100
http://localhost:5200/api/spring-rest/infinite-zero/corona/all/sort/totalnewrecover/100
http://localhost:5200/api/spring-rest/infinite-zero/corona/all/sort/totaldeath/100
http://localhost:5200/api/spring-rest/infinite-zero/corona/all/sort/totalnewdeath/100

### last value is the limit 


### GETTING LIST OF  CORONA DATA BASED ON COUNTRY NAME
http://localhost:5200/api/spring-rest/infinite-zero/corona/country/Spain
http://localhost:5200/api/spring-rest/infinite-zero/corona/country/US

## Error

http://tips.stevenchu.com/tech/aws-elastic-beanstalk-invalid-yaml-or-json-error-when-uploading-zip-file-from-mac-os/

```
$ zip -d nameofyourzipfile.zip __MACOSX/\*
```