package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;



@CucumberOptions(
features="src/test/java/features", 
glue= {"stepdefinitions"},
plugin = {
        "pretty", // console readable format
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
},
monochrome = true,
tags= "@vieworder")
@RunWith(Cucumber.class)
public class Runner {	

}
		