# PetStoreAPI by Helen Wu

## Github URL for my feature branch:
https://github.com/LiyWu/PetStoreAPI/tree/feature/HelenWu-CBATest

## Development Environment
IDE: IntelliJ  
JDK: Java17

There are two ways to run the test:
### Clone the code and run locally

1. clone the code and switch to the feature branch
```sh
git clone https://github.com/LiyWu/PetStoreAPI.git
cd PetStoreAPI
# switch to the feature branch
git checkout feature/HelenWu-CBATest 
```

2. run the tests
```sh
mvn test -DsuiteXmlFile=src/test/resources/PetStore_API_Regression.xml
```

3. view the report
```sh
open ExtentReports/ExtentReportResults.html
```

### Goto Github and run from Actions
1. go to https://github.com/LiyWu/PetStoreAPI/actions/workflows/ci.yml
2. click button "Run workflow", select "feature/HelenWu-CBATest", then click "Run workflow"
3. download the test-results artifact in Artifacts section, unzip it and view the html report "ExtentReportResults.html"
