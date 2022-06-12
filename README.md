# Distance Dynamic Damage

###Requires [Fabric API](https://github.com/FabricMC/fabric)

## About

Distance Dynamic Damage adds a configuration to change the damage a player causes depending
on the distance the attacking player is to the target.

## Usage
The distance dynamic damage formula is:

`(calculated game damage / distance to target) * DynamicDamageMultiplier`

The `DynamicDamageMultiplier` is by default `1.5` and it can be set on the configuration file
of the mod that is on the config folder of the minecraft instance. The file name is
**distancedynamicdamage.properties** and by default it has this value

`DynamicDamageMultiplier=1.5`

That number is used on the formula to calculate the damage. After changing it on the file,
the following command can be executed by any player with **OP** permissions to reload it

`/Distance Dynamic Damage Reload`

That will reload the value from the file and apply it to the game.
## License

This mod requires Fabric API which has its own license: https://github.com/FabricMC/fabric

This mod itself is available under The Unlicense.