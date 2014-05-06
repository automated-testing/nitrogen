Nitrogen Testing Framework
=========

*Nitrogen* is a web testing framework that simplify the process of developing functional tests for web applications using the Selenium WebDriver API. The framework hides away the interactions with the WebDriver API from the tests and the underlying Page Object Model (POM) let the developer focus on the functionality being tested rather than having to worry about the interaction with 3rd party API.

The framework goes into a great deal with whittling down any intermittent and what seemed to be obscure reasons that leads a particular test to fail. These includes, but not limited to:


  - Elements are not present or completely displayed in the DOM at the moment where the WebDriver API tries to interact with them from within the tests.
  - AJAX responses that are received after the tests have been terminated, which could potentially leads to a test failure even though there seems to be nothing particularly wrong with the functionality being tested.
  - The dreaded `StaleElementException` that usually happens when the WebDriver API tried to interact with a DOM element that is not present in the DOM anymore due to a previous interaction that led to the removal of that element from the DOM instance held by the WebDriver.

Features
-----------

 - It's written in pure Java.
 - It wraps the Selenium WebDriver API and abstract it away from the POM and the Tests written on top of them.
 - The framework guarantees that there can only be one instance of a WebDriver running at any point in time per each JVM process that runs the tests.
 - Easily swap and use different WebDriver implementations to be used to run the tests. Currently we support Firefox WebDriver and PhantomJS (powered by GhostDriver). Other implementations can be supported via a minimal effort.
 - Error messages and custom exceptions are fairly self-explanatory and often points to the exact place where the problem lies without the need for further debugging.
 - Upon test failures, the framework records screenshots at the point at which the test had failed. This would allows for easy identification of the root cause of test failures without the need to re-run the test locally or to debug it.
 - Automatic generation of test specifications with snapshots describing the steps taken by the test and the asserts that have been made in a human-readable fashion.


Coding Conventions (DRAFT):
---

*More in-depth examples to follow*

- **Everything extends from BaseElement:** Everything is a `BaseElement`, from a validation error to a submit button to a whole form on a page to the page itself.
 - **Tests only interact with component-level classes:** The JUnit tests themselves should always interact with Component-Level classes, any interaction with a child of a particular component should happen through the component itself and never directly from the test.
- **There are 4 levels of abstraction:**
    - Test classes
    - Component/template classes
    - Element classes
    - Selenium/WebDriver classes
- **Tests should carry out actions, not asserts** Asserts needed by a journey are verified by the component methods used and embeded within.
    - Components and elements should assert state.
- **Strictly apply the "Tell don't ask principle" in components and child elements.**
- **Assertion should use the JUnit `assertThat()` method "only".**
- Methods whose only action is an assert should follow the naming convention:

        public void shouldXYZ();

- **Test methods model journeys**
 - Component & element methods model specs
- Tests should only interact with components, not controls
- CSS selectors used to locate instances of BaseElement(s) should only be defined inside their respective classes. No css selectors should be defined anywhere else in the code to avoid duplication of css selectors scattered around in the code base.
- WebDriver API should not be used directly from within the framework, instead the services they provide are wrapped inside framework-specific classes, e.g. `BaseElement` & `WebDriverRemoteFactory`.
- Tests should follow the following structure:
 - Arrange
 - Act
 - Assert
- `StaleElementReferenceException` is your fault!
 - When creating new classes to represent any form controls or components on the page, one thing to keep in mind that instances of these classes should model a form element or a particular component in a certain state, e.g. With a disabled button, instead of saying `button.isDisabled()`, 
we would create a new instance of the button that it is a representation of its disabled state, e.g. `new DisabledButton();`

- When assigning a description to the elements, that need to only include a descriptive name of what the element represent, without any extra wording.

Versioning
----

This project is using [Semantic Versioning] & currently in a Beta release.


Continuous Integration
----
CI builds are provided by [Travis CI], where compilation & test results can be checked.

License
----

TBD

Thanks to…
---
Thanks to [Digitas LBi] for allowing us to open-source this framework.

[Semantic versioning]:http://semver.org
[Digitas LBi]:http://www.digitaslbi.com
[Travis]:https://travis-ci.org/automated-testing/nitrogen
