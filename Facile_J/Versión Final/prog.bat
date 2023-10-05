@echo off

REM Lee el archivo config.txt y encuentra la línea que contiene la ruta de JavaFX
for /f "usebackq delims== tokens=2" %%a in ("config.txt") do (
    set "ruta=%%~a"
)

REM Elimina los espacios en blanco al principio y al final de la ruta
set "ruta=%ruta: =%"

REM Ejecuta el comando java con las opciones necesarias
javaw -p %ruta% --add-modules javafx.controls,javafx.base,javafx.fxml,javafx.graphics,javafx.media,javafx.web -jar Facile_J.jar

REM Pausa para que el mensaje final no se cierre inmediatamente
exit 0
