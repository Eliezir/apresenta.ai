package com.dev.apresenta_ia.Arquitetura;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.RestController;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

@ActiveProfiles("test")
@AnalyzeClasses(packages = "com.dev.apresenta_ia")
public class ArquiteturaTest {

    @ArchTest
    final static ArchRule ControllerTest = ArchRuleDefinition.classes()
            .that().areAnnotatedWith(RestController.class)
            .should().resideInAPackage("..Controller..");
}
