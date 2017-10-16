package com.example.at.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber/web", "json:target/cucumber/cucumber-web.json"},
        features = {"src/test/resources/features"},
        tags = {"@web"},
        glue = {"com.example.at.stepdefs", "com.example.at.hooks"}
)
public class RunWebTest {

}
