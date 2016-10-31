@echo off

mysqldump -u manager -h localhost -pmanager lecturemanager > .\db_scripts\lecturemanager.data.sql
mysqldump -u manager -h localhost -pmanager --no-data lecturemanager > .\db_scripts\lecturemanager.schema.sql