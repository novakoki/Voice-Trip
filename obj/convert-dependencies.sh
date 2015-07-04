#!/bin/sh
# AUTO-GENERATED FILE, DO NOT EDIT!
if [ -f $1.org ]; then
  sed -e 's!^C:/Users/szq/AppData/Local/Temp!/tmp!ig;s! C:/Users/szq/AppData/Local/Temp! /tmp!ig;s!^D:/GNUstep/msys/1\.0/!/!ig;s! D:/GNUstep/msys/1\.0/! /!ig;s!^D:/GNUstep/msys/1\.0!/usr!ig;s! D:/GNUstep/msys/1\.0! /usr!ig;s!^D:/GNUstep/GNUstep!/GNUstep!ig;s! D:/GNUstep/GNUstep! /GNUstep!ig;s!^D:/GNUstep!/mingw!ig;s! D:/GNUstep! /mingw!ig;s!^h:!/h!ig;s! h:! /h!ig;s!^g:!/g!ig;s! g:! /g!ig;s!^f:!/f!ig;s! f:! /f!ig;s!^e:!/e!ig;s! e:! /e!ig;s!^d:!/d!ig;s! d:! /d!ig;s!^c:!/c!ig;s! c:! /c!ig;' $1.org > $1 && rm -f $1.org
fi
