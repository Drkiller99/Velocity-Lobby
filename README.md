# Velocity-Lobby
This plugin was originally made on 2020/05/19 but I had a lot of other private projects I was working on, I've recently just discovered it again as making a Minecraft server for a few of my friends and wanted a fallback server. I figured why not update to the latest API and upload it. I hope everyone enjoys it and I will be releasing some projects that I've been working on for a while in the future. I may work with velocity API in the future if plugins are requested enough.

# Configuration

The config is pretty basic as it was my first time working with their API, it's a simple one-liner that you need to input your fallback servers name. It is set by default as "lobby".

# Commands
The current commands are listed below and they all direct you to the specified lobby defined in your config.toml.
/hub | /lobby | /logout

Would you like to edit, add, or remove one of the plugins aliases? Please refer to line 54 of the main.java to edit the code below.

```java
cmd.register(new LobbyCommand(s), "hub", "lobby", "logout");
```

You can also use the command /server <name> by default now without downloading this plugin.

# Summary
> This won't be updated anymore not will anything else be defined as I'm currently involved with to many projects, It could possibly be updated a year down the line, however, I'm doubtful. 
  

