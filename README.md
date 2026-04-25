# Testing and Software Quality
## Laboratory works done by Andrii Litvinov
### Laboratory work 1: See the [calculator realization](src/main/java/math) and [calculator testing](src/test/java/math), TestNG suites can be found in [testNGSuites](src/test/resources/testNGSuites)
### Laboratory work 2: See the [XPath and CSS locators](LB2.md)
### Laboratory work 3: See the [OrangeHRM package](src/test/java/orangeHRM)
### Laboratory work 4: See the POM classes [OrangeHRM base](src/test/java/orangeHRM/base) and [OrangeHRM pages](src/test/java/orangeHRM/pages) and their usage in [OrangeHRM steps](src/test/java/orangeHRM/steps)
### Laboratory work 5: See the Cucumber implementation in [OrangeHRM steps](src/test/java/orangeHRM/steps) and [OrangeHRM runners](src/test/java/orangeHRM/runners), [OrangeHRM hooks](src/test/java/orangeHRM/hooks), [features](src/test/resources/features)
### Laboratory work 6: See the API tests implementation in [Petstore tests](src/test/java/petstore/tests)

#### To run JUnit unit tests use [CalculatorTest](src/test/java/math/tests/CalculatorTest.java)
#### To run TestNG unit tests use [LucasTest](src/test/java/math/tests/LucasTest.java) or [lucal-all.xml](src/test/resources/testNGSuites/lucas-all.xml)
#### To run Cucumber tests use [CucumberRunner](src/test/java/orangeHRM/runners/CucumberRunner.java) or [features-all.xml](src/test/resources/cucumberSuites/features-all.xml)
#### To generate Allure report use  ```allure serve target/allure-results```
#### To clean Allure results use ```Remove-Item -Recurse -Force target/allure-results```
