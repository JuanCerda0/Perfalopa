@echo off
setlocal enabledelayedexpansion

set ROOT_DIR=%cd%

set SERVICES=sistema-de-administracion sistema-de-cliente sistema-de-inventario sistema-de-logistica sistema-de-ventas sistema-de-web

for %%S in (%SERVICES%) do (
    echo ============================
    echo Compilando %%S
    echo ============================
    cd /d "%ROOT_DIR%\%%S"
    if exist mvnw (
        call mvnw clean package -DskipTests
    ) else (
        call mvn clean package -DskipTests
    )
    if errorlevel 1 (
        echo Error al compilar %%S
        cd /d "%ROOT_DIR%"
        goto :end
    )
    cd /d "%ROOT_DIR%"
)

:end
echo Todos los microservicios han sido compilados.
pause