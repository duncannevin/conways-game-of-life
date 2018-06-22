# Conway's Game of Life
---
Implementation of [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life) built with Scala and Akka actors. 

### Prerequisites

* This assumes that you have [npm](https://npmjs.org/) installed.
* Must have [scala](https://www.scala-lang.org/download/) installed.

### Let's get started

* Clone the application and open application as a sbt project.

* This application is not using any of the [scala](https://www.playframework.com/documentation/2.6.x/ScalaHome) play views and all the views are served by the [Vue](https://vuejs.org/) code base which is inside the `ui` folder.

* Used any of the sbt commands listed in the below according to the requirement which are working fine with this application.(To see more details of [sbt](http://www.scala-sbt.org/))

``` 
    sbt clean           # Clear existing build files
    
    sbt stage           # Build your application from your project’s source directory
    
    sbt run             # Run both backend and frontend builds in watch mode
    
    sbt dist            # Build both backend and frontend sources into a single distribution
    
    sbt test            # Run both backend and frontend unit tests 
```

## Complete Directory Layout

```
├── /app/                           # The backend (scala) application sources (controllers, models, views, assets)
├── /conf/                          # Configurations files and other non-compiled resources (on classpath)
│     ├── application.conf          # Builds the project from source to output(lib and bower) folder
│     ├── logback.xml               # Logging configuration
│     └── routes                    # Routes definition
├── /logs/                          # Logs folder
│     └── application.log           # Default log file
├── /project/                       # Sbt configuration files
│     ├── FrontendCommands.scala    # Frontend build commands
│     ├── FrontendRunHook.scala     # Forntend build PlayRunHook (trigger frontend serve on sbt run)
│     ├── build.properties          # Marker for sbt project
│     └── plugins.sbt               # Sbt plugins declaration
├── /public/                        # Frontend build artifacts will be copied to this directory
├── /target/                        # Generated stuff
│     ├── /universal/               # Application packaging
│     └── /web/                     # Compiled web assets
├── /test/                          # Contains unit tests for scala play sources
├── /ui/                            # Vue front end sources
│     ├── /build/                  # Webpack configs and dev server
│     ├── /config/                 # Webpack configuration
│     ├── /node_modules/           # imported node modules
│     ├── /src/                    # The frontend source code (modules, componensts, models, directives, services etc.) of the application
│     ├── /static/                  # Static assets (images, dependencies loaded directly by html
│     ├── /test/                    # Client side tests
│     ├── /.babelrc/                # Transpiler config
│     ├── .editorconfig             # Define and maintain consistent coding styles between different editors and IDEs
│     ├── .eslintignore             # Files to not lint
│     ├── .postcssrc                # Post CSS config
│     ├── index.html                # Page index  
│     ├── package.json              # Holds various metadata configuration relevant to the ui
│     ├── package-lock.json         # Dependency version lock
│     └── README.md                 # Contains ui build command instructions
├── .gitignore                      # Contains files to be ignored when pushing to git
├── build.sbt                       # Play application build script
├── LICENSE                         # Contains License Agreement file
├── README.md                       # Contains all user guide details for the application
└── ui-build.sbt                    # Associated frontend build scripts with sbt
```
