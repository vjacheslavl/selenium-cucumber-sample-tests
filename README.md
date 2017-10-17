Selenium / Feign (REST) / Cucumber sample project
=======================

### What should I know?

##Running tests with specific tags

To run scenarios with @debug1 and @debug2:
mvn verify -Dcucumber.options="--tags @debug1 --tags @debug2"

To run scenarios with @debug1 or @debug2:
mvn verify -Dcucumber.options="--tags @debug1,@debug2"


### Anything else?

Configuration defined in ApplicationProperties.java

You can specify environment to run
-Dapplication.env=local
-Dapplication.env=dev

Yes you can specify which browser to use by using one of the following switches:

- -Dbrowser=firefox
- -Dbrowser=chrome
- -Dbrowser=ie
- -Dbrowser=edge
- -Dbrowser=safari
- -Dbrowser=phantomjs
- -Dbrowser=opera

You can specify a grid to connect to where you can choose your browser, browser version and platform:

- -Dapplication.remotDriver=true 
- -DseleniumGridURL=http://{username}:{accessKey}@ondemand.saucelabs.com:80/wd/hub 
- -Dplatform=xp 
- -Dbrowser=firefox 
- -DbrowserVersion=44

You can also specify a proxy to use

- -Dapplication.proxyEnabled=true
- -Dapplication.proxyHost=localhost
- -Dapplication.proxyPost=8080

Screenshots are taken and included in cucumber report