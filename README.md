# Location Mod 
Available for: `1.15.2`, `1.16.1`

Minecraft forge based mod that displays information about direction, 
location, biome and highlighted block. 

This mod is probably of no use to you, there are probably other mods 
that do the same thing better. This project is meant as an introduction 
to programming for my kids. 

### Controls: 

`=` Toggle all location info on/off  
`[` Toggle highlighted block info on/off  
`]` Toggle terrain (biome + block) info on/off 

### Build:  

`./gradlew clean build`

### Install: 

#### Mac:  
`cp build/libs/location*.jar ~/Library/Application\ Support/minecraft/mods/`

#### Windows:  

Press the Windows key and R at the same time
Open `%appdata%\.minecraft\mods\` folder
Copy `location*.jar` to that folder

#### Linux: 

You probably don't need help