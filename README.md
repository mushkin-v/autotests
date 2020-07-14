# Web Autotests Boilerplate (Java + Selenide + TestNG + Selenoid + Allure)#

Template for automated end-to-end testing.

Run test: `mvn clean test -Dbrowser=chrome -Dselenoid=1 -Dheadless=0`

Generate allure report:  `allure serve target/allure-results`

**parameters:**

- `browser` - chrome, firefox, safari, safari_on_device, safari_on_device_simulator. default - chrome. 
- `headless` - 0 or 1. default - 0
- `selenoid` - 0 or 1. default - 0

**install:**

- install JDK version "11.0.7" 2020-04-14 LTS
- `brew install maven` ver. 3.6.3
- `brew install allure` ver. 2.13.4
- install Selenoid

**Seleniod:**

To install:

 - https://aerokube.com/selenoid/latest/
 - browsers.json config is in the repo root folder
 
Selenoid Docker supports only Chrome and Firefox browsers. 
Safari + mobile Safari tests must be launched without Docker or even without Selenoid.
 
To run/stop with Docker:

- `./cm selenoid start --vnc`
- `./cm selenoid-ui start` - starts Seleniod UI
- `./cm selenoid stop`

Links(Only for start with Docker):

- Open UI - http://localhost:8080
- check status - http://localhost:4444/status

To run/stop without Docker(No UI):

- `./cm selenoid start --use-drivers`
- `./cm selenoid stop --use-drivers`

**Safari Mobile testing(Mac only)**

- Run in console on your Mac: `safaridriver --enable`
- Connect your iPhone or iPad running iOS 13 to your computer via USB. 
- Enable remote automation by going to Settings > Safari > Advanced > Remote automation.
- Next step is to ‘trust’ your computer. This can be done by tapping ‘trust’ on the pop up when you connect the iPhone or iPad to your computer for the first time.
- Keep your iPhone or iPad unlocked
- Launch tests, for example: `mvn clean test -Dbrowser=safari_on_device -Dselenoid=0`
- The top bar will turn orange, this means the browser is being controlled by a remote automation


To launch tests in X-Code Simulator: `mvn clean test -Dbrowser=safari_on_device_simulator -Dselenoid=0`
(Simulator must be opened: XCode->Open Dev tool->Simulator)


**links:**

- https://aerokube.com/selenoid/latest/
- https://ru.selenide.org/quick-start.html
- http://allure.qatools.ru/
- https://github.com/SeleniumHQ/selenium/wiki/DesiredCapabilities

