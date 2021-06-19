
scoreboard objectives add btp_loaded dummy
scoreboard players add #1 btp_loaded 1
scoreboard players set #2 btp_loaded 1
execute if score #1 btp_loaded = #2 btp_loaded run tellraw @a ["",{"text":"- - - - - - - - - - - - - - - - - - -\n  Thank you for using the ","color":"gray"},{"text":"betterpack","color":"blue","clickEvent":{"action":"open_url","value":"https://github.com/Skyball2000/mc-betterpack"}},{"text":"\n"},{"text":"         by ","color":"gray"},{"text":"_Skyball_","color":"blue","clickEvent":{"action":"open_url","value":"http://yanwittmann.de"}},{"text":" ","color":"gray","clickEvent":{"action":"open_url","value":"http://yanwittmann.de"}},{"text":"and ","color":"gray"},{"text":"Scir","color":"blue","clickEvent":{"action":"open_url","value":"https://mine.ly/Scir.5"}},{"text":"\n"},{"text":"- - - - - - - - - - - - - - - - - - -","color":"gray"}]
