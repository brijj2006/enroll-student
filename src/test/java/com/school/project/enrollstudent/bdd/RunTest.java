package com.school.project.enrollstudent.bdd;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true,
        glue = {"classpath:com.school.project.enrollstudent.bdd"},
        features = {"classpath:features"},
        plugin = {
                "pretty", "html:target/cucumber-reports",
                "json:target/cucumber.json"}
)

public class RunTest {
}
