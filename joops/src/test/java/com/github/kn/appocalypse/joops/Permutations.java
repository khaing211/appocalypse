package com.github.kn.appocalypse.joops;


import com.github.kn.appocalypse.joops.annotations.TestMemberAnnotation;
import com.github.kn.appocalypse.joops.annotations.TestMethodAnnotation;
import com.github.kn.appocalypse.joops.annotations.TestTypeAnnotation;

/**
 * The goal of this class is to have every possible permutation of
 * class reference location.  We can then run tests against this class.
 */
@TestTypeAnnotation
public class Permutations {
    FieldTypeReference fieldRef;
    @TestMemberAnnotation({ClassArrayInAnnotation.class}) String annotationTest = "";
    EnumerationTest e = EnumerationTest.blah;
    InnerClass c = new InnerClass();

    public ReturnTypeReference someMethod() {
        //who cares?
        return null;
    }
    
    public void anotherMethod(MethodParameterReference theRef) {
        //no op
    }
    
    public void doSomething() {
        MethodImplementationReference mir = new MethodImplementationReference();
        mir.activate();
    }
    
    @TestMethodAnnotation
    public void annotated() {
        
    }
    
    public class InnerClass {
        
    }
}
