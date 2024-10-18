@echo off
echo Starting build and deployment process...

echo Building with Maven...
call mvn clean package
if %errorlevel% neq 0 (
    echo Maven build failed. Exiting.
    pause
    exit /b %errorlevel%
)

echo Maven build successful. Starting Docker Compose...
docker-compose up --build -d
if %errorlevel% neq 0 (
    echo Docker Compose failed. Exiting.
    pause
    exit /b %errorlevel%
)

echo Build and deployment completed successfully.
pause